package com.irb.migration.service.ETL;

import com.irb.migration.entity.from.FChangeUserType;
import com.irb.migration.entity.from.FEmailNotification;
import com.irb.migration.entity.to.AspNetUsers;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.entity.to.TransactionLogs;
import com.irb.migration.service.transforms.ELTFactoryTransformation;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ETLTransactionsLogsChangeUserType implements IETL{

    @Inject
    public ELTFactoryTransformation eltFactoryTransformation;

    public boolean StartETL() {
        EntityManagerFactory sourceEMF = Persistence.createEntityManagerFactory("sourcePU");
        EntityManager sourceEM = sourceEMF.createEntityManager();


        EntityManagerFactory destEMF = Persistence.createEntityManagerFactory("destPUR");
        EntityManager destEM = destEMF.createEntityManager();

        // Extract data from source
        List<FChangeUserType> sourceData = sourceEM.createQuery("SELECT s FROM FChangeUserType s", FChangeUserType.class).getResultList();
        List<AspNetUsers> users = destEM.createQuery("SELECT s FROM AspNetUsers s", AspNetUsers.class).getResultList();

        Map<String, AspNetUsers> usersMap = users.stream().collect(Collectors.toMap(data -> data.NormalizedEmail, data -> data));
        users = null;

        // Transform data
        List<TransactionLogs> transformedData = eltFactoryTransformation.getTransformation("changeuser").TransformData(sourceData, usersMap);

        // Load data into destination
        destEM.getTransaction().begin();
        for (TransactionLogs destEntity : transformedData) {
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
