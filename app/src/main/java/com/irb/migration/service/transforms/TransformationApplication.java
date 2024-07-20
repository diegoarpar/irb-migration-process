package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FApplicationFormBasic;
import com.irb.migration.entity.to.*;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransformationApplication implements ETLTransformation<IrbApplications, FApplicationFormBasic> {

    public List<IrbApplications> TransformData(List<FApplicationFormBasic> sourceData) {

        return null;
    }

    @Inject
    public Helper helper;
    @Override
    public List<IrbApplications> TransformData(List<FApplicationFormBasic> origin, Map... data) {
        return origin.stream().map(source -> {
            IrbApplications  application = new IrbApplications();
            application.UserId = (AspNetUsers) data[0].get(source.gu_email.toUpperCase());
            if (application.UserId == null) {
                return null;
            }

            application.Title = source.title_of_research;
            application.Description = source.description;
            application.TypeOfReview = getTypeOfReview(source.typeofreview);
            application.ApplicationCode = source.application_id;
            application.AppStatus = getStatus(source.date_of_submission, source.editable_app);
            application.SubmittedDate = helper.toDateSlash(source.date_of_submission);
            application.CreatedDate = helper.toDateSlash(source.date_of_submission);
            application.IrbDate = helper.toDateSlash(source.decision_date);
            application.IrbStatus = source.irbappstatus;
            application.IrbExpireDate = helper.toDateSlash(source.irb_approval_expires);
            return application;
        }).collect(Collectors.toList());
    }


    private String getStatus(String typeofreview, String editable_app) {
        if (Strings.isNullOrEmpty(typeofreview) || "yes".equalsIgnoreCase(editable_app)) {
            return "Editing";
        }

        return "Submitted";
    }
    private String getIrbStatus(String irbStatus) {
        switch (irbStatus) {
            case "approved": return "Approved";
            case "approved with recommendations": return "Approved with recommendations";
            case "pending": return "Pending";
            case "rejected": return "Rejected";
            case "submited": return "Under Review";
        }

        return null;
    }














    private String getTypeOfReview(String typeofreview) {
        if (Strings.isNullOrEmpty(typeofreview)) {
            return "";

        }

        if (typeofreview.contains("Expedited")) {
            return "expedited";

        }
        if (typeofreview.contains("Standard")) {
            return "Standard";

        }
        return "Exempted";
    }
}
