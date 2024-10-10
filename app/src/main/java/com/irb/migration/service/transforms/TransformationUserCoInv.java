package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FCoinvestigator;
import com.irb.migration.entity.to.*;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class TransformationUserCoInv implements IETLTransformation<UserProfiles, FCoinvestigator> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransformationUserCoInv.class.getName());


    @Inject
    public Helper helper;
    public List<UserProfiles> TransformData(List<FCoinvestigator> sourceData) {
        return List.of();
    }

    @Override
    public List<UserProfiles> TransformData(List<FCoinvestigator> origin, Map... data) {
        List<UserProfiles> listUsers = new ArrayList<>();
        Universities universities = (Universities) data[1].get("gannon");
        for (FCoinvestigator co: origin) {

            if (shouldAddInvestigator(co.coinvesti_email1)) {
                UserProfiles userProfiles = (UserProfiles) data[0].get(co.coinvesti_email1.toUpperCase());
                if (userProfiles == null) {
                    userProfiles = helper.getUserProfile(co.coinvesti_email1, co.coinvesti_phone1, co.coinvesti_name1, co.coinvesti_sign_init1,
                            "", "", "", new Date(),
                            "", "", "", "", "", "yes", "Student", "no", "no",
                            co.coinvesti_address1, universities);
                    listUsers.add(userProfiles);
                }

            }

            if (shouldAddInvestigator(co.coinvesti_email2)) {
                UserProfiles userProfiles = (UserProfiles) data[0].get(co.coinvesti_email2.toUpperCase());
                if (userProfiles == null) {
                    userProfiles = helper.getUserProfile(co.coinvesti_email2, co.coinvesti_phone2, co.coinvesti_name2, co.coinvesti_sign_init2,
                            "", "", "", new Date(),
                            "", "", "", "", "", "yes", "Student", "no", "no",
                            co.coinvesti_address2, universities);
                    listUsers.add(userProfiles);
                }

            }

            if (shouldAddInvestigator(co.coinvesti_email3)) {
                UserProfiles userProfiles = (UserProfiles) data[0].get(co.coinvesti_email3.toUpperCase());
                if (userProfiles == null) {
                    userProfiles = helper.getUserProfile(co.coinvesti_email3, co.coinvesti_phone3, co.coinvesti_name3, co.coinvesti_sign_init3,
                            "", "", "", new Date(),
                            "", "", "", "", "", "yes", "Student", "no", "no",
                            co.coinvesti_address3, universities);
                    listUsers.add(userProfiles);
                }

            }

            if (shouldAddInvestigator(co.coinvesti_email4)) {
                UserProfiles userProfiles = (UserProfiles) data[0].get(co.coinvesti_email4.toUpperCase());
                if (userProfiles == null) {
                    userProfiles = helper.getUserProfile(co.coinvesti_email4, co.coinvesti_phone4, co.coinvesti_name4, co.coinvesti_sign_init4,
                            "", "", "", new Date(),
                            "", "", "", "", "", "yes", "Student", "no", "no",
                            co.coinvesti_address4, universities);
                    listUsers.add(userProfiles);
                }

            }

            if (shouldAddInvestigator(co.coinvesti_email5)) {
                UserProfiles userProfiles = (UserProfiles) data[0].get(co.coinvesti_email5.toUpperCase());
                if (userProfiles == null) {
                    userProfiles = helper.getUserProfile(co.coinvesti_email5, co.coinvesti_phone5, co.coinvesti_name5, co.coinvesti_sign_init5,
                            "", "", "", new Date(),
                            "", "", "", "", "", "yes", "Student", "no", "no",
                            co.coinvesti_address5, universities);
                    listUsers.add(userProfiles);
                }

            }

            if (shouldAddInvestigator(co.coinvesti_email6)) {
                UserProfiles userProfiles = (UserProfiles) data[0].get(co.coinvesti_email6.toUpperCase());
                if (userProfiles == null) {
                    userProfiles = helper.getUserProfile(co.coinvesti_email6, co.coinvesti_phone6, co.coinvesti_name6, co.coinvesti_sign_init6,
                            "", "", "", new Date(),
                            "", "", "", "", "", "yes", "Student", "no", "no",
                            co.coinvesti_address6, universities);
                    listUsers.add(userProfiles);
                }

            }

            if (shouldAddInvestigator(co.coinvesti_email7)) {
                UserProfiles userProfiles = (UserProfiles) data[0].get(co.coinvesti_email7.toUpperCase());
                if (userProfiles == null) {
                    userProfiles = helper.getUserProfile(co.coinvesti_email7, co.coinvesti_phone7, co.coinvesti_name7, co.coinvesti_sign_init7,
                            "", "", "", new Date(),
                            "", "", "", "", "", "yes", "Student", "no", "no",
                            co.coinvesti_address7, universities);
                    listUsers.add(userProfiles);
                }

            }

            if (shouldAddInvestigator(co.coinvesti_email8)) {
                UserProfiles userProfiles = (UserProfiles) data[0].get(co.coinvesti_email8.toUpperCase());
                if (userProfiles == null) {
                    userProfiles = helper.getUserProfile(co.coinvesti_email8, co.coinvesti_phone8, co.coinvesti_name8, co.coinvesti_sign_init8,
                            "", "", "", new Date(),
                            "", "", "", "", "", "yes", "Student", "no", "no",
                            co.coinvesti_address8, universities);
                    listUsers.add(userProfiles);
                }

            }

            if (shouldAddInvestigator(co.coinvesti_email9)) {
                UserProfiles userProfiles = (UserProfiles) data[0].get(co.coinvesti_email9.toUpperCase());
                if (userProfiles == null) {
                    userProfiles = helper.getUserProfile(co.coinvesti_email9, co.coinvesti_phone9, co.coinvesti_name9, co.coinvesti_sign_init9,
                            "", "", "", new Date(),
                            "", "", "", "", "", "yes", "Student", "no", "no",
                            co.coinvesti_address9, universities);
                    listUsers.add(userProfiles);
                }

            }

            if (shouldAddInvestigator(co.coinvesti_email10)) {
                UserProfiles userProfiles = (UserProfiles) data[0].get(co.coinvesti_email10.toUpperCase());
                if (userProfiles == null) {
                    userProfiles = helper.getUserProfile(co.coinvesti_email10, co.coinvesti_phone10, co.coinvesti_name10, co.coinvesti_sign_init10,
                            "", "", "", new Date(),
                            "", "", "", "", "", "yes", "Student", "no", "no",
                            co.coinvesti_address10, universities);
                    listUsers.add(userProfiles);
                }

            }

            if (shouldAddInvestigator(co.coinvesti_email11)) {
                UserProfiles userProfiles = (UserProfiles) data[0].get(co.coinvesti_email11.toUpperCase());
                if (userProfiles == null) {
                    userProfiles = helper.getUserProfile(co.coinvesti_email11, co.coinvesti_phone11, co.coinvesti_name11, co.coinvesti_sign_init11,
                            "", "", "", new Date(),
                            "", "", "", "", "", "yes", "Student", "no", "no",
                            co.coinvesti_address11, universities);
                    listUsers.add(userProfiles);
                }

            }

            if (shouldAddInvestigator(co.coinvesti_sign_email12)) {
                UserProfiles userProfiles = (UserProfiles) data[0].get(co.coinvesti_sign_email12.toUpperCase());
                if (userProfiles == null) {
                    userProfiles = helper.getUserProfile(co.coinvesti_sign_email12, co.coinvesti_sign_phone12, co.coinvesti_name12, co.coinvesti_sign_init12,
                            "", "", "", new Date(),
                            "", "", "", "", "", "yes", "Student", "no", "no",
                            co.coinvesti_address12, universities);
                    listUsers.add(userProfiles);
                }

            }

            if (shouldAddInvestigator(co.coinvesti_email13)) {
                UserProfiles userProfiles = (UserProfiles) data[0].get(co.coinvesti_email13.toUpperCase());
                if (userProfiles == null) {
                    userProfiles = helper.getUserProfile(co.coinvesti_email13, co.coinvesti_phone13, co.coinvesti_name13, co.coinvesti_sign_init13,
                            "", "", "", new Date(),
                            "", "", "", "", "", "yes", "Student", "no", "no",
                            co.coinvesti_address13, universities);
                    listUsers.add(userProfiles);
                }

            }

            if (shouldAddInvestigator(co.coinvesti_email14)) {
                UserProfiles userProfiles = (UserProfiles) data[0].get(co.coinvesti_email14.toUpperCase());
                if (userProfiles == null) {
                    userProfiles = helper.getUserProfile(co.coinvesti_email14, co.coinvesti_phone14, co.coinvesti_name14, co.coinvesti_sign_init14,
                            "", "", "", new Date(),
                            "", "", "", "", "", "yes", "Student", "no", "no",
                            co.coinvesti_address14, universities);
                    listUsers.add(userProfiles);
                }

            }

            if (shouldAddInvestigator(co.coinvesti_email15)) {
                UserProfiles userProfiles = (UserProfiles) data[0].get(co.coinvesti_email15.toUpperCase());
                if (userProfiles == null) {
                    userProfiles = helper.getUserProfile(co.coinvesti_email15, co.coinvesti_phone15, co.coinvesti_name15, co.coinvesti_sign_init15,
                            "", "", "", new Date(),
                            "", "", "", "", "", "yes", "Student", "no", "no",
                            co.coinvesti_address15, universities);
                    listUsers.add(userProfiles);
                }

            }

        }
        return listUsers;
    }

    private boolean shouldAddInvestigator(String email) {
        return !Strings.isNullOrEmpty(email);
    }


}
