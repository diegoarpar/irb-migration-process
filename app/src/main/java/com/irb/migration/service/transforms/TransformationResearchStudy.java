package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FResearchStudy;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.entity.to.ResearchStudies;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransformationResearchStudy implements IETLTransformation<ResearchStudies, FResearchStudy> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransformationResearchStudy.class.getName());


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
                LOGGER.error("MIGRATION: IRB does not exist when migrate research study " + source.application_id);
                return null;
            }
            ResearchStudies researchStudies = new ResearchStudies();

            researchStudies.IrbApplicationId = application;
            researchStudies.UserId = application.UserId;
            researchStudies.FundingAgency = source.fund_agency;

            if (!"n/a".equalsIgnoreCase(source.fund_agency) && !Strings.isNullOrEmpty(source.fund_agency) && source.fund_agency.trim().length() > 3) {
                researchStudies.AgencyType = "External Agency";
                researchStudies.Agency = "Others";
                researchStudies.AgencyOther = source.fund_agency;
            } else {
                researchStudies.AgencyType = "Not Funded Research";
                researchStudies.Agency = "";
            }

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
            researchStudies.AgencyOther = "";
            researchStudies.IsUtilizeAcademicRecord = 0;
            researchStudies.IsAnyIntervention = 0;
            researchStudies.Procedure = "";
            researchStudies.IsRequiredGrant = !Strings.isNullOrEmpty(source.research_std_proposal) && source.research_std_proposal.toLowerCase().contains("grant")? 1: 0;
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
        return typeOfConsent.toLowerCase().contains(word.toLowerCase()) ? "True" : "False";
    }

}
