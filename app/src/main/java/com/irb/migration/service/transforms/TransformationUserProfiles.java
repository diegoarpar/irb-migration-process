package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FUserDetails;
import com.irb.migration.entity.to.AspNetUserClaims;
import com.irb.migration.entity.to.AspNetUsers;
import com.irb.migration.entity.to.Universities;
import com.irb.migration.entity.to.UserProfiles;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransformationUserProfiles implements IETLTransformation<UserProfiles, FUserDetails> {

    @Inject
    public Helper helper;
    public List<UserProfiles> TransformData(List<FUserDetails> sourceData) {
        Universities universities = new Universities();
        universities.Name = "Gannon University";
        return sourceData.stream().map(source -> {
            AspNetUserClaims aspNetUserClaims = new AspNetUserClaims();
            AspNetUsers aspNetUsers = new AspNetUsers();
            aspNetUsers.NormalizedEmail = source.gu_email.toUpperCase();
            aspNetUsers.Email = source.gu_email;
            aspNetUsers.EmailConfirmed = "1".equalsIgnoreCase(source.admin_approval)? 1: 0 ;
            aspNetUsers.UserName = source.gu_email;
            aspNetUsers.PhoneNumber = source.contact_no;
            aspNetUsers.PhoneNumberConfirmed = source.contact_no;
            aspNetUsers.TwoFactorEnabled = "0";
            aspNetUsers.LockoutEnabled = 1;
            aspNetUsers.AccessFailedCount = 0;

            UserProfiles userProfileUser = new UserProfiles();
            userProfileUser.UserId = aspNetUsers;
            userProfileUser.FirstName  = source.firstname;
            userProfileUser.LastName = source.last_name;
            userProfileUser.MiddleName = source.middle_name;
            userProfileUser.Title = source.title;
            userProfileUser.Picture = source.img;
            userProfileUser.CreatedDate = source.reg_date;
            userProfileUser.LastLogin = helper.toDate(source.last_visit);
            userProfileUser.University = universities;
            userProfileUser.Campus = source.campus;
            userProfileUser.Department = source.department;
            userProfileUser.ResearchArea = source.research_area;
            userProfileUser.Gender = source.gender;
            userProfileUser.Role = getRoles(source.user_type, source.IsUserAdmin, source.HasAdminPrivilages);

            aspNetUserClaims.UserId = aspNetUsers;
            aspNetUserClaims.ClaimType ="role";
            aspNetUserClaims.ClaimValue = source.user_type;


            return userProfileUser;
        }).collect(Collectors.toList());
    }

    @Override
    public List<UserProfiles> TransformData(List<FUserDetails> origin, Map... data) {
        return List.of();
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
