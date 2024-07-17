package com.irb.migration.service;

import com.irb.migration.entity.from.ApplicationFormBasic;
import com.irb.migration.entity.from.UserDetails;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.entity.to.UserProfiles;
import com.irb.migration.service.transforms.ELTFactoryTransformation;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ETLApplications {

    @Inject
    public ELTFactoryTransformation eltFactoryTransformation;

    public boolean StartETL() {
        EntityManagerFactory sourceEMF = Persistence.createEntityManagerFactory("sourcePU");
        EntityManager sourceEM = sourceEMF.createEntityManager();


        EntityManagerFactory destEMF = Persistence.createEntityManagerFactory("destPUR");
        EntityManager destEM = destEMF.createEntityManager();

        try {
            // Extract data from source
            List<ApplicationFormBasic> sourceData = sourceEM.createQuery("SELECT s FROM ApplicationFormBasic s", ApplicationFormBasic.class).getResultList();

            // Transform data
            List<IrbApplications> transformedData = eltFactoryTransformation.getTransformation(sourceData.get(0)).TransformData(sourceData);

            // Load data into destination
            destEM.getTransaction().begin();
            for (IrbApplications destEntity : transformedData) {
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
