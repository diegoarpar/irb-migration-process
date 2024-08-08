package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FNotes;
import com.irb.migration.entity.to.AspNetUsers;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.entity.to.ReviewNotes;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;

import java.util.*;

public class TransformationNotes implements IETLTransformation<ReviewNotes, FNotes> {

    @Inject
    public Helper helper;
    public List<ReviewNotes> TransformData(List<FNotes> sourceData) {
        return List.of();
    }

    @Override
    public List<ReviewNotes> TransformData(List<FNotes> origin, Map... data) {
        List<ReviewNotes> reviewNotes = new ArrayList<>();
        for (FNotes source : origin) {
            if (Strings.isNullOrEmpty(source.application_fid) || Strings.isNullOrEmpty(source.user_fid)) {
                continue;
            }

            IrbApplications application = (IrbApplications) data[1].get(source.application_fid.toUpperCase());
            if (application == null) {
                continue;
            }

            AspNetUsers user = (AspNetUsers) data[0].get(source.user_fid.toUpperCase());
            if (user == null) {
                continue;
            }
            int order = 1;
            if (!Strings.isNullOrEmpty(source.notes_subdefinedtl)) {
                reviewNotes.add(getNote("Subject", source.notes_subdefinedtl, user, application, 2));
            }

            if (!Strings.isNullOrEmpty(source.notes_riskfactdtl)) {
                reviewNotes.add(getNote("Risk", source.notes_riskfactdtl, user, application, 3));
            }

            if (!Strings.isNullOrEmpty(source.notes_researchdtl)) {
                reviewNotes.add(getNote("Research Activities", source.notes_researchdtl, user, application, 7 ));
            }

            if (!Strings.isNullOrEmpty(source.notes_consentdtl)) {
                reviewNotes.add(getNote("Informed", source.notes_consentdtl, user, application, 4));
            }

            if (!Strings.isNullOrEmpty(source.notes_datahandlingdtl)) {
                reviewNotes.add(getNote("Data Handlings", source.notes_datahandlingdtl, user, application, 5));
            }

            if (!Strings.isNullOrEmpty(source.notes_text)) {
                reviewNotes.add(getNote("Research", source.notes_text, user, application, 1));
            }

            if (!Strings.isNullOrEmpty(source.notes_uploads)) {
                reviewNotes.add(getNote("Recruitment", source.notes_uploads, user, application, 8));
            }

        }
        return reviewNotes;

    }


    public ReviewNotes getNote(String type, String note, AspNetUsers user, IrbApplications app, Integer order) {
        ReviewNotes reviewNote = new ReviewNotes();

        reviewNote.IrbApplicationId = app;
        reviewNote.UserId = user;
        reviewNote.Type = type;
        reviewNote.Note = note;
        reviewNote.Order = order;

        reviewNote.CreatedDate = app.SubmittedDate != null? app.SubmittedDate: new Date();
        reviewNote.UpdatedDate = reviewNote.CreatedDate;
        return reviewNote;
    }

}
