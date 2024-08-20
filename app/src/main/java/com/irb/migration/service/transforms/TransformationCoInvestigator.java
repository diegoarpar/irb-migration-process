package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FCoinvestigator;
import com.irb.migration.entity.to.*;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        for (FCoinvestigator source: origin) {
            CoInvestigators coInvestigator =
                getCoinvestigator( source.application_id,  source.coinvesti_email1,
                        source.coinvesti_name1,  source.coinvesti_address1, source.coinvesti_sign_init1,
                            source.coinvesti_phone1, data[0], data[1]);
            if (coInvestigator != null) coInvestigators.add(coInvestigator);


            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email2,
                            source.coinvesti_name2,  source.coinvesti_address2, source.coinvesti_sign_init2,
                            source.coinvesti_phone2, data[0], data[1]);
            if (coInvestigator != null) coInvestigators.add(coInvestigator);

            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email3,
                            source.coinvesti_name3,  source.coinvesti_address3, source.coinvesti_sign_init3,
                            source.coinvesti_phone3, data[0], data[1]);
            if (coInvestigator != null) coInvestigators.add(coInvestigator);

            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email4,
                            source.coinvesti_name4,  source.coinvesti_address4, source.coinvesti_sign_init4,
                            source.coinvesti_phone4, data[0], data[1]);
            if (coInvestigator != null) coInvestigators.add(coInvestigator);

            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email5,
                            source.coinvesti_name5,  source.coinvesti_address5, source.coinvesti_sign_init5,
                            source.coinvesti_phone5, data[0], data[1]);
            if (coInvestigator != null) coInvestigators.add(coInvestigator);

            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email6,
                            source.coinvesti_name6,  source.coinvesti_address6, source.coinvesti_sign_init6,
                            source.coinvesti_phone6, data[0], data[1]);
            if (coInvestigator != null) coInvestigators.add(coInvestigator);

            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email7,
                            source.coinvesti_name7,  source.coinvesti_address7, source.coinvesti_sign_init7,
                            source.coinvesti_phone7, data[0], data[1]);
            if (coInvestigator != null) coInvestigators.add(coInvestigator);

            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email8,
                            source.coinvesti_name8,  source.coinvesti_address8, source.coinvesti_sign_init8,
                            source.coinvesti_phone8, data[0], data[1]);
            if (coInvestigator != null) coInvestigators.add(coInvestigator);

            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email9,
                            source.coinvesti_name9,  source.coinvesti_address9, source.coinvesti_sign_init9,
                            source.coinvesti_phone9, data[0], data[1]);
            if (coInvestigator != null) coInvestigators.add(coInvestigator);

            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email10,
                            source.coinvesti_name10,  source.coinvesti_address10, source.coinvesti_sign_init10,
                            source.coinvesti_phone10, data[0], data[1]);
            if (coInvestigator != null) coInvestigators.add(coInvestigator);

            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email11,
                            source.coinvesti_name11,  source.coinvesti_address11, source.coinvesti_sign_init11,
                            source.coinvesti_phone11, data[0], data[1]);
            if (coInvestigator != null) coInvestigators.add(coInvestigator);

            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_sign_email12,
                            source.coinvesti_name12,  source.coinvesti_address1, source.coinvesti_sign_init12,
                            source.coinvesti_sign_phone12, data[0], data[1]);

            if (coInvestigator != null) coInvestigators.add(coInvestigator);

            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email13,
                            source.coinvesti_name13,  source.coinvesti_address13, source.coinvesti_sign_init13,
                            source.coinvesti_phone13, data[0], data[1]);
            if (coInvestigator != null) coInvestigators.add(coInvestigator);



            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email14,
                            source.coinvesti_name14,  source.coinvesti_address14, source.coinvesti_sign_init14,
                            source.coinvesti_phone14, data[0], data[1]);
            if (coInvestigator != null) coInvestigators.add(coInvestigator);

            coInvestigator =
                    getCoinvestigator( source.application_id,  source.coinvesti_email15,
                            source.coinvesti_name15,  source.coinvesti_address15, source.coinvesti_sign_init15,
                            source.coinvesti_phone15, data[0], data[1]);
            if (coInvestigator != null) coInvestigators.add(coInvestigator);

        }
        return coInvestigators;
    }

    private CoInvestigators getCoinvestigator(
                                                String application_id,
                                                String coinvestiEmail1,
                                              String coinvestiName1,
                                              String coinvestiAddress1,
                                              String coinvestiSignInit1,
                                                String coinvestiPhone1,
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
        coInvestigators.IrbApplicationId = (IrbApplications) apps.get(application_id);
        if (coInvestigators.IrbApplicationId == null) {
            LOGGER.error("MIGRATION: IRB does not exist when migrate co-investigators " + application_id);
            return  null;
        }
        AspNetUsers coInvestigatorUser = (AspNetUsers) users.get(coinvestiEmail1.toUpperCase());
        if (coInvestigatorUser == null) {
            coInvestigatorUser = new AspNetUsers();
            coInvestigatorUser.Email = coinvestiEmail1;
            coInvestigatorUser.PhoneNumber = coinvestiPhone1;
            coInvestigatorUser.NormalizedEmail = coinvestiEmail1.toUpperCase();
            coInvestigatorUser.UserName = coinvestiEmail1;
            coInvestigatorUser.NormalizedUserName = coinvestiEmail1.toUpperCase();

            coInvestigatorUser.TwoFactorEnabled = 0;
            coInvestigatorUser.LockoutEnabled = 1;
            coInvestigatorUser.AccessFailedCount = 0;
            coInvestigatorUser.PhoneNumberConfirmed = Strings.isNullOrEmpty(coinvestiPhone1)? 0: 1 ;
            coInvestigatorUser.EmailConfirmed = Strings.isNullOrEmpty(coinvestiEmail1)? 0: 1 ;
            coInvestigatorUser.SecurityStamp =  helper.generateRandomStamp();
            LOGGER.info("MIGRATION: Creating new user when migrate co-investigators " + coInvestigatorUser.Email);
            users.put(coInvestigatorUser.NormalizedEmail, coInvestigatorUser);
        }
        coInvestigators.IrbUserId = coInvestigatorUser;
        coInvestigators.Firstname = coinvestiName1;
        coInvestigators.Lastname = coinvestiName1;
        coInvestigators.Email = coinvestiEmail1;
        coInvestigators.CreatedDate = new Date();
        coInvestigators.UpdatedDate = new Date();

        coInvestigators.Address = coinvestiAddress1;
        coInvestigators.Signature = coinvestiSignInit1;
        coInvestigators.Phone = coinvestiPhone1;
        return coInvestigators;
    }


}
