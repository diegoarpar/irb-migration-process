package com.irb.migration.service.transforms.helpers;

import com.google.common.base.Strings;
import com.irb.migration.entity.to.AspNetUsers;
import com.irb.migration.entity.to.Universities;
import com.irb.migration.entity.to.UserProfiles;
import jakarta.inject.Singleton;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

@Singleton
public class Helper {

    public Date toDate(String date) {
        try {
            return new SimpleDateFormat("yyyyMMddmmss").parse(date);
        } catch(Exception e) {
            return null;
        }
    }

    public String toCategory(String category) {
        if (Strings.isNullOrEmpty(category)) {
            return "";
        }
        String number = category.substring(category.length() -1);
        return String.format("%s %s", "Category", number);
    }

    public Date toDateSlash(String date) {
        try {
            return new SimpleDateFormat("MM/dd/yyyy").parse(date);
        } catch(Exception e) {
            return null;
        }
    }

    public Date toDateMinus(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch(Exception e) {
            return null;
        }
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public String generateRandomStamp() {
        SecureRandom random = new SecureRandom();
        int length = 32;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }

    public Integer fromYesNoToInt(String data) {
        return "yes".equalsIgnoreCase(data)? 1: 0;
    }

    public UserProfiles getUserProfile(String guEmail, String contactNo, String firstname,
                                       String lastName, String middleName, String title, String img, Date regDate,
                                       String lastVisit, String campus, String department,
                                       String researchArea, String gender, String admin_approval,
                                       String user_type, String IsUserAdmin, String HasAdminPrivilages, String address, Universities universities) {

        AspNetUsers aspNetUsers = new AspNetUsers();
        aspNetUsers.NormalizedEmail = guEmail.toUpperCase();
        aspNetUsers.Email = guEmail;
        aspNetUsers.EmailConfirmed = "1".equalsIgnoreCase(admin_approval)? 1: 0 ;
        aspNetUsers.UserName = guEmail;
        aspNetUsers.PhoneNumber = contactNo;
        aspNetUsers.TwoFactorEnabled = 0;
        aspNetUsers.LockoutEnabled = 1;
        aspNetUsers.AccessFailedCount = 0;
        aspNetUsers.NormalizedUserName = guEmail.toUpperCase();
        aspNetUsers.SecurityStamp =  generateRandomStamp();
        aspNetUsers.PhoneNumberConfirmed = Strings.isNullOrEmpty(contactNo)? 0: 1 ;

        UserProfiles userProfileUser = new UserProfiles();
        userProfileUser.UserId = aspNetUsers;
        userProfileUser.FirstName  = Strings.isNullOrEmpty(firstname)? "": firstname;
        userProfileUser.LastName = Strings.isNullOrEmpty(lastName)? "": lastName;
        userProfileUser.MiddleName = Strings.isNullOrEmpty(middleName)? "": middleName;
        userProfileUser.Title = title;
        userProfileUser.Picture = img;
        userProfileUser.CreatedDate = regDate;
        userProfileUser.LastLogin = toDate(lastVisit);
        userProfileUser.University = universities;
        userProfileUser.Campus = campus;
        userProfileUser.Address = address;
        userProfileUser.Department = department;
        userProfileUser.ResearchArea = researchArea;
        userProfileUser.Gender = gender;
        userProfileUser.Role = getRoles(user_type, IsUserAdmin, HasAdminPrivilages);
        userProfileUser.CreatedDate = new Date();
        return userProfileUser;
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
                if (!Strings.isNullOrEmpty(role) && !role.toLowerCase().contains("admin")) {
                    role = String.format("%s,%s", role, "admin");
                } else {
                    role = "irbchair,admin";
                }
            }
        }
        return role;
    }

    public String contains(String word, String typeOfConsent) {
        if (Strings.isNullOrEmpty(typeOfConsent)) {
            return "False";
        }
        return typeOfConsent.toLowerCase().contains(word.toLowerCase()) ? "True" : "False";
    }

    public String trueFalseFromYes(String word) {
        return "yes".equalsIgnoreCase(word)? "True": "False";
    }
}
