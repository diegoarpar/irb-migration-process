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
            String role = getRoles(o.user_type, o.IsUserAdmin, o.HasAdminPrivilages);
            AspNetUsers user = (AspNetUsers) data[0].get(o.gu_email.toUpperCase());
            String[] roles = role.split(",");
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

    private String getRoles(String userType, String isUserAdmin, String hasAdminPrivilages) {
        String role = "";
        if (!Strings.isNullOrEmpty(userType)) {
            role = switch (userType) {
                case "Student" -> "student";
                case "IRB Staff" -> "irbmember";
                case "GU Staff" -> "irbchair";
                case "Faculty" -> "faculty";
                case "Admin" -> "irbchair,admin";
                default -> role;
            };
            if ("yes".equalsIgnoreCase(isUserAdmin) || "yes".equalsIgnoreCase(hasAdminPrivilages)) {
                if (!Strings.isNullOrEmpty(role) && !role.contains("admin")) {
                    role = String.format("%s,%s", role, "admin");
                } else {
                    role = "irbchair,admin";
                }
            }
        }
        return role;
    }

}
