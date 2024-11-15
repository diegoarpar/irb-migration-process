package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FUserDetails;
import com.irb.migration.entity.to.AspNetUserClaims;
import com.irb.migration.entity.to.AspNetUsers;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

public class TransformationUserClaims implements IETLTransformation<AspNetUserClaims, FUserDetails> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransformationUserClaims.class.getName());


    @Inject
    public Helper helper;
    public List<AspNetUserClaims> TransformData(List<FUserDetails> sourceData) {
        return List.of();
    }

    @Override
    public List<AspNetUserClaims> TransformData(List<FUserDetails> origin, Map... data) {
        List<AspNetUserClaims> list = new ArrayList<>();
        for (FUserDetails o : origin ) {
            String role = helper.getRoles(o.user_type, o.IsUserAdmin, o.HasAdminPrivilages);
            AspNetUsers user = (AspNetUsers) data[0].get(o.gu_email.toUpperCase());
            String[] roles = role.split(",");
            if (user == null) {
                continue;
            }
            for (String s : roles) {
                AspNetUserClaims aspNetUserClaims = new AspNetUserClaims();
                aspNetUserClaims.UserId = user;
                aspNetUserClaims.ClaimType ="role";
                aspNetUserClaims.ClaimValue = s;
                list.add(aspNetUserClaims);
            }
        }
        return list;
    }



}
