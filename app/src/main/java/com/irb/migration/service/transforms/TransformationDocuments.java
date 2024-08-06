package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FDocuments;
import com.irb.migration.entity.to.Documents;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;

import java.util.*;

public class TransformationDocuments implements IETLTransformation<Documents, FDocuments> {

    @Inject
    public Helper helper;
    public List<Documents> TransformData(List<FDocuments> sourceData) {
        return List.of();
    }

    @Override
    public List<Documents> TransformData(List<FDocuments> origin, Map... data) {
        List<Documents> documents = new ArrayList<>();
        for (FDocuments fDoc : origin) {
            IrbApplications application = (IrbApplications) data[1].get(fDoc.application_id.toUpperCase());
            if (application == null) {
                continue;
            }

            if (fDoc.data != null) {
                Documents document1 =  getDocumentBasicInfo(fDoc, application);
                document1.Name = fDoc.file_name;
                document1.Type = fDoc.content_type;
                document1.data = fDoc.data;
                documents.add(document1);
            }

            if (!Strings.isNullOrEmpty(fDoc.hyperlink1 )) {
                Documents document2 =  getDocumentBasicInfo(fDoc, application);
                document2.Name = fDoc.file_name;
                document2.Type = "link";
                document2.Url = fDoc.hyperlink1;
                documents.add(document2);
            }

            if (!Strings.isNullOrEmpty(fDoc.hyperlink2 )) {
                Documents document3 =  getDocumentBasicInfo(fDoc, application);
                document3.Name = fDoc.file_name;
                document3.Type = "link";
                document3.Url = fDoc.hyperlink2;
                documents.add(document3);
            }

            if (!Strings.isNullOrEmpty(fDoc.hyperlink3 )) {
                Documents document4 =  getDocumentBasicInfo(fDoc, application);
                document4.Name = fDoc.file_name;
                document4.Type = "link";
                document4.Url = fDoc.hyperlink3;
                documents.add(document4);
            }

        }
        return documents;

    }

    private Documents getDocumentBasicInfo(FDocuments source, IrbApplications application) {
        Documents document =  new Documents();

        document.IrbApplicationId = application;
        document.UserId = application.UserId;
        document.Name = source.file_name;
        document.Classification = "Research-Activites";

        document.CreatedDate = application.SubmittedDate != null? application.SubmittedDate: new Date();
        document.UpdatedDate = document.CreatedDate;
        return document;
    }


}
