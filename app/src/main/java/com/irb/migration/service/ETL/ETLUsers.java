package com.irb.migration.service.ETL;

import com.irb.migration.entity.from.FApplicationFormBasic;
import com.irb.migration.entity.from.FCoinvestigator;
import com.irb.migration.entity.from.FUserDetails;
import com.irb.migration.entity.to.Universities;
import com.irb.migration.entity.to.UserProfiles;
import com.irb.migration.service.transforms.ELTFactoryTransformation;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ETLUsers implements IETL{

    @Inject
    public ELTFactoryTransformation eltFactoryTransformation;

    EntityManagerFactory sourceEMF = Persistence.createEntityManagerFactory("sourcePU");
    EntityManager sourceEM = sourceEMF.createEntityManager();


    EntityManagerFactory destEMF = Persistence.createEntityManagerFactory("destPU");
    EntityManager destEM = destEMF.createEntityManager();

    public boolean StartETL() {

    List<FUserDetails> sourceData = sourceEM.createQuery("SELECT s FROM FUserDetails s", FUserDetails.class).getResultList();
    List<FApplicationFormBasic> sourceDataApp = sourceEM.createQuery("SELECT s FROM FApplicationFormBasic s", FApplicationFormBasic.class).getResultList();
    List<FCoinvestigator> sourceDataCo = sourceEM.createQuery("SELECT s FROM FCoinvestigator s", FCoinvestigator.class).getResultList();
    // Transform data
    List<UserProfiles> transformedData = eltFactoryTransformation.getTransformation("user").TransformData(sourceData);
    Map<String, UserProfiles> usersMap = new HashMap<>();
    Map<String, UserProfiles> usersMapPersisted = new HashMap<>();
    Universities universities = new Universities();
    // Load data into destination
    destEM.getTransaction().begin();
    for (UserProfiles destEntity : transformedData) {
        if (!usersMapPersisted.containsKey(destEntity.UserId.NormalizedEmail)) {
            destEM.persist(destEntity);
        }
        usersMap.putIfAbsent(destEntity.UserId.NormalizedEmail, destEntity);
        usersMapPersisted.putIfAbsent(destEntity.UserId.NormalizedEmail, destEntity);
        universities = destEntity.University;
    }

    Map<String, Universities> univerisityMap = Collections.singletonMap("gannon", universities);

    transformedData = eltFactoryTransformation.getTransformation("user_app").TransformData(sourceDataApp, usersMap, univerisityMap);
    for (UserProfiles destEntity : transformedData) {
        if (!usersMapPersisted.containsKey(destEntity.UserId.NormalizedEmail)) {
            destEM.persist(destEntity);
        }
        usersMap.putIfAbsent(destEntity.UserId.NormalizedEmail, destEntity);
        usersMapPersisted.putIfAbsent(destEntity.UserId.NormalizedEmail, destEntity);
    }

    transformedData = eltFactoryTransformation.getTransformation("user_co").TransformData(sourceDataCo, usersMap, univerisityMap);
    for (UserProfiles destEntity : transformedData) {
        if (!usersMapPersisted.containsKey(destEntity.UserId.NormalizedEmail)) {
            destEM.persist(destEntity);
        }
        usersMap.putIfAbsent(destEntity.UserId.NormalizedEmail, destEntity);
        usersMapPersisted.putIfAbsent(destEntity.UserId.NormalizedEmail, destEntity);
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
