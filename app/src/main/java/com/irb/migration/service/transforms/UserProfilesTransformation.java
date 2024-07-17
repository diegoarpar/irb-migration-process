package com.irb.migration.service.transforms;

import com.irb.migration.entity.from.UserDetails;
import com.irb.migration.entity.to.AspNetUserClaims;
import com.irb.migration.entity.to.AspNetUsers;
import com.irb.migration.entity.to.Universities;
import com.irb.migration.entity.to.UserProfiles;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.stream.Collectors;

public class UserProfilesTransformation implements ETLTransformation<UserProfiles, UserDetails> {

    public List<UserProfiles> TransformData(List<UserDetails> sourceData) {
        Universities universities = new Universities();
        universities.Name = "Gannon University";
        return sourceData.stream().map(source -> {
            AspNetUserClaims aspNetUserClaims = new AspNetUserClaims();
            AspNetUsers aspNetUsers = new AspNetUsers();
            aspNetUsers.UserName = source.gu_email;
            aspNetUsers.NormalizedEmail = source.gu_email.toUpperCase();
            aspNetUsers.Email = source.gu_email;
            aspNetUsers.EmailConfirmed = "1".equalsIgnoreCase(source.admin_approval)? 1: 0 ;
            aspNetUsers.UserName = source.gu_email;
            aspNetUsers.PhoneNumber = source.contact_no;
            aspNetUsers.PhoneNumberConfirmed = source.contact_no;
            aspNetUsers.TwoFactorEnabled = "0";
            aspNetUsers.LockoutEnabled = 1;

            UserProfiles userProfileUser = new UserProfiles();
            userProfileUser.UserId = aspNetUsers;
            userProfileUser.FirstName  = source.firstname;
            userProfileUser.LastName = source.last_name;
            userProfileUser.MiddleName = source.middle_name;
            userProfileUser.Title = source.title;
            userProfileUser.Picture = source.img;
            userProfileUser.CreatedDate = source.reg_date;
            try {
                userProfileUser.LastLogin = new SimpleDateFormat("yyyyMMddmmss").parse(source.last_visit);
            } catch (Exception e) {

            }
            userProfileUser.University = universities;
            userProfileUser.Campus = source.campus;
            userProfileUser.Department = source.department;
            userProfileUser.ResearchArea = source.research_area;
            userProfileUser.Gender = source.gender;
            userProfileUser.Role = source.user_type;

            aspNetUserClaims.UserId = aspNetUsers;
            aspNetUserClaims.ClaimType ="role";
            aspNetUserClaims.ClaimValue = source.user_type;


            return userProfileUser;
        }).collect(Collectors.toList());
    }
}
