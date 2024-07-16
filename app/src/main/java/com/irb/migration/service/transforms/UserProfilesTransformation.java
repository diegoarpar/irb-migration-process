package com.irb.migration.service.transforms;

import com.irb.migration.entity.from.UserDetails;
import com.irb.migration.entity.to.UserProfiles;

import java.util.List;
import java.util.stream.Collectors;

public class UserProfilesTransformation implements ETLTransformation<UserProfiles, UserDetails> {

    public List<UserProfiles> TransformData(List<UserDetails> sourceData) {
        return sourceData.stream().map(source -> {
            UserProfiles dest = new UserProfiles();
            dest.FirstName  = source.firstname;
            dest.LastName = source.last_name;
            return dest;
        }).collect(Collectors.toList());
    }
}
