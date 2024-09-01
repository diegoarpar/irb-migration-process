package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FDataHandling;
import com.irb.migration.entity.from.FResearchStudy;
import com.irb.migration.entity.from.FRiskFactor;
import com.irb.migration.entity.to.DataHandling;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.entity.to.Recruitments;
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

public class TransformationRecruitment implements IETLTransformation<Recruitments, FRiskFactor> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransformationRecruitment.class.getName());


    @Inject
    public Helper helper;
    public List<Recruitments> TransformData(List<FRiskFactor> sourceData) {
        return List.of();
    }

    @Override
    public List<Recruitments> TransformData(List<FRiskFactor> origin, Map... data) {
        return origin.stream().map(source -> {

            IrbApplications application = (IrbApplications) data[0].get(source.application_id.toUpperCase());
            if (application == null) {
                LOGGER.error("MIGRATION: IRB does not exist when migrate recruitment " + source.application_id);
                return null;
            }
            FDataHandling dataHandling = (FDataHandling) data[1].get(source.application_id.toUpperCase());
            Recruitments recruitment = new Recruitments();

            recruitment.IrbApplicationId = application.Id;
            recruitment.UserId = application.UserId.Id;
            recruitment.WillSubjectsReceiveAnything = 0;
            recruitment.AreChosenFromRecord = 0;
            recruitment.HasAccess = 0;
            recruitment.HasIdentifiableInformation = 0;
            if (!Strings.isNullOrEmpty(source.sub_rewarded_compen) && source.sub_rewarded_compen.length() > 4) {
                recruitment.WillSubjectsReceiveAnything = 1;
                recruitment.ReceiveDetail = source.sub_rewarded_compen;
            }

            if (dataHandling != null) {
                recruitment.Strategy = dataHandling.research_dissem_sub;
                recruitment.HasAccess = "yes".equalsIgnoreCase(dataHandling.data_collect_identi)? 1: 0;
                recruitment.HasIdentifiableInformation = "yes".equalsIgnoreCase(dataHandling.data_collect_identi)? 1: 0;
                recruitment.AreChosenFromRecord = "archival".contains(dataHandling.data_collect_method.toLowerCase())? 1: 0;
                recruitment.ApprovedPerson = dataHandling.acc_person;
                recruitment.IdentifiableInformation = dataHandling.sch_mthd_deidenti_data;
            }


            recruitment.CreatedDate = application.SubmittedDate != null? application.SubmittedDate: new Date();
            recruitment.UpdatedDate = recruitment.CreatedDate;

            return recruitment;
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
