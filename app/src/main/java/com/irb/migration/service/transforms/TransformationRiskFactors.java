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
            riskFactors.DegreeOfRisks = source.exp_dtl;
            riskFactors.Field43 = source.field43;
            riskFactors.Field44 = source.field44;
            riskFactors.GeneralKnowledge = String.format("%s %s", source.contri_to_gk, source.sub_rewarded_compen);
            riskFactors.IncludeSomething = 0;
            riskFactors.InvolvedResearch = getInvolvedResearch(source);
            riskFactors.PossibleBenefit = source.possible_benefits + " " + source.contri_to_gk;

            riskFactors.CreatedDate = application.SubmittedDate != null? application.SubmittedDate: new Date();
            riskFactors.UpdatedDate = riskFactors.CreatedDate;

            return riskFactors;
        }).filter(Objects::nonNull).collect(Collectors.toList());

    }

    private String getInvolvedResearch(FRiskFactor risk) {
        String consent = "1|Any surgical procedure|%s, 2|Administration of approved/unapproved drugs / chemical or biological agents|%s, 3|Only administration of legend drugs|%s, 4|Administration of approved/unapproved devices|%s, 5|Radioisotopes or other sources of ionizing radiation Placebos|%s, 6|Controlled Substances|%s, 7|Recombinant DNA|%s, 8|Human Gene Transfer|%s, 9|Biological Toxins|%s, 10|Infectious Agents|%s, 11|Embryonic stem cells|%s, 12|Administration of physical stimuli Major changes in diet / exercise or sleep Blood Draw|%s, 13|Use of private records|%s, 14|Possible invasion of privacy of subject or family|%s, 15|Manipulation of psychological or social variables|%s, 16|Any probing for personal or sensitive information in surveys or interviews|%s, 17|Presentation of materials which subjects might consider sensitive / offensive / threatening or degrading|%s, 18|Use of a deceptive technique|%s, 19|Other risks(specify)|%s, 20|None of the above|%s";
        String anyRisk1 = "False";
        String drugs2 = helper.trueFalseFromYes(risk.exp_drug);
        String drugs3 = drugs2;
        String devices4 = "False";
        String ionization5 = helper.trueFalseFromYes(risk.elec_equip);
        String controllledSubstance6 = "False";
        String dna7 = "False";
        String gene8 = "False";
        String toxic9 = "False";
        String agents10 = "False";
        String embrionic11 = "False";
        String physicalStimulation12 = helper.trueFalseFromYes(risk.sub_phys_discomfort);
        String privateRecords13 = "False";
        String familiyPrivaticty14 = "False";
        String physicology15 = helper.trueFalseFromYes(risk.sub_mental_discomfort);
        String interview16 = "False";
        String sensiveMaterial17 = "False";
        String deception18 = helper.trueFalseFromYes(risk.sub_deception);
        String otherRisk19 = "False";
        String none20 = "False";
        String finalInfo = String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s", consent, anyRisk1, drugs2, drugs3, devices4, ionization5, controllledSubstance6
                , dna7, gene8, toxic9, agents10, embrionic11, physicalStimulation12, privateRecords13,
                familiyPrivaticty14, physicology15, interview16, sensiveMaterial17, deception18, otherRisk19, none20);

        none20 = finalInfo.toLowerCase().contains("yes")? "No": "Yes";
        anyRisk1 = finalInfo.toLowerCase().contains("yes")? "Yes": "No";
        return String.format(consent, anyRisk1, drugs2, drugs3, devices4, ionization5, controllledSubstance6
                , dna7, gene8, toxic9, agents10, embrionic11, physicalStimulation12, privateRecords13,
                familiyPrivaticty14, physicology15, interview16, sensiveMaterial17, deception18, otherRisk19, none20
                );
    }


}
