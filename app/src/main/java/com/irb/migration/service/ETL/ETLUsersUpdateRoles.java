package com.irb.migration.service.ETL;

import com.irb.migration.entity.from.FUserDetails;
import com.irb.migration.entity.to.AspNetUserClaims;
import com.irb.migration.entity.to.AspNetUsers;
import com.irb.migration.entity.to.UserProfiles;
import com.irb.migration.service.transforms.ELTFactoryTransformation;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ETLUsersUpdateRoles implements IETL{

    @Inject
    public ELTFactoryTransformation eltFactoryTransformation;

    EntityManagerFactory sourceEMF = Persistence.createEntityManagerFactory("sourcePU");
    EntityManager sourceEM = sourceEMF.createEntityManager();


    EntityManagerFactory destEMF = Persistence.createEntityManagerFactory("destPUR");
    EntityManager destEM = destEMF.createEntityManager();

    public boolean StartETL() {

    List<FUserDetails> sourceData = sourceEM.createQuery("SELECT s FROM FUserDetails s", FUserDetails.class).getResultList();
    List<UserProfiles> userProfiles = destEM.createQuery("SELECT s FROM UserProfiles s", UserProfiles.class).getResultList();
    Map<String, UserProfiles> userProfileMap = userProfiles.stream().collect(Collectors.toMap(aspNetUsersM -> aspNetUsersM.UserId.NormalizedEmail , aspNetUsersM -> aspNetUsersM));
    Map<String, AspNetUsers> netUserMap = new HashMap<>();


        // Transform data
    List<UserProfiles> transformedData = eltFactoryTransformation.getTransformation("updateusers").TransformData(sourceData, userProfileMap);

    Map<String, UserProfiles> usersMapPersisted = new HashMap<>();
    // Load data into destination
    destEM.getTransaction().begin();
    for (UserProfiles destEntity : transformedData) {
        if (!usersMapPersisted.containsKey(destEntity.UserId.NormalizedEmail)) {
            destEM.persist(destEntity);

        }
        usersMapPersisted.putIfAbsent(destEntity.UserId.NormalizedEmail, destEntity);
        netUserMap.putIfAbsent(destEntity.UserId.NormalizedEmail, destEntity.UserId);
    }


    List<AspNetUserClaims> transformedData2 = eltFactoryTransformation.getTransformation("userclaim").TransformData(sourceData, netUserMap);
    for (AspNetUserClaims destEntity : transformedData2) {
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
