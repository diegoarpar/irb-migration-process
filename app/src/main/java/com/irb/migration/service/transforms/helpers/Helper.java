package com.irb.migration.service.transforms.helpers;

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

    public Date toDateSlash(String date) {
        try {
            return new SimpleDateFormat("MM/dd/yyyy").parse(date);
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

}
