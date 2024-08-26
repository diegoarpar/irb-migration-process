package com.irb.migration.service.ETL;

import com.irb.migration.entity.from.FApplicationFormBasic;
import com.irb.migration.entity.from.FCoinvestigator;
import com.irb.migration.entity.to.*;
import com.irb.migration.service.transforms.ELTFactoryTransformation;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ETLCoInvestigator implements IETL{

    @Inject
    public ELTFactoryTransformation eltFactoryTransformation;

    public boolean StartETL() {
        EntityManagerFactory sourceEMF = Persistence.createEntityManagerFactory("sourcePU");
        EntityManager sourceEM = sourceEMF.createEntityManager();


        EntityManagerFactory destEMF = Persistence.createEntityManagerFactory("destPUR");
        EntityManager destEM = destEMF.createEntityManager();

        // Extract data from source
        List<FCoinvestigator> sourceData = sourceEM.createQuery("SELECT s FROM FCoinvestigator s", FCoinvestigator.class).getResultList();
        List<UserProfiles> users = destEM.createQuery("SELECT s FROM UserProfiles s", UserProfiles.class).getResultList();
        List<IrbApplications> applications = destEM.createQuery("SELECT s FROM IrbApplications s", IrbApplications.class).getResultList();
        List<Universities> universities = destEM.createQuery("SELECT s FROM Universities s", Universities.class).getResultList();
        Map<String, Universities> univerisityMap = Collections.singletonMap("gannon", universities.getFirst());

        Map<String, UserProfiles> usersMap = users.stream().collect(Collectors.toMap(aspNetUsers -> aspNetUsers.UserId.NormalizedEmail , aspNetUsers -> aspNetUsers));
        Map<String, IrbApplications> applicatinosMap = applications.stream().collect(Collectors.toMap(application -> application.ApplicationCode, application -> application));
        applications = null;
        users = null;
        // Transform data
        List<CoInvestigators> transformedData = eltFactoryTransformation.getTransformation("coinvestigator").TransformData(sourceData, usersMap, applicatinosMap, univerisityMap);

        destEM.getTransaction().begin();
        // Load data into destination
        Iterator<Map.Entry<String, UserProfiles>> userUpdated = usersMap.entrySet().iterator();
        while (userUpdated.hasNext()) {
            destEM.persist(userUpdated.next().getValue());
        }
        users = destEM.createQuery("SELECT s FROM UserProfiles s", UserProfiles.class).getResultList();
        transformedData = eltFactoryTransformation.getTransformation("coinvestigator").TransformData(sourceData, usersMap, applicatinosMap, univerisityMap);

        for (CoInvestigators destEntity : transformedData) {
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
