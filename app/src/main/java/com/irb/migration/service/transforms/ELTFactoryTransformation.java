package com.irb.migration.service.transforms;

import com.google.inject.Guice;
import com.irb.migration.entity.from.ApplicationFormBasic;
import com.irb.migration.entity.from.UserDetails;

public class ELTFactoryTransformation {

    public ETLTransformation getTransformation(Object object) {

        if (object instanceof UserDetails) {
            return  Guice.createInjector().getInstance(UserProfilesTransformation.class);
        } else if (object instanceof ApplicationFormBasic) {
            return  Guice.createInjector().getInstance(ApplicationTransformation.class);
        }
        return null;

    }
}
