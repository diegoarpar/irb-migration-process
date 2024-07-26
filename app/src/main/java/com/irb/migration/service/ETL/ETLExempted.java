package com.irb.migration.service.ETL;

import com.irb.migration.entity.from.FExempted;
import com.irb.migration.entity.from.FExpedited;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.entity.to.NonStandards;
import com.irb.migration.service.transforms.ELTFactoryTransformation;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ETLExempted implements IETL{

    @Inject
    public ELTFactoryTransformation eltFactoryTransformation;

    public boolean StartETL() {
        EntityManagerFactory sourceEMF = Persistence.createEntityManagerFactory("sourcePU");
        EntityManager sourceEM = sourceEMF.createEntityManager();


        EntityManagerFactory destEMF = Persistence.createEntityManagerFactory("destPUR");
        EntityManager destEM = destEMF.createEntityManager();

        // Extract data from source
        List<FExempted> sourceData = sourceEM.createQuery("SELECT s FROM FExempted s", FExempted.class).getResultList();
        List<IrbApplications> applications = destEM.createQuery("SELECT s FROM IrbApplications s", IrbApplications.class).getResultList();

        Map<String, IrbApplications> applicatinosMap = applications.stream().collect(Collectors.toMap(application -> application.ApplicationCode, application -> application));
        applications = null;

        // Transform data
        List<NonStandards> transformedData = eltFactoryTransformation.getTransformation("exempted").TransformData(sourceData, applicatinosMap);

        // Load data into destination
        destEM.getTransaction().begin();
        for (NonStandards destEntity : transformedData) {
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
