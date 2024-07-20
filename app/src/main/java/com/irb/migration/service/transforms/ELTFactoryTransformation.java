package com.irb.migration.service.transforms;

import com.google.inject.Guice;

public class ELTFactoryTransformation {

    public ETLTransformation getTransformation(String key) {

        switch (key) {
            case "application" : return Guice.createInjector().getInstance(TransformationApplication.class);
            case "user" : return Guice.createInjector().getInstance(TransformationUserProfiles.class);
            case "sponsor" : return Guice.createInjector().getInstance(TransformationFacultySponsors.class);
            case "screening" : return Guice.createInjector().getInstance(TransformationScreening.class);
            case "coinvestigator" : return Guice.createInjector().getInstance(TransformationCoInvestigator.class);
        }

        return null;

    }
}
