package com.irb.migration.service.transforms;

import com.irb.migration.entity.from.FRiskFactor;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.entity.to.RiskFactors;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransformationRiskFactors implements IETLTransformation<RiskFactors, FRiskFactor> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransformationRiskFactors.class.getName());


    @Inject
    public Helper helper;
    public List<RiskFactors> TransformData(List<FRiskFactor> sourceData) {
        return List.of();
    }

    @Override
    public List<RiskFactors> TransformData(List<FRiskFactor> origin, Map... data) {
        return origin.stream().map(source -> {

            IrbApplications application = (IrbApplications) data[1].get(source.application_id.toUpperCase());
            if (application == null) {
                LOGGER.error("MIGRATION: IRB does not exist when migrate risk factor " + source.application_id);
                return null;
            }
            RiskFactors riskFactors = new RiskFactors();
            riskFactors.IrbApplicationId = application;
            riskFactors.UserId = application.UserId;
            riskFactors.SubjectRisk = helper.fromYesNoToInt(source.sub_risk);
            riskFactors.ExperimentalDrug = helper.fromYesNoToInt(source.exp_drug);
            riskFactors.MedicalProblem = helper.fromYesNoToInt(source.medical_prob);
            riskFactors.PhysicalDiscomfort = helper.fromYesNoToInt(source.sub_phys_discomfort);
            riskFactors.MentalDiscomfort = helper.fromYesNoToInt(source.sub_mental_discomfort);
            riskFactors.ElectricalEquipment = helper.fromYesNoToInt(source.elec_equip);
            riskFactors.SubjectRecord = helper.fromYesNoToInt(source.sub_record);
            riskFactors.SubjectCoercion = helper.fromYesNoToInt(source.coercion_sub);
            riskFactors.SubjectDeception = helper.fromYesNoToInt(source.sub_deception);
            riskFactors.CoercionDeceptionDetail = source.exp_dtl;
            riskFactors.Field43 = source.field43;
            riskFactors.Field44 = source.field44;
            riskFactors.GeneralKnowledge = String.format("%s %s", source.contri_to_gk, source.sub_rewarded_compen);
            riskFactors.IncludeSomething = 0;
            riskFactors.InvolvedResearch = getInvolvedResearch();
            riskFactors.PossibleBenefit = source.possible_benefits + " " + source.contri_to_gk;

            riskFactors.CreatedDate = application.SubmittedDate != null? application.SubmittedDate: new Date();
            riskFactors.UpdatedDate = riskFactors.CreatedDate;

            return riskFactors;
        }).filter(Objects::nonNull).collect(Collectors.toList());

    }

    private String getInvolvedResearch() {
        return "1|Any surgical procedure|False, 2|Administration of approved/unapproved drugs / chemical or biological agents|False, 3|Only administration of legend drugs|False, 4|Administration of approved/unapproved devices|False, 5|Radioisotopes or other sources of ionizing radiation Placebos|False, 6|Controlled Substances|False, 7|Recombinant DNA|False, 8|Human Gene Transfer|False, 9|Biological Toxins|False, 10|Infectious Agents|False, 11|Embryonic stem cells|False, 12|Administration of physical stimuli Major changes in diet / exercise or sleep Blood Draw|False, 13|Use of private records|False, 14|Possible invasion of privacy of subject or family|False, 15|Manipulation of psychological or social variables|False, 16|Any probing for personal or sensitive information in surveys or interviews|False, 17|Presentation of materials which subjects might consider sensitive / offensive / threatening or degrading|False, 18|Use of a deceptive technique|False, 19|Other risks(specify)|False, 20|None of the above|False";
    }


}
