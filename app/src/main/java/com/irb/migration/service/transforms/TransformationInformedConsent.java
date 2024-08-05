package com.irb.migration.service.transforms;

import com.irb.migration.entity.from.FDataHandling;
import com.irb.migration.entity.to.*;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransformationDataHandling implements IETLTransformation<DataHandling, FDataHandling> {

    @Inject
    public Helper helper;
    public List<DataHandling> TransformData(List<FDataHandling> sourceData) {
        return List.of();
    }

    @Override
    public List<DataHandling> TransformData(List<FDataHandling> origin, Map... data) {
        return origin.stream().map(source -> {

            IrbApplications application = (IrbApplications) data[1].get(source.application_id.toUpperCase());
            if (application == null) {
                return null;
            }
            DataHandling dataHandling = new DataHandling();

            dataHandling.IrbApplicationId = application;
            dataHandling.UserId = application.UserId;
            dataHandling.CollectMethod = source.data_collect_method; //meed modify @TODO
            dataHandling.CollectMethodOther = source.data_collect_method + " " +source.field53other;
            dataHandling.Identifier = "yes".equalsIgnoreCase(source.data_collect_identi)? 1: 0;
            dataHandling.Analysis = "yes".equalsIgnoreCase(source.data_retain_identi_analysis)? 1: 0;
            dataHandling.Reporting = "yes".equalsIgnoreCase(source.data_retain_identi_reporting)? 1: 0;
            dataHandling.Disseminated = source.research_dissem_sub;
            dataHandling.Field58a = source.storage_arrang;
            dataHandling.Field58b = source.acc_person;
            dataHandling.Field58c = source.sch_mthd_even_destruct;
            dataHandling.Field58d = source.sch_mthd_raw_data;
            dataHandling.Field58e = source.sch_mthd_even_destruct;
            dataHandling.CreatedDate = application.SubmittedDate;
            dataHandling.UpdatedDate = application.SubmittedDate;

            return dataHandling;
        }).filter(Objects::nonNull).collect(Collectors.toList());

    }




}
