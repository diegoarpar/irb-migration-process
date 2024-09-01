package com.irb.migration.service.ETL;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FDataHandling;
import com.irb.migration.entity.from.FExpedited;
import com.irb.migration.entity.from.FRiskFactor;
import com.irb.migration.entity.to.DataHandling;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.entity.to.NonStandards;
import com.irb.migration.entity.to.Recruitments;
import com.irb.migration.service.transforms.ELTFactoryTransformation;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ETLRecruitments implements IETL{

    @Inject
    public ELTFactoryTransformation eltFactoryTransformation;

    public boolean StartETL() {
        EntityManagerFactory sourceEMF = Persistence.createEntityManagerFactory("sourcePU");
        EntityManager sourceEM = sourceEMF.createEntityManager();


        EntityManagerFactory destEMF = Persistence.createEntityManagerFactory("destPUR");
        EntityManager destEM = destEMF.createEntityManager();

        // Extract data from source
        List<FRiskFactor> sourceData = sourceEM.createQuery("SELECT s FROM FRiskFactor s", FRiskFactor.class).getResultList();
        List<IrbApplications> applications = destEM.createQuery("SELECT s FROM IrbApplications s", IrbApplications.class).getResultList();
        List<FDataHandling> dataHandling = destEM.createQuery("SELECT s FROM FDataHandling s", FDataHandling.class).getResultList();

        Map<String, IrbApplications> applicatinosMap = applications.stream().collect(Collectors.toMap(application -> application.ApplicationCode, application -> application));
        Map<String, FDataHandling> dataHandlingMap = dataHandling.stream().filter(row -> !Strings.isNullOrEmpty(row.application_id)).collect(Collectors.toMap(row -> row.application_id.toUpperCase(), row -> row));
        applications = null;

        // Transform data
        List<Recruitments> transformedData = eltFactoryTransformation.getTransformation("recruitment").TransformData(sourceData, applicatinosMap, dataHandlingMap);

        // Load data into destination
        destEM.getTransaction().begin();
        for (Recruitments destEntity : transformedData) {
            destEM.persist(destEntity);
        }
        destEM.getTransaction().commit();

        System.out.println("ETL process completed successfully.");

        sourceEM.close();
        sourceEMF.close();
        destEM.close();
        destEMF.close();

        return true;
    }
}
