package com.irb.migration.service.transforms;

import com.irb.migration.entity.from.FChangeUserType;
import com.irb.migration.entity.to.*;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class TransformationTransactionLogsChangeUser implements IETLTransformation<TransactionLogs, FChangeUserType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransformationTransactionLogsChangeUser.class.getName());


    @Inject
    public Helper helper;
    public List<TransactionLogs> TransformData(List<FChangeUserType> sourceData) {
        return List.of();
    }

    @Override
    public List<TransactionLogs> TransformData(List<FChangeUserType> origin, Map... data) {
        List<TransactionLogs> logs = new ArrayList<>();
        for (FChangeUserType source : origin) {
            TransactionLogs log = new TransactionLogs();
            AspNetUsers user = (AspNetUsers) data[0].get(source.gu_email != null ? source.gu_email.toUpperCase() : "");
            log.IrbApplicationId = -1;
            log.UserId = user != null && !Objects.isNull(user.Id) ? user.Id : "-1";
            log.Action = "Change_User";
            log.EventDate = helper.toDateSlash(source.request_date);
            log.EventName = "Change_User";
            log.Info = String.format("Name:%s %s, Role:%s, Requested Role:%s, New Role:%s, Decision:%s, User Comment:%s, Admin Comment:%s, Decision Date:%s"
                    , source.firstname, source.lastname, source.original_usertype, source.requested_usertype, source.new_usertype, source.request_decision, source.user_comment, source.admin_comment, source.decision_date);
            logs.add(log);
        }

        Iterator<Map.Entry<String, IrbApplications>> applicationIterator = ((Map<String, IrbApplications>) data[1]).entrySet().iterator();

        while (applicationIterator.hasNext()) {
            IrbApplications application = applicationIterator.next().getValue();
            TransactionLogs logSubmitted = new TransactionLogs();
            logSubmitted.IrbApplicationId = application.Id;
            logSubmitted.UserId = application.UserId.Id;
            logSubmitted.Action = "Application Submitted";
            logSubmitted.EventDate = application.SubmittedDate;
            logSubmitted.EventName = "Application Submitted";
            logSubmitted.Info = "Application Submitted";
            if (logSubmitted.EventDate != null) {
                logs.add(logSubmitted);
            }
        }

        Iterator<Map.Entry<String, Reviewers>> reviewerIterator = ((Map<String, Reviewers>) data[2]).entrySet().iterator();

        while (reviewerIterator.hasNext()) {
            Reviewers reviewer = reviewerIterator.next().getValue();
            TransactionLogs logReview = new TransactionLogs();
            logReview.IrbApplicationId = reviewer.IrbApplicationId;
            if (logReview.IrbApplicationId == null) {
                logReview.IrbApplicationId = -1;
            }
            logReview.UserId = reviewer.UserId;
            logReview.Action = "Review Application";
            logReview.EventDate = reviewer.CreatedDate;
            logReview.EventName = "Review Application";
            logReview.Info = "Review Application " + reviewer.Status + " " + reviewer.Description + " " + reviewer.Signature;
            if (logReview.EventDate != null) {
                logs.add(logReview);
            }

            TransactionLogs logReviewDecision = new TransactionLogs();
            logReviewDecision.IrbApplicationId = reviewer.IrbApplicationId;
            if (logReviewDecision.IrbApplicationId == null) {
                logReviewDecision.IrbApplicationId = -1;
            }
            logReviewDecision.UserId = reviewer.UserId;
            logReviewDecision.Action = "Decision Application";
            logReviewDecision.EventDate = reviewer.DecisionDate;
            logReviewDecision.EventName = "Decision Application";
            logReviewDecision.Info = "Decision Application " + reviewer.Status + " " + reviewer.Description + " " + reviewer.Signature;
            if (logReviewDecision.EventDate != null) {
                logs.add(logReviewDecision);
            }
        }

        Iterator<Map.Entry<String, StandardVotes>> standardVotesIterator = ((Map<String, StandardVotes>) data[3]).entrySet().iterator();

        while (standardVotesIterator.hasNext()) {
            StandardVotes standardVotes = standardVotesIterator.next().getValue();
            TransactionLogs logStandardVote = new TransactionLogs();
            logStandardVote.IrbApplicationId = standardVotes.IrbApplicationId;
            logStandardVote.UserId = standardVotes.UserId;
            logStandardVote.Action = "Application Vote";
            logStandardVote.EventDate = standardVotes.CreatedDate;
            logStandardVote.EventName = "Application Vote";
            logStandardVote.Info = "Application Vote " + standardVotes.Decision + " " + standardVotes.Reason;
            if (logStandardVote.EventDate != null) {
                logs.add(logStandardVote);
            }
        }

        Iterator<Map.Entry<String, FacultySponsors>> facultyIterator = ((Map<String, FacultySponsors>) data[4]).entrySet().iterator();

        while (facultyIterator.hasNext()) {
            FacultySponsors facultySponsors = facultyIterator.next().getValue();
            TransactionLogs facultyLog = new TransactionLogs();
            facultyLog.IrbApplicationId = facultySponsors.IrbApplicationId.Id;
            facultyLog.UserId = facultySponsors.UserId.Id;
            facultyLog.Action = "Application Faculty Decision";
            facultyLog.EventDate = facultySponsors.CreatedDate;
            if (facultySponsors.DecisionDate != null) {
                facultyLog.EventDate = facultySponsors.DecisionDate;
            }
            facultyLog.EventName = "Application Faculty Decision";
            facultyLog.Info = "Application Faculty Decision Approved:" + facultySponsors.IsApproved + " " + facultySponsors.Signature;
            if (facultyLog.EventDate != null) {
                logs.add(facultyLog);
            }

        }

        Iterator<Map.Entry<String, ReviewNotes>> reviewNotesIterator = ((Map<String, ReviewNotes>) data[5]).entrySet().iterator();

        while (reviewNotesIterator.hasNext()) {
            ReviewNotes reviewNote = reviewNotesIterator.next().getValue();
            TransactionLogs reviewLog = new TransactionLogs();
            reviewLog.IrbApplicationId = reviewNote.IrbApplicationId.Id;
            reviewLog.UserId = reviewNote.UserId.Id;
            reviewLog.Action = "New Note";
            reviewLog.EventDate = reviewNote.CreatedDate;
            reviewLog.EventName = "New Note";
            reviewLog.Info = "New Note:" + reviewNote.Note;
            if (reviewLog.EventDate != null) {
                logs.add(reviewLog);
            }

        }

        return logs;

    }


}
