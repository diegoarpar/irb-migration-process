package com.irb.migration.service.ETL;

import com.irb.migration.entity.from.FUserDetails;
import com.irb.migration.entity.to.UserProfiles;
import com.irb.migration.service.transforms.ELTFactoryTransformation;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ETLUsers {

    @Inject
    public ELTFactoryTransformation eltFactoryTransformation;

    EntityManagerFactory sourceEMF = Persistence.createEntityManagerFactory("sourcePU");
    EntityManager sourceEM = sourceEMF.createEntityManager();


    EntityManagerFactory destEMF = Persistence.createEntityManagerFactory("destPU");
    EntityManager destEM = destEMF.createEntityManager();

    public boolean StartETL() {

    List<FUserDetails> sourceData = sourceEM.createQuery("SELECT s FROM FUserDetails s", FUserDetails.class).getResultList();

    // Transform data
    List<UserProfiles> transformedData = eltFactoryTransformation.getTransformation("user").TransformData(sourceData);

    // Load data into destination
    destEM.getTransaction().begin();
    for (UserProfiles destEntity : transformedData) {
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
