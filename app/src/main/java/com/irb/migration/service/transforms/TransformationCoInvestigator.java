package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FCoinvestigator;
import com.irb.migration.entity.to.*;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TransformationCoInvestigator implements IETLTransformation<CoInvestigators, FCoinvestigator> {

    @Inject
    public Helper helper;
    public List<CoInvestigators> TransformData(List<FCoinvestigator> sourceData) {
        return List.of();
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(TransformationCoInvestigator.class.getName());

    @Override
    public List<CoInvestigators> TransformData(List<FCoinvestigator> origin, Map... data) {
        List<CoInvestigators> coInvestigators = new ArrayList<>();
        Universities universities = (Universities) data[2].get("gannon");
        for (FCoinvestigator source: origin) {
            CoInvestigators coInvestigator =
                getCoinvestigator( source.application_id,  source.coinvesti_email1,
                        source.coinvesti_name1,  source.coinvesti_address1, source.coinvesti_sign_init1,
                            source.coinvesti_phone1, universities, data[0], data[1]);
            if (shouldAddInvestigator(coInvestigator, coInvestigators)) coInvestigators.add(coInvestigator);


            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email2,
                            source.coinvesti_name2,  source.coinvesti_address2, source.coinvesti_sign_init2,
                            source.coinvesti_phone2, universities, data[0], data[1]);
            if (shouldAddInvestigator(coInvestigator, coInvestigators)) coInvestigators.add(coInvestigator);

            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email3,
                            source.coinvesti_name3,  source.coinvesti_address3, source.coinvesti_sign_init3,
                            source.coinvesti_phone3, universities, data[0], data[1]);
            if (shouldAddInvestigator(coInvestigator, coInvestigators)) coInvestigators.add(coInvestigator);

            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email4,
                            source.coinvesti_name4,  source.coinvesti_address4, source.coinvesti_sign_init4,
                            source.coinvesti_phone4, universities, data[0], data[1]);
            if (shouldAddInvestigator(coInvestigator, coInvestigators)) coInvestigators.add(coInvestigator);

            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email5,
                            source.coinvesti_name5,  source.coinvesti_address5, source.coinvesti_sign_init5,
                            source.coinvesti_phone5, universities, data[0], data[1]);
            if (shouldAddInvestigator(coInvestigator, coInvestigators)) coInvestigators.add(coInvestigator);

            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email6,
                            source.coinvesti_name6,  source.coinvesti_address6, source.coinvesti_sign_init6,
                            source.coinvesti_phone6, universities, data[0], data[1]);
            if (shouldAddInvestigator(coInvestigator, coInvestigators)) coInvestigators.add(coInvestigator);

            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email7,
                            source.coinvesti_name7,  source.coinvesti_address7, source.coinvesti_sign_init7,
                            source.coinvesti_phone7, universities, data[0], data[1]);
            if (shouldAddInvestigator(coInvestigator, coInvestigators)) coInvestigators.add(coInvestigator);

            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email8,
                            source.coinvesti_name8,  source.coinvesti_address8, source.coinvesti_sign_init8,
                            source.coinvesti_phone8, universities, data[0], data[1]);
            if (shouldAddInvestigator(coInvestigator, coInvestigators)) coInvestigators.add(coInvestigator);

            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email9,
                            source.coinvesti_name9,  source.coinvesti_address9, source.coinvesti_sign_init9,
                            source.coinvesti_phone9, universities, data[0], data[1]);
            if (shouldAddInvestigator(coInvestigator, coInvestigators)) coInvestigators.add(coInvestigator);

            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email10,
                            source.coinvesti_name10,  source.coinvesti_address10, source.coinvesti_sign_init10,
                            source.coinvesti_phone10, universities, data[0], data[1]);
            if (shouldAddInvestigator(coInvestigator, coInvestigators)) coInvestigators.add(coInvestigator);

            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email11,
                            source.coinvesti_name11,  source.coinvesti_address11, source.coinvesti_sign_init11,
                            source.coinvesti_phone11, universities, data[0], data[1]);
            if (shouldAddInvestigator(coInvestigator, coInvestigators)) coInvestigators.add(coInvestigator);

            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_sign_email12,
                            source.coinvesti_name12,  source.coinvesti_address1, source.coinvesti_sign_init12,
                            source.coinvesti_sign_phone12, universities, data[0], data[1]);

            if (shouldAddInvestigator(coInvestigator, coInvestigators)) coInvestigators.add(coInvestigator);

            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email13,
                            source.coinvesti_name13,  source.coinvesti_address13, source.coinvesti_sign_init13,
                            source.coinvesti_phone13, universities, data[0], data[1]);
            if (shouldAddInvestigator(coInvestigator, coInvestigators)) coInvestigators.add(coInvestigator);



            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email14,
                            source.coinvesti_name14,  source.coinvesti_address14, source.coinvesti_sign_init14,
                            source.coinvesti_phone14, universities, data[0], data[1]);
            if (shouldAddInvestigator(coInvestigator, coInvestigators)) coInvestigators.add(coInvestigator);

            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email15,
                            source.coinvesti_name15,  source.coinvesti_address15, source.coinvesti_sign_init15,
                            source.coinvesti_phone15, universities, data[0], data[1]);
            if (shouldAddInvestigator(coInvestigator, coInvestigators)) coInvestigators.add(coInvestigator);

        }
        return coInvestigators;
    }
    
    private boolean shouldAddInvestigator(CoInvestigators coInvestigator, List<CoInvestigators> coInvestigators) {
        if (Objects.isNull(coInvestigator) || Strings.isNullOrEmpty(coInvestigator.Email)) {
            return false;
        }
        return coInvestigators
                .stream()
                .noneMatch(coPI -> coPI.Email.equalsIgnoreCase(coInvestigator.Email));
    }

    private CoInvestigators getCoinvestigator(
                                                String application_id,
                                                String coinvestiEmail1,
                                              String coinvestiName1,
                                              String coinvestiAddress1,
                                              String coinvestiSignInit1,
                                                String coinvestiPhone1,
                                              Universities universities,
                                              Map users, Map apps) {
        if (Strings.isNullOrEmpty(application_id)
            || Strings.isNullOrEmpty(coinvestiEmail1)
                || Strings.isNullOrEmpty(coinvestiName1)
                || "N/A".equalsIgnoreCase(coinvestiEmail1)
                || "N/A".equalsIgnoreCase(coinvestiName1)
        ) {
            return  null;
        }
        CoInvestigators coInvestigators = new CoInvestigators();
        IrbApplications app = (IrbApplications) apps.get(application_id);
        coInvestigators.IrbApplicationId = app;

        if (coInvestigators.IrbApplicationId == null) {
            LOGGER.error("MIGRATION: IRB does not exist when migrate co-investigators " + application_id);
            return  null;
        }
        UserProfiles userProfiles = (UserProfiles) users.get(coinvestiEmail1.toUpperCase());

        if (userProfiles == null) {
            userProfiles = helper.getUserProfile(coinvestiEmail1, coinvestiPhone1, coinvestiName1, "", "",
                    "", "", app.SubmittedDate, "", "", "", "", "",
                    "yes", "Faculty", "no", "no",
                    coinvestiAddress1, universities);
            LOGGER.info("MIGRATION: Creating new user when migrate co-investigators " + userProfiles.UserId.Email);
            users.put(userProfiles.UserId.NormalizedEmail, userProfiles);
        }

        AspNetUsers coInvestigatorUser = userProfiles.UserId;

        coInvestigators.IrbUserId = coInvestigatorUser;
        coInvestigators.Firstname = coinvestiName1;
        coInvestigators.Lastname = coinvestiName1;
        coInvestigators.Email = coinvestiEmail1;
        coInvestigators.CreatedDate = app.SubmittedDate;
        coInvestigators.UpdatedDate = app.SubmittedDate;

        coInvestigators.Address = coinvestiAddress1;
        if (Strings.isNullOrEmpty(userProfiles.Address) && !Strings.isNullOrEmpty(coinvestiAddress1)) {
            userProfiles.Address = coInvestigators.Address;
        }
        coInvestigators.Signature = coinvestiSignInit1;
        coInvestigators.Phone = coinvestiPhone1;
        if (Strings.isNullOrEmpty(userProfiles.UserId.PhoneNumber) && !Strings.isNullOrEmpty(coinvestiPhone1)) {
            userProfiles.UserId.PhoneNumber = coinvestiPhone1;
        }
        return coInvestigators;
    }


}
