package com.irb.migration.service.ETL;

import com.irb.migration.entity.from.FApplicationFormBasic;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.entity.to.Universities;
import com.irb.migration.entity.to.UserProfiles;
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

public class ETLApplications implements IETL{

    @Inject
    public ELTFactoryTransformation eltFactoryTransformation;

    public boolean StartETL() {
        EntityManagerFactory sourceEMF = Persistence.createEntityManagerFactory("sourcePU");
        EntityManager sourceEM = sourceEMF.createEntityManager();


        EntityManagerFactory destEMF = Persistence.createEntityManagerFactory("destPUR");
        EntityManager destEM = destEMF.createEntityManager();


        // Extract data from source
        List<FApplicationFormBasic> sourceData = sourceEM.createQuery("SELECT s FROM FApplicationFormBasic s", FApplicationFormBasic.class).getResultList();
        List<UserProfiles> users = destEM.createQuery("SELECT s FROM UserProfiles s", UserProfiles.class).getResultList();
        List<Universities> universities = destEM.createQuery("SELECT s FROM Universities s", Universities.class).getResultList();
        Map<String, Universities> univerisityMap = Collections.singletonMap("gannon", universities.get(0));
        Map<String, UserProfiles> usersMap = users.stream().collect(Collectors.toMap(aspNetUsers -> aspNetUsers.UserId.NormalizedEmail, aspNetUsers -> aspNetUsers));
        users = null;
        // Transform data
        List<IrbApplications> transformedData = eltFactoryTransformation.getTransformation("application").TransformData(sourceData, usersMap, univerisityMap);

        // Load data into destination
        destEM.getTransaction().begin();

        Iterator<Map.Entry<String, UserProfiles>> userUpdated = usersMap.entrySet().iterator();
        while (userUpdated.hasNext()) {
            destEM.persist(userUpdated.next().getValue());
        }
        users = destEM.createQuery("SELECT s FROM UserProfiles s", UserProfiles.class).getResultList();
        usersMap = users.stream().collect(Collectors.toMap(aspNetUsers -> aspNetUsers.UserId.NormalizedEmail, aspNetUsers -> aspNetUsers));

        transformedData = eltFactoryTransformation.getTransformation("application").TransformData(sourceData, usersMap, univerisityMap);

        for (IrbApplications destEntity : transformedData) {
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
