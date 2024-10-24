package com.irb.migration.service.ETL;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FDocuments;
import com.irb.migration.entity.to.AspNetUsers;
import com.irb.migration.entity.to.Documents;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.service.transforms.ELTFactoryTransformation;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ETLDocuments implements IETL {

    @Inject
    public ELTFactoryTransformation eltFactoryTransformation;

    public boolean StartETL() {
        EntityManagerFactory sourceEMF = Persistence.createEntityManagerFactory("sourcePU");
        EntityManager sourceEM = sourceEMF.createEntityManager();


        EntityManagerFactory destEMF = Persistence.createEntityManagerFactory("destPUR");
        EntityManager destEM = destEMF.createEntityManager();

        // Extract data from source
        List<FDocuments> sourceData = sourceEM.createQuery("SELECT NEW FDocuments (s.application_id, s.file_name, s.content_type, s.notes, s.hyperlink1, s.hyperlink2, s.hyperlink3) FROM FDocuments s", FDocuments.class).getResultList();
        List<AspNetUsers> users = destEM.createQuery("SELECT s FROM AspNetUsers s", AspNetUsers.class).getResultList();
        List<IrbApplications> applications = destEM.createQuery("SELECT s FROM IrbApplications s", IrbApplications.class).getResultList();

        Map<String, AspNetUsers> usersMap = users.stream().collect(Collectors.toMap(aspNetUsers -> aspNetUsers.NormalizedEmail , aspNetUsers -> aspNetUsers));
        Map<String, IrbApplications> applicatinosMap = applications.stream().collect(Collectors.toMap(application -> application.ApplicationCode, application -> application));
        applications = null;
        users = null;
        // Transform data
        List<Documents> transformedData = eltFactoryTransformation.getTransformation("documents").TransformData(sourceData, usersMap, applicatinosMap);
        usersMap = null;
        applicatinosMap = null;
        // Load data into destination

        destEM.getTransaction().begin();
        for (Documents destEntity : transformedData) {
            Documents newDoc = new Documents();
            newDoc.IrbApplicationId = destEntity.IrbApplicationId;
            newDoc.UserId = destEntity.UserId;
            newDoc.Type = destEntity.Type;
            newDoc.Name = destEntity.Name;
            newDoc.Url = destEntity.Url;
            newDoc.CreatedDate = destEntity.CreatedDate;
            newDoc.UpdatedDate = destEntity.UpdatedDate;
            newDoc.Classification = destEntity.Classification;
            destEM.persist(newDoc);
        }
        destEM.flush();
        destEM.clear();
        destEM.getTransaction().commit();

        List<Documents> documents = destEM.createQuery("SELECT s FROM Documents s", Documents.class).getResultList();

        destEM.getTransaction().begin();
        for (Documents newDoc : documents) {
            List<FDocuments> docs = sourceEM.createQuery("SELECT new FDocuments(s.data) FROM FDocuments s where s.application_id = :appId and s.file_name = :filename", FDocuments.class)
                    .setParameter("appId", newDoc.IrbApplicationId.ApplicationCode)
                    .setParameter("filename", newDoc.Name)
                    .getResultList();
            byte[] data = null;
            for (FDocuments fd : docs) {
                if (!Objects.isNull(fd.data)) {
                    data = fd.data;
                }
            }
            if (data == null && Strings.isNullOrEmpty(newDoc.Url)) {

                continue;
            }

            CriteriaBuilder cb = destEM.getCriteriaBuilder();

            // create update
            CriteriaUpdate<Documents> update = cb.
                    createCriteriaUpdate(Documents.class);

            // set the root class
            Root e = update.from(Documents.class);

            // set update and where clause
            update.set("data", data);
            update.where(cb.equal(e.get("Id"), newDoc.Id));

            // perform update
            destEM.createQuery(update).executeUpdate();
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
