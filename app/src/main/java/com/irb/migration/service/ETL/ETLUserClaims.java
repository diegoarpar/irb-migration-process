package com.irb.migration.service.ETL;

import com.irb.migration.entity.from.FApplicationFormBasic;
import com.irb.migration.entity.from.FUserDetails;
import com.irb.migration.entity.to.AspNetUserClaims;
import com.irb.migration.entity.to.AspNetUsers;
import com.irb.migration.entity.to.FacultySponsors;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.service.transforms.ELTFactoryTransformation;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ETLUserClaims implements IETL{

    @Inject
    public ELTFactoryTransformation eltFactoryTransformation;

    public boolean StartETL() {
        EntityManagerFactory sourceEMF = Persistence.createEntityManagerFactory("sourcePU");
        EntityManager sourceEM = sourceEMF.createEntityManager();


        EntityManagerFactory destEMF = Persistence.createEntityManagerFactory("destPUR");
        EntityManager destEM = destEMF.createEntityManager();

        // Extract data from source
        List<FUserDetails> sourceData = sourceEM.createQuery("SELECT s FROM FUserDetails s", FUserDetails.class).getResultList();
        List<AspNetUsers> users = destEM.createQuery("SELECT s FROM AspNetUsers s", AspNetUsers.class).getResultList();

        Map<String, AspNetUsers> usersMap = users.stream().collect(Collectors.toMap(aspNetUsers -> aspNetUsers.NormalizedEmail , aspNetUsers -> aspNetUsers));
        users = null;
        // Transform data
        List<AspNetUserClaims> transformedData = eltFactoryTransformation.getTransformation("userclaim").TransformData(sourceData, usersMap);

        // Load data into destination
        destEM.getTransaction().begin();
        for (AspNetUserClaims destEntity : transformedData) {
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
