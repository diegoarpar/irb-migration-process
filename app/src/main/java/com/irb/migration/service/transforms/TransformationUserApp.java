package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FApplicationFormBasic;
import com.irb.migration.entity.to.Universities;
import com.irb.migration.entity.to.UserProfiles;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TransformationUserApp implements IETLTransformation<UserProfiles, FApplicationFormBasic> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransformationUserApp.class.getName());


    @Inject
    public Helper helper;
    public List<UserProfiles> TransformData(List<FApplicationFormBasic> sourceData) {
        return List.of();
    }

    @Override
    public List<UserProfiles> TransformData(List<FApplicationFormBasic> origin, Map... data) {
        List<UserProfiles> listUsers = new ArrayList<>();

        Universities universities = (Universities) data[1].get("gannon");

        for (FApplicationFormBasic app : origin) {

            //gu_email
            if (!Strings.isNullOrEmpty(app.gu_email)) {
                UserProfiles userProfiles = (UserProfiles) data[0].get(app.gu_email.toUpperCase());
                if (userProfiles == null) {
                    userProfiles = helper.getUserProfile(app.gu_email, app.telephone, app.principalinvestigator, app.princi_investi_sign,
                            "", "", "", helper.toDateSlash(app.date_of_submission),
                            "", "", "", "", "", "1", "Student", "no", "no",
                            app.investigator_mail_add, universities);

                    data[0].put(userProfiles.UserId.NormalizedEmail, userProfiles);
                    listUsers.add(userProfiles);
                }
            }

            //email faculty
            if (!Strings.isNullOrEmpty(app.email)) {
                UserProfiles userProfiles = (UserProfiles) data[0].get(app.email.toUpperCase());
                if (userProfiles == null) {
                    userProfiles = helper.getUserProfile(app.email, app.telephone, app.sponsor_name_degree, app.faculty_sponsor_sign, "",
                            "", "", new Date(), "", "", "",
                            "", "", "1", "Faculty", "no", "no"
                            , app.faculty_sponsor_office, universities);

                    data[0].put(userProfiles.UserId.NormalizedEmail, userProfiles);
                    listUsers.add(userProfiles);
                }
            }



        }
        return listUsers;

    }


}
