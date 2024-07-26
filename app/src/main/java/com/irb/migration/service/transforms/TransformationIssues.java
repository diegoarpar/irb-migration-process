package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FApplicationFormBasic;
import com.irb.migration.entity.from.FIssues;
import com.irb.migration.entity.to.AspNetUsers;
import com.irb.migration.entity.to.FacultySponsors;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.entity.to.Issues;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransformationIssues implements IETLTransformation<Issues, FIssues> {

    @Inject
    public Helper helper;
    public List<Issues> TransformData(List<FIssues> sourceData) {
        return List.of();
    }

    @Override
    public List<Issues> TransformData(List<FIssues> origin, Map... data) {
        return origin.stream().map(source -> {

            AspNetUsers user = (AspNetUsers) data[0].get(source.guemailid != null? source.guemailid.toUpperCase(): "");
            AspNetUsers userSolve = (AspNetUsers) data[0].get(source.solved_by != null? source.solved_by.toUpperCase(): "");
            Issues issue = new Issues();
            issue.UserIdReporter = user != null && Strings.isNullOrEmpty(user.Id)? user.Id : "-1";
            issue.Subject = source.subject;
            issue.IrbApplicationId = "-1";
            issue.NotificationFormId = "-1";
            issue.Type = getType(source.bug_type);
            issue.Other = source.bug_type_other;
            issue.SourceUrl = source.source_url;
            issue.Description = source.description;
            issue.Status = "solved".equalsIgnoreCase(source.bug_status)? "Closed": "Open"; //TODO
            issue.DeviceInformation = source.os_name_version + " " + source.browser_name_version;
            issue.ResolutionDescription = source.admin_dev_comments;
            issue.UserIdSolver = userSolve != null && Strings.isNullOrEmpty(userSolve.Id)? userSolve.Id : "-1";
            issue.EventDateCreated = helper.toDateSlash(source.bug_close_date);
            issue.EventDateVisited = issue.EventDateCreated;
            issue.EventDateSolved = issue.EventDateCreated;
            issue.Sent = Objects.nonNull(issue.EventDateCreated)? 1: 0;
            issue.Visited = issue.Sent;

            return issue;
        }).collect(Collectors.toList());
    }

    public String getType(String type) {
        if("Click On Button Nothing Happend".equalsIgnoreCase(type)) {
            return "Click On Button Nothing Happend";
        } else if("Control is not Showing Properly".equalsIgnoreCase(type)) {
            return "Control is not Showing Properly";
        } else if("controlnotshowingproperly".equalsIgnoreCase(type)) {
            return "Control is not Showing Properly";
        } else if("Data is not Loading".equalsIgnoreCase(type)) {
            return "Data is not Loading";
        } else if("Navigation Problem".equalsIgnoreCase(type)) {
            return "Navigation Problem";
        } else if("other".equalsIgnoreCase(type)) {
            return "Others, please specify";
        }
        return type;
    }


}
