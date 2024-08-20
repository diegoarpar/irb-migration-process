package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FStandardDecision;
import com.irb.migration.entity.to.AspNetUsers;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.entity.to.StandardVotes;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransformationVote implements IETLTransformation<StandardVotes, FStandardDecision> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransformationVote.class.getName());


    @Inject
    public Helper helper;
    public List<StandardVotes> TransformData(List<FStandardDecision> sourceData) {
        return List.of();
    }

    @Override
    public List<StandardVotes> TransformData(List<FStandardDecision> origin, Map... data) {
        return origin.stream().map(source -> {
            if (Strings.isNullOrEmpty(source.id.application_id)) {
                return null;
            }
            IrbApplications application = (IrbApplications) data[1].get(source.id.application_id.toUpperCase());
            AspNetUsers staff = (AspNetUsers) data[0].get(source.id.irbstaffemail.toUpperCase());
            if (application == null) {
                return null;
            }

            if (staff == null) {
                return null;
            }
            StandardVotes vote = new StandardVotes();
            vote.CreatedDate = application.SubmittedDate;
            vote.UserId = staff.Id;
            vote.IrbApplicationId = application.Id;
            vote.UpdatedDate = application.IrbDate;
            vote.Decision = "approve".equalsIgnoreCase(source.decision)? "Approved": "Rejected";
            vote.Reason = source.reason;


            return vote;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }


}
