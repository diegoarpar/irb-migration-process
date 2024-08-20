package com.irb.migration.service.transforms;

import com.irb.migration.entity.from.FEmailNotification;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.entity.to.TransactionLogs;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransformationTransactionLogsEmail implements IETLTransformation<TransactionLogs, FEmailNotification> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransformationTransactionLogsEmail.class.getName());


    @Inject
    public Helper helper;
    public List<TransactionLogs> TransformData(List<FEmailNotification> sourceData) {
        return List.of();
    }

    @Override
    public List<TransactionLogs> TransformData(List<FEmailNotification> origin, Map... data) {
        return origin.stream().map(source -> {

            IrbApplications app = (IrbApplications) data[0].get(source.application_id != null? source.application_id.toUpperCase(): "");
            TransactionLogs transactionLogs = new TransactionLogs();
            transactionLogs.IrbApplicationId = app != null && !Objects.isNull(app.Id)? app.Id : -1;
            transactionLogs.UserId = app != null && !Objects.isNull(app.Id)? app.UserId.Id: "-1";
            transactionLogs.Action = "Sent_Email";
            transactionLogs.EventDate = source.sentdate;
            transactionLogs.EventName = "Email";
            transactionLogs.Info = String.format("FullName:%s, Exp Date:%s, To:%s, Subject:%s, Body:%s, Status:%s, type:%s, sentDesc:%s"
                                                , source.fullname, source.expdate, source.toemail, source.tosubject, source.tosubject, source.status, source.type, source.sentdesc);
            return transactionLogs;
        }).collect(Collectors.toList());
    }


}
