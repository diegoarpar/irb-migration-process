package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FInformedConsent;
import com.irb.migration.entity.to.InformedConsents;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransformationInformedConsent implements IETLTransformation<InformedConsents, FInformedConsent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransformationInformedConsent.class.getName());

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
                LOGGER.error("MIGRATION: IRB does not exist when migrate information consent " + source.application_id);
                return null;
            }
            InformedConsents informedConsents = new InformedConsents();

            informedConsents.IrbApplicationId = application;
            informedConsents.UserId = application.UserId;
            informedConsents.ConsentType = getConsentType(source.type_of_consent);
            informedConsents.FormDistribution = source.consent_form_distri + " " + source.sub_inf_withdraw_other + " " + source.minor_sub_obtain_other;
            informedConsents.SubjectWithdraw = helper.fromYesNoToBoolean(source.sub_inf_withdraw);
            informedConsents.WrittenConsent = helper.fromYesNoToBoolean(source.writ_consent);
            informedConsents.WrittenConsentOther = source.writ_consent_other;
            informedConsents.OralConsent = helper.fromYesNoToBoolean(source.oral_consent);
            informedConsents.OralConsentIOther = source.oral_consent_other;
            informedConsents.MinorParticipate = helper.fromYesNoToBoolean(source.minor_participate);
            informedConsents.MinorParticipateOther = source.minor_participate_other;
            informedConsents.MinorSubject = helper.fromYesNoToBoolean(source.minor_sub_obtain);
            informedConsents.PrincipalConcent = helper.fromYesNoToBoolean(source.princi_consent_waive);
            informedConsents.PrincipalConcentOther = source.princi_consent_waive_other;
            informedConsents.R01 = helper.fromYesNoToBoolean(source.r01);
            informedConsents.R02 = helper.fromYesNoToBoolean(source.r02);
            informedConsents.R03 = helper.fromYesNoToBoolean(source.r03);
            informedConsents.R04 = helper.fromYesNoToBoolean(source.r04);
            informedConsents.R05 = helper.fromYesNoToBoolean(source.r05);
            informedConsents.R06 = helper.fromYesNoToBoolean(source.r06);
            informedConsents.R07 = helper.fromYesNoToBoolean(source.r07);
            informedConsents.R08 = helper.fromYesNoToBoolean(source.r08);
            informedConsents.R09 = helper.fromYesNoToBoolean(source.r09);
            informedConsents.R10 = helper.fromYesNoToBoolean(source.r10);
            informedConsents.R11 = helper.fromYesNoToBoolean(source.r11);
            informedConsents.R12 = helper.fromYesNoToBoolean(source.r12);
            informedConsents.R13 = helper.fromYesNoToBoolean(source.r13);
            informedConsents.R14 = helper.fromYesNoToBoolean(source.r14);
            informedConsents.R15 = helper.fromYesNoToBoolean(source.r15);
            informedConsents.R16 = helper.fromYesNoToBoolean(source.r16);
            informedConsents.R17 = helper.fromYesNoToBoolean(source.r17);
            informedConsents.R01R17Other = source.exp_dtl_r1_to_r17;
            informedConsents.R18 = helper.fromYesNoToBoolean(source.r18);
            informedConsents.R19 = helper.fromYesNoToBoolean(source.r19);
            informedConsents.R20 = helper.fromYesNoToBoolean(source.r20);
            informedConsents.R21 = helper.fromYesNoToBoolean(source.r21);
            informedConsents.R22 = helper.fromYesNoToBoolean(source.r22);
            informedConsents.R23 = helper.fromYesNoToBoolean(source.r23);
            informedConsents.R24 = helper.fromYesNoToBoolean(source.r24);
            informedConsents.R25 = helper.fromYesNoToBoolean(source.r25);
            informedConsents.R26 = helper.fromYesNoToBoolean(source.r26);
            informedConsents.R27 = helper.fromYesNoToBoolean(source.r27);
            informedConsents.R28 = helper.fromYesNoToBoolean(source.r28);
            informedConsents.R29 = helper.fromYesNoToBoolean(source.r29);
            informedConsents.R30 = helper.fromYesNoToBoolean(source.r30);
            informedConsents.R31 = helper.fromYesNoToBoolean(source.r31);
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
        return typeOfConsent.toLowerCase().contains(word.toLowerCase()) ? "True" : "False";
    }


}
