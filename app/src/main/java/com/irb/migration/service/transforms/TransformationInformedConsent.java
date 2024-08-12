package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FDataHandling;
import com.irb.migration.entity.from.FInformedConsent;
import com.irb.migration.entity.to.DataHandling;
import com.irb.migration.entity.to.InformedConsents;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransformationInformedConsent implements IETLTransformation<InformedConsents, FInformedConsent> {

    @Inject
    public Helper helper;
    public List<InformedConsents> TransformData(List<FInformedConsent> sourceData) {
        return List.of();
    }

    @Override
    public List<InformedConsents> TransformData(List<FInformedConsent> origin, Map... data) {
        return origin.stream().map(source -> {

            IrbApplications application = (IrbApplications) data[1].get(source.application_id.toUpperCase());
            if (application == null) {
                return null;
            }
            InformedConsents informedConsents = new InformedConsents();

            informedConsents.IrbApplicationId = application;
            informedConsents.UserId = application.UserId;
            informedConsents.ConsentType = getConsentType(source.type_of_consent);
            informedConsents.FormDistribution = source.consent_form_distri;
            informedConsents.SubjectWithdraw = helper.fromYesNoToInt(source.sub_inf_withdraw);
            informedConsents.WrittenConsent = helper.fromYesNoToInt(source.writ_consent);
            informedConsents.WrittenConsentOther = source.writ_consent_other;
            informedConsents.OralConsent = helper.fromYesNoToInt(source.oral_consent);
            informedConsents.OralConsentIOther = source.oral_consent_other;
            informedConsents.MinorParticipate = helper.fromYesNoToInt(source.minor_participate);
            informedConsents.MinorParticipateOther = source.minor_participate_other;
            informedConsents.MinorSubject = helper.fromYesNoToInt(source.minor_sub_obtain);
            informedConsents.PrincipalConcent = helper.fromYesNoToInt(source.princi_consent_waive);
            informedConsents.PrincipalConcentOther = source.princi_consent_waive_other;
            informedConsents.R01 = helper.fromYesNoToInt(source.r01);
            informedConsents.R02 = helper.fromYesNoToInt(source.r02);
            informedConsents.R03 = helper.fromYesNoToInt(source.r03);
            informedConsents.R04 = helper.fromYesNoToInt(source.r04);
            informedConsents.R05 = helper.fromYesNoToInt(source.r05);
            informedConsents.R06 = helper.fromYesNoToInt(source.r06);
            informedConsents.R07 = helper.fromYesNoToInt(source.r07);
            informedConsents.R08 = helper.fromYesNoToInt(source.r08);
            informedConsents.R09 = helper.fromYesNoToInt(source.r09);
            informedConsents.R10 = helper.fromYesNoToInt(source.r10);
            informedConsents.R11 = helper.fromYesNoToInt(source.r11);
            informedConsents.R12 = helper.fromYesNoToInt(source.r12);
            informedConsents.R13 = helper.fromYesNoToInt(source.r13);
            informedConsents.R14 = helper.fromYesNoToInt(source.r14);
            informedConsents.R15 = helper.fromYesNoToInt(source.r15);
            informedConsents.R16 = helper.fromYesNoToInt(source.r16);
            informedConsents.R17 = helper.fromYesNoToInt(source.r17);
            informedConsents.R01R17Other = source.exp_dtl_r1_to_r17;
            informedConsents.R18 = helper.fromYesNoToInt(source.r18);
            informedConsents.R19 = helper.fromYesNoToInt(source.r19);
            informedConsents.R20 = helper.fromYesNoToInt(source.r20);
            informedConsents.R21 = helper.fromYesNoToInt(source.r21);
            informedConsents.R22 = helper.fromYesNoToInt(source.r22);
            informedConsents.R23 = helper.fromYesNoToInt(source.r23);
            informedConsents.R24 = helper.fromYesNoToInt(source.r24);
            informedConsents.R25 = helper.fromYesNoToInt(source.r25);
            informedConsents.R26 = helper.fromYesNoToInt(source.r26);
            informedConsents.R27 = helper.fromYesNoToInt(source.r27);
            informedConsents.R28 = helper.fromYesNoToInt(source.r28);
            informedConsents.R29 = helper.fromYesNoToInt(source.r29);
            informedConsents.R30 = helper.fromYesNoToInt(source.r30);
            informedConsents.R31 = helper.fromYesNoToInt(source.r31);
            informedConsents.CreatedDate = application.SubmittedDate != null? application.SubmittedDate: new Date();
            informedConsents.UpdatedDate = informedConsents.CreatedDate;


            return informedConsents;
        }).filter(Objects::nonNull).collect(Collectors.toList());

    }

    private String getConsentType(String typeOfConsent) {
        String consent = "1|Written (or electronic)|%s, 2|Child Assent|%s, 3|Oral|%s, 4|Information Sheet|%s, 5|Consent of participants subjects 18 years of age and older|%s, 6|Request for consent to be waived|%s";
        String written = contains("written", typeOfConsent);
        String child = contains("minors", typeOfConsent);
        String oral = contains("oral", typeOfConsent);
        String sheet = contains("sheet", typeOfConsent);
        String older = contains("parental", typeOfConsent);
        String waiver = contains("waiver", typeOfConsent);
        return String.format(consent, written, child, oral, sheet, older, waiver);
    }

    private String contains(String word, String typeOfConsent) {
        if (Strings.isNullOrEmpty(typeOfConsent)) {
            return "False";
        }
        return typeOfConsent.contains(word) ? "True" : "False";
    }


}
