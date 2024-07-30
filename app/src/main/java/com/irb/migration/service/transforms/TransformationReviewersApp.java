package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FApplicationFormBasic;
import com.irb.migration.entity.to.AspNetUsers;
import com.irb.migration.entity.to.FacultySponsors;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.entity.to.Reviewers;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransformationReviewersApp implements IETLTransformation<Reviewers, FApplicationFormBasic> {

    @Inject
    public Helper helper;
    public List<Reviewers> TransformData(List<FApplicationFormBasic> sourceData) {
        return List.of();
    }

    @Override
    public List<Reviewers> TransformData(List<FApplicationFormBasic> origin, Map... data) {
        return origin.stream().map(source -> {
            if (Strings.isNullOrEmpty(source.assignedstaff) || "none".equalsIgnoreCase(source.assignedstaff)) {
                return null;
            }
            IrbApplications application = (IrbApplications) data[1].get(source.application_id.toUpperCase());
            AspNetUsers staff = (AspNetUsers) data[0].get(source.assignedstaff.toUpperCase());


            Reviewers reviewer = new Reviewers();
            reviewer.CreatedDate = application.SubmittedDate;
            reviewer.UserId = staff.Id;
            reviewer.IrbApplicationId = application.Id;
            reviewer.Status = "Assigned";
            reviewer.DecisionDate = application.IrbDate;
            reviewer.Description = source.approve_reason;
            reviewer.Signature = source.assignedstaff;
            reviewer.Decision = application.IrbStatus;

            return reviewer;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }


}
