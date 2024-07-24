package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FApplicationFormBasic;
import com.irb.migration.entity.from.FUserDetails;
import com.irb.migration.entity.to.AspNetUserClaims;
import com.irb.migration.entity.to.AspNetUsers;
import com.irb.migration.entity.to.FacultySponsors;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransformationUserClaims implements IETLTransformation<AspNetUserClaims, FUserDetails> {

    @Inject
    public Helper helper;
    public List<AspNetUserClaims> TransformData(List<FUserDetails> sourceData) {
        return List.of();
    }

    @Override
    public List<AspNetUserClaims> TransformData(List<FUserDetails> origin, Map... data) {
        return origin.stream().map(source -> {
            AspNetUsers user = (AspNetUsers) data[0].get(source.gu_email.toUpperCase());
            AspNetUserClaims aspNetUserClaims = new AspNetUserClaims();
            aspNetUserClaims.UserId = user;
            aspNetUserClaims.ClaimType ="role";
            aspNetUserClaims.ClaimValue = getRoles(source.user_type, source.IsUserAdmin, source.HasAdminPrivilages);
            return aspNetUserClaims;
        }).collect(Collectors.toList());
    }

    private String getRoles(String userType, String isUserAdmin, String hasAdminPrivilages) {
        String role = "";
        if (!Strings.isNullOrEmpty(userType)) {
            role = switch (userType) {
                case "Student" -> "student";
                case "IRB Staff" -> "irbmember";
                case "GU Staff" -> "irbchair";
                case "Faculty" -> "faculty";
                case "Admin" ->  "admin";
                default -> role;
            };
            if ("yes".equalsIgnoreCase(isUserAdmin) || "yes".equalsIgnoreCase(hasAdminPrivilages)) {
                    role = "admin";
            }

        }
        return role;
    }

}
