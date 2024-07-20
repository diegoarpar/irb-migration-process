package com.irb.migration.service.ETL;

import com.irb.migration.entity.from.FScreening;
import com.irb.migration.entity.from.FUserDetails;
import com.irb.migration.entity.to.AspNetUsers;
import com.irb.migration.entity.to.Screenings;
import com.irb.migration.entity.to.UserProfiles;
import com.irb.migration.service.transforms.ELTFactoryTransformation;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ETLScreening {

    @Inject
    public ELTFactoryTransformation eltFactoryTransformation;

    EntityManagerFactory sourceEMF = Persistence.createEntityManagerFactory("sourcePU");
    EntityManager sourceEM = sourceEMF.createEntityManager();


    EntityManagerFactory destEMF = Persistence.createEntityManagerFactory("destPUR");
    EntityManager destEM = destEMF.createEntityManager();


    public boolean StartETL() {

        List<FScreening> sourceData = sourceEM.createQuery("SELECT s FROM FScreening s", FScreening.class).getResultList();
        List<AspNetUsers> users = destEM.createQuery("SELECT s FROM AspNetUsers s", AspNetUsers.class).getResultList();
        Map<String, AspNetUsers> usersMap = users.stream().collect(Collectors.toMap(aspNetUsers -> aspNetUsers.NormalizedEmail , aspNetUsers -> aspNetUsers));
        users = null;
        // Transform data
        List<Screenings> transformedData = eltFactoryTransformation.getTransformation("screening").TransformData(sourceData, usersMap);

        // Load data into destination
        destEM.getTransaction().begin();
        for (Screenings destEntity : transformedData) {
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
