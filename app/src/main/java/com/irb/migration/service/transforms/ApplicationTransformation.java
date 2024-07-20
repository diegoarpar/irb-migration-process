package com.irb.migration.service.transforms;

import com.irb.migration.entity.from.ApplicationFormBasic;
import com.irb.migration.entity.from.UserDetails;
import com.irb.migration.entity.to.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationTransformation implements ETLTransformation<IrbApplications, ApplicationFormBasic> {

    public List<IrbApplications> TransformData(List<ApplicationFormBasic> sourceData) {

        return sourceData.stream().map(source -> {
            IrbApplications  application = new IrbApplications();
            application.Title = source.title_of_research;
            application.TypeOfReview = source.typeofreview;
            application.ApplicationCode = source.application_id;
            application.AppStatus = source.status;
            //application.SubmittedDate = source.date_of_submission;


            return application;
        }).collect(Collectors.toList());
    }
}
