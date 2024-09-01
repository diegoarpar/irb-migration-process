package com.irb.migration.service.ETL;

import com.irb.migration.entity.from.FChangeUserType;
import com.irb.migration.entity.from.FEmailNotification;
import com.irb.migration.entity.to.*;
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
        List<IrbApplications> irbApplications = destEM.createQuery("SELECT s FROM IrbApplications s", IrbApplications.class).getResultList();
        List<StandardVotes> standardVotes = destEM.createQuery("SELECT s FROM StandardVotes s", StandardVotes.class).getResultList();
        List<Reviewers> reviewers = destEM.createQuery("SELECT s FROM Reviewers s", Reviewers.class).getResultList();
        List<FacultySponsors> facultySponsors = destEM.createQuery("SELECT s FROM FacultySponsors s", FacultySponsors.class).getResultList();
        List<ReviewNotes> reviewNotes = destEM.createQuery("SELECT s FROM ReviewNotes s", ReviewNotes.class).getResultList();

        Map<String, AspNetUsers> usersMap = users.stream().collect(Collectors.toMap(data -> data.NormalizedEmail, data -> data));
        Map<String, IrbApplications> applicationMap = irbApplications.stream().collect(Collectors.toMap(data -> data.ApplicationCode, data -> data));
        Map<String, Reviewers> reviewerMap = reviewers.stream().collect(Collectors.toMap(data -> data.IrbApplicationId + "-" + data.Id, data -> data, (data1, data2) -> data2));
        Map<String, StandardVotes> standardVotesMap = standardVotes.stream().collect(Collectors.toMap(data -> data.IrbApplicationId + "-" + data.Id, data -> data, (data1, data2) -> data2));
        Map<String, FacultySponsors> facultySponsorsMap = facultySponsors.stream().collect(Collectors.toMap(data -> data.IrbApplicationId + "-" + data.Id, data -> data));
        Map<String, ReviewNotes> reviewNotesMap = reviewNotes.stream().collect(Collectors.toMap(data -> data.IrbApplicationId + "-" + data.Id, data -> data, (data1, data2) -> data2));
        users = null;

        // Transform data
        List<TransactionLogs> transformedData = eltFactoryTransformation.getTransformation("changeuser").TransformData(sourceData, usersMap, applicationMap, reviewerMap, standardVotesMap, facultySponsorsMap, reviewNotesMap);

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
