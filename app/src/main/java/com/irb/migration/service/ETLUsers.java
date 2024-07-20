package com.irb.migration.service;

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

    EntityManagerFactory destEMFR = Persistence.createEntityManagerFactory("destPUR");

    EntityManager destEMR = destEMFR.createEntityManager();
    public boolean StartETL() {


        try {
            // Extract data from source
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
        } catch(Exception e) {
            return false;
        }  finally {
            sourceEM.close();
            sourceEMF.close();
            destEM.close();
            destEMF.close();
        }
        return true;
    }


}
