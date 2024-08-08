package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FDataHandling;
import com.irb.migration.entity.from.FNotificationForm;
import com.irb.migration.entity.to.AspNetUsers;
import com.irb.migration.entity.to.DataHandling;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.entity.to.NotificationForms;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;

import java.util.*;
import java.util.stream.Collectors;

public class TransformationNotificationForms implements IETLTransformation<NotificationForms, FNotificationForm> {

    @Inject
    public Helper helper;
    public List<NotificationForms> TransformData(List<FNotificationForm> sourceData) {
        return List.of();
    }

    @Override
    public List<NotificationForms> TransformData(List<FNotificationForm> origin, Map... data) {
        List<String> ku = List.of("SMK", "Kubiak", "SK");
        List<String> woke = List.of("J");
        String kuEmail = ((Map<String, AspNetUsers>) data[0]).keySet().stream().filter(s -> s.contains("UBIAK")).findFirst().orElse(null);
        String wokeEmail = ((Map<String, AspNetUsers>) data[0]).keySet().stream().filter(s -> s.contains("WOKE")).findFirst().orElse(null);
        AspNetUsers kuUser = ((Map<String, AspNetUsers>) data[0]).get(kuEmail);
        AspNetUsers kuWoke = ((Map<String, AspNetUsers>) data[0]).get(wokeEmail);
        return origin.stream().map(source -> {
            if (Strings.isNullOrEmpty(source.application_id) || Strings.isNullOrEmpty(source.date_submited)) {
                return null;
            }

            IrbApplications application = (IrbApplications) data[1].get(source.application_id.toUpperCase());
            if (application == null) {
                return null;
            }
            String userKuFound = null;
            String userWokeFound = null;
            if (!Strings.isNullOrEmpty(source.received_by_irb)) {
                userKuFound = ku.stream().filter(s ->  source.received_by_irb.contains(s)).findFirst().orElse(null);
                userWokeFound = woke.stream().filter(s -> source.received_by_irb.contains(s)).findFirst().orElse(null);
            }


            NotificationForms notificationForms = new NotificationForms();

            notificationForms.IrbApplicationId = application;
            notificationForms.UserId = application.UserId;

            notificationForms.CreatedDate = helper.toDateSlash(source.date_submited);
            notificationForms.NamePrinted = source.received_by_irb;
            notificationForms.ReceivedBy = source.received_by_irb;
            notificationForms.Remarks = source.check_box_other;
            notificationForms.Position = source.position;
            notificationForms.Observation = source.reason;
            notificationForms.ReceivedByDate = helper.toDateSlash(source.received_by_irb_on);
            notificationForms.UpdatedDate = helper.toDateSlash(source.date_approved) != null? helper.toDateSlash(source.date_approved): notificationForms.CreatedDate;

            if (!Strings.isNullOrEmpty(source.date_submited)) {
                notificationForms.Status = "Submitted";
            }
            if ("approved".equalsIgnoreCase(source.app_status)) {
                notificationForms.Status = "Approved";
            }

            if ("reassign".equalsIgnoreCase(source.app_status)) {
                notificationForms.Status = "Rejected";
            }

            if ("anychanges".equalsIgnoreCase(source.check_box)) {
                notificationForms.Type = "Any changes";
            }

            if ("studyiscomplete".equalsIgnoreCase(source.check_box)) {
                notificationForms.Type = "Study is not complete";
            }
            if ("studyisdiscontinued".equalsIgnoreCase(source.check_box)) {
                notificationForms.Type = "Study is discontinued";
            }
            if ("studyiscomplete".equalsIgnoreCase(source.check_box)) {
                notificationForms.Type = "Study is complete";
            }

            if (!Strings.isNullOrEmpty(userWokeFound)) {
                notificationForms.AssignedUserId = kuWoke.Id;
            }

            if (!Strings.isNullOrEmpty(userKuFound)) {
                notificationForms.AssignedUserId = kuUser.Id;
            }

            return notificationForms;
        }).filter(Objects::nonNull).collect(Collectors.toList());

    }




}
