package com.irb.migration.service.ETL;

import com.irb.migration.entity.from.FDataHandling;
import com.irb.migration.entity.from.FInformedConsent;
import com.irb.migration.entity.to.AspNetUsers;
import com.irb.migration.entity.to.DataHandling;
import com.irb.migration.entity.to.InformedConsents;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.service.transforms.ELTFactoryTransformation;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ETLInformedConsents implements IETL{

    @Inject
    public ELTFactoryTransformation eltFactoryTransformation;

    public boolean StartETL() {
        EntityManagerFactory sourceEMF = Persistence.createEntityManagerFactory("sourcePU");
        EntityManager sourceEM = sourceEMF.createEntityManager();


        EntityManagerFactory destEMF = Persistence.createEntityManagerFactory("destPUR");
        EntityManager destEM = destEMF.createEntityManager();

        // Extract data from source
        List<FInformedConsent> sourceData = sourceEM.createQuery("SELECT s FROM FInformedConsent s", FInformedConsent.class).getResultList();
        List<AspNetUsers> users = destEM.createQuery("SELECT s FROM AspNetUsers s", AspNetUsers.class).getResultList();
        List<IrbApplications> applications = destEM.createQuery("SELECT s FROM IrbApplications s", IrbApplications.class).getResultList();

        Map<String, AspNetUsers> usersMap = users.stream().collect(Collectors.toMap(aspNetUsers -> aspNetUsers.NormalizedEmail , aspNetUsers -> aspNetUsers));
        Map<String, IrbApplications> applicatinosMap = applications.stream().collect(Collectors.toMap(application -> application.ApplicationCode, application -> application));
        applications = null;
        users = null;
        // Transform data
        List<InformedConsents> transformedData = eltFactoryTransformation.getTransformation("informed").TransformData(sourceData, usersMap, applicatinosMap);

        // Load data into destination
        destEM.getTransaction().begin();
        for (InformedConsents destEntity : transformedData) {
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
