package com.irb.migration.service.transforms;

import com.irb.migration.entity.from.FExpedited;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.entity.to.NonStandards;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransformationExpedited implements IETLTransformation<NonStandards, FExpedited> {

    @Inject
    public Helper helper;
    public List<NonStandards> TransformData(List<FExpedited> sourceData) {
        return List.of();
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(TransformationExpedited.class.getName());

    @Override
    public List<NonStandards> TransformData(List<FExpedited> origin, Map... data) {
        return origin.stream().map(source -> {

            IrbApplications application = (IrbApplications) data[0].get(source.application_id.toUpperCase());
            if (application == null || !"expedited".equalsIgnoreCase(application.TypeOfReview)) {
                LOGGER.error("MIGRATION: IRB does not exist when migrate expedited " + source.application_id + " " + (application != null? application.TypeOfReview: ""));
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
