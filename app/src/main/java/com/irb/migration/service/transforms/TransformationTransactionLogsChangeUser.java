package com.irb.migration.service.transforms;

import com.irb.migration.entity.from.FChangeUserType;
import com.irb.migration.entity.to.AspNetUsers;
import com.irb.migration.entity.to.TransactionLogs;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransformationTransactionLogsChangeUser implements IETLTransformation<TransactionLogs, FChangeUserType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransformationTransactionLogsChangeUser.class.getName());


    @Inject
    public Helper helper;
    public List<TransactionLogs> TransformData(List<FChangeUserType> sourceData) {
        return List.of();
    }

    @Override
    public List<TransactionLogs> TransformData(List<FChangeUserType> origin, Map... data) {
        return origin.stream().map(source -> {

            AspNetUsers user = (AspNetUsers) data[0].get(source.gu_email != null? source.gu_email.toUpperCase(): "");
            TransactionLogs transactionLogs = new TransactionLogs();
            transactionLogs.IrbApplicationId = -1;
            transactionLogs.UserId = user != null && !Objects.isNull(user.Id)? user.Id: "-1";
            transactionLogs.Action = "Change_User";
            transactionLogs.EventDate = helper.toDateSlash(source.request_date);
            transactionLogs.EventName = "Change_User";
            transactionLogs.Info = String.format("Name:%s %s, Role:%s, Requested Role:%s, New Role:%s, Decision:%s, User Comment:%s, Admin Comment:%s, Decision Date:%s"
                                                , source.firstname, source.lastname, source.original_usertype, source.requested_usertype, source.new_usertype, source.request_decision, source.user_comment, source.admin_comment, source.decision_date);
            return transactionLogs;
        }).collect(Collectors.toList());
    }


}
