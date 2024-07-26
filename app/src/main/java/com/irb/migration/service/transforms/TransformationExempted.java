package com.irb.migration.service.transforms;

import com.irb.migration.entity.from.FExempted;
import com.irb.migration.entity.from.FExpedited;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.entity.to.NonStandards;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransformationExempted implements IETLTransformation<NonStandards, FExempted> {

    @Inject
    public Helper helper;
    public List<NonStandards> TransformData(List<FExempted> sourceData) {
        return List.of();
    }

    @Override
    public List<NonStandards> TransformData(List<FExempted> origin, Map... data) {
        return origin.stream().map(source -> {

            IrbApplications application = (IrbApplications) data[0].get(source.application_id.toUpperCase());
            if (application == null || !"Exempted".equalsIgnoreCase(application.TypeOfReview)) {
                return null;
            }
            NonStandards nonStandards = new NonStandards();
            nonStandards.Category = helper.toCategory(source.que1);
            nonStandards.Reason = source.que2;
            nonStandards.IrbApplicationId = application;
            nonStandards.UserId = application.UserId;
            nonStandards.CreatedDate = application.CreatedDate;
            nonStandards.UpdatedDate = application.CreatedDate;
            return nonStandards;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }



}
