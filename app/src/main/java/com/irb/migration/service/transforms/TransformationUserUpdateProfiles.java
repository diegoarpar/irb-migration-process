package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FUserDetails;
import com.irb.migration.entity.to.UserProfiles;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransformationUserUpdateProfiles implements IETLTransformation<UserProfiles, FUserDetails> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransformationUserUpdateProfiles.class.getName());


    @Inject
    public Helper helper;
    public List<UserProfiles> TransformData(List<FUserDetails> sourceData) {
        return new ArrayList<>();
    }

    @Override
    public List<UserProfiles> TransformData(List<FUserDetails> origin, Map... data) {
        List<UserProfiles> userProfilesList = new ArrayList<>();

        for(FUserDetails fu : origin) {
            if(Strings.isNullOrEmpty(fu.gu_email)) {
                continue;
            }
            UserProfiles p = (UserProfiles) data[0].get(fu.gu_email.toUpperCase());
            if (p == null) {
                continue;
            }
            p.Role = helper.getRoles(fu.user_type, fu.IsUserAdmin, fu.HasAdminPrivilages);
            userProfilesList.add(p);
        }
        return userProfilesList;
    }


}
