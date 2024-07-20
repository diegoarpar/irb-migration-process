package com.irb.migration.service.transforms.helpers;

import jakarta.inject.Singleton;

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
}
