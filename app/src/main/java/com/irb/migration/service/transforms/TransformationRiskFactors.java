package com.irb.migration.service.transforms;

import com.irb.migration.entity.from.FDataHandling;
import com.irb.migration.entity.from.FRiskFactor;
import com.irb.migration.entity.to.DataHandling;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.entity.to.RiskFactors;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransformationRiskFactors implements IETLTransformation<RiskFactors, FRiskFactor> {

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


            riskFactors.CreatedDate = application.SubmittedDate != null? application.SubmittedDate: new Date();
            riskFactors.UpdatedDate = riskFactors.CreatedDate;

            return riskFactors;
        }).filter(Objects::nonNull).collect(Collectors.toList());

    }




}
