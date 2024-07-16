package com.irb.migration.service.transforms;

import com.google.inject.Guice;
import com.irb.migration.entity.from.UserDetails;

public class ELTFactoryTransformation {

    public ETLTransformation getTransformation(Object object) {

        if (object instanceof UserDetails) {
            return  Guice.createInjector().getInstance(UserProfilesTransformation.class);
        }
        return null;

    }
}
