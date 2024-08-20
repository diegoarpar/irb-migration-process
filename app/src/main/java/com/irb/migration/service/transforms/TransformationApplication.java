package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FApplicationFormBasic;
import com.irb.migration.entity.to.*;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransformationApplication implements IETLTransformation<IrbApplications, FApplicationFormBasic> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransformationApplication.class.getName());

    public List<IrbApplications> TransformData(List<FApplicationFormBasic> sourceData) {

        return null;
    }

    @Inject
    public Helper helper;
    @Override
    public List<IrbApplications> TransformData(List<FApplicationFormBasic> origin, Map... data) {
        return origin.stream().map(source -> {
            IrbApplications  application = new IrbApplications();
            AspNetUsers userProfiles = (AspNetUsers) data[0].get(source.gu_email.toUpperCase());
            if (userProfiles == null) {
                userProfiles = new AspNetUsers();
                userProfiles.UserName = source.gu_email;
                userProfiles.PhoneNumberConfirmed = Strings.isNullOrEmpty(source.telephone)? 0 : 1;
                userProfiles.PhoneNumber = source.telephone;
                userProfiles.Email = source.gu_email;
                userProfiles.EmailConfirmed = 1;
                userProfiles.NormalizedEmail = source.gu_email.toUpperCase();
                userProfiles.NormalizedUserName = source.gu_email.toUpperCase();
                userProfiles.AccessFailedCount = 0;
                userProfiles.LockoutEnabled = 1;
                userProfiles.TwoFactorEnabled = 0;
                userProfiles.SecurityStamp =  helper.generateRandomStamp();

                data[0].put(userProfiles.NormalizedEmail, userProfiles);
                LOGGER.info("MIGRATION: New User " + source.gu_email);
            }
            application.UserId = userProfiles;
            application.Title = source.title_of_research;
            application.Description = source.description;
            application.TypeOfReview = getTypeOfReview(source.typeofreview);
            application.ApplicationCode = source.application_id;
            application.AppStatus = getStatus(source.date_of_submission, source.editable_app, source.decision_date, source.factdtlvalidate, source.title_of_research, source.irbappstatus);
            application.SubmittedDate = helper.toDateSlash(source.date_of_submission);
            application.CreatedDate = helper.toDateSlash(source.date_of_submission);
            application.IrbDate = helper.toDateSlash(source.decision_date);
            application.IrbStatus = getIrbStatus(source.irbappstatus, source.factdtlvalidate);
            application.IrbExpireDate = helper.toDateSlash(source.irb_approval_expires);
            application.Completion  = 0;
            return application;
        }).collect(Collectors.toList());
    }


    private String getStatus(String typeofreview, String editable_app,
                             String decisionDate, String facultyValidate, String title,
                            String irbStatus) {

        if ("submited".equalsIgnoreCase(irbStatus)) {
            return "Submitted";
        }
        if ("approved with recommendations".equalsIgnoreCase(irbStatus)) {
            return "Submitted";
        }

        if ("approved".equalsIgnoreCase(irbStatus)) {
            return "Submitted";
        }

        if (Strings.isNullOrEmpty(typeofreview) || "yes".equalsIgnoreCase(editable_app)
                || (Strings.isNullOrEmpty(decisionDate) && "pending".equalsIgnoreCase(facultyValidate))
            || Strings.isNullOrEmpty(title)) {
            return "Editing";
        }

        if ("rejected".equalsIgnoreCase(facultyValidate)) {
            return "Rejected";
        }

        if ( (Strings.isNullOrEmpty(decisionDate) && "approved".equalsIgnoreCase(facultyValidate))) {
            return "SponsorApproved";
        }

        return "Submitted";
    }
    private String getIrbStatus(String irbStatus, String faculty) {
        switch (irbStatus) {
            case "approved": return "Approved";
            case "approved with recommendations": return "Approved with recommendations";
            case "rejected": return "Rejected";
            case "submited": return "Under Review";
        }

        if ("reject".equalsIgnoreCase(faculty)) {
            return "Rejected";
        }

        return null;
    }


    private String getTypeOfReview(String typeofreview) {
        if (Strings.isNullOrEmpty(typeofreview)) {
            return "";

        }

        if (typeofreview.contains("Expedited")) {
            return "Expedited";

        }
        if (typeofreview.contains("Standard")) {
            return "Standard";

        }
        return "Exempted";
    }
}
