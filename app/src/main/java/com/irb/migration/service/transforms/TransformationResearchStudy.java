package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FResearchStudy;
import com.irb.migration.entity.from.FSubjectDef;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.entity.to.ResearchStudies;
import com.irb.migration.entity.to.SubjectDefines;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransformationResearchStudy implements IETLTransformation<ResearchStudies, FResearchStudy> {

    @Inject
    public Helper helper;
    public List<ResearchStudies> TransformData(List<FResearchStudy> sourceData) {
        return List.of();
    }

    @Override
    public List<ResearchStudies> TransformData(List<FResearchStudy> origin, Map... data) {
        return origin.stream().map(source -> {

            IrbApplications application = (IrbApplications) data[1].get(source.application_id.toUpperCase());
            if (application == null) {
                return null;
            }
            ResearchStudies researchStudies = new ResearchStudies();

            researchStudies.IrbApplicationId = application;
            researchStudies.UserId = application.UserId;
            researchStudies.FundingAgency = source.fund_agency;
            researchStudies.IsApprovalRequired = helper.fromYesNoToInt(source.irb_appvr_req);
            researchStudies.StartDate = helper.toDateMinus(source.data_start_day);
            researchStudies.StartDate = Objects.isNull(researchStudies.StartDate)? new Date():  researchStudies.StartDate;
            researchStudies.EndDate = helper.toDateMinus(source.data_end_day);
            researchStudies.EndDate = Objects.isNull(researchStudies.EndDate)? new Date(): researchStudies.EndDate;
            researchStudies.Duration = source.projected_std_dur;
            researchStudies.Purpose = source.purpose;
            researchStudies.Method = source.methodology;
            researchStudies.HasConflict = 0;
            researchStudies.IsReviewed = 0;
            researchStudies.IsUtilizeAcademicRecord = 0;
            researchStudies.IsAnyIntervention = 0;
            researchStudies.Procedure = "";
            researchStudies.IsRequiredGrant = !Strings.isNullOrEmpty(source.research_std_proposal) && source.research_std_proposal.contains("grant")? 1: 0;
            researchStudies.ResearchProposals = getProposal(source.research_std_proposal);


            researchStudies.CreatedDate = application.SubmittedDate != null? application.SubmittedDate: new Date();
            researchStudies.UpdatedDate = researchStudies.CreatedDate;

            return researchStudies;
        }).filter(Objects::nonNull).collect(Collectors.toList());

    }

    private String getProposal(String typeOfConsent) {
        String consent = "1|A new grant/contract|%s, 2|A dissertation or thesis|%s, 3|An independent study|%s, 4|A graduate research proposal|%s, 5|A class project|%s, 6|An undergraduate research proposal|%s, 7|An existing grant|%s";
        String grant1 = contains("grant", typeOfConsent);
        String thesis2 = contains("thesis", typeOfConsent);
        String independent3 = contains("independent", typeOfConsent);
        String graduate4 = contains("graduate", typeOfConsent);
        String class5 = contains("class", typeOfConsent);
        String undergraduate6 = contains("undergraduate", typeOfConsent);
        String existing7 = contains("existing", typeOfConsent);

        return String.format(consent, grant1, thesis2, independent3, graduate4, class5, undergraduate6, existing7);
    }



    private String contains(String word, String typeOfConsent) {
        if (Strings.isNullOrEmpty(typeOfConsent)) {
            return "False";
        }
        return typeOfConsent.contains(word) ? "True" : "False";
    }

}
