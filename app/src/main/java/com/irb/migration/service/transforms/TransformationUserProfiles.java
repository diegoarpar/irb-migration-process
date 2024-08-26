package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FUserDetails;
import com.irb.migration.entity.to.AspNetUsers;
import com.irb.migration.entity.to.Universities;
import com.irb.migration.entity.to.UserProfiles;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransformationUserProfiles implements IETLTransformation<UserProfiles, FUserDetails> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransformationUserProfiles.class.getName());


    @Inject
    public Helper helper;
    public List<UserProfiles> TransformData(List<FUserDetails> sourceData) {
        Universities universities = new Universities();
        universities.Name = "Gannon University";
        universities.CreatedDate = new Date();
        return sourceData.stream().map(source -> {
            return helper.getUserProfile(source.gu_email, source.contact_no, source.firstname,
                    source.last_name, source.middle_name, source.title, source.img, source.reg_date,
                    source.last_visit, source.campus, source.department, source.research_area,
                    source.gender, source.admin_approval , source.user_type, source.IsUserAdmin, source.HasAdminPrivilages, "", universities);
        }).collect(Collectors.toList());
    }

    @Override
    public List<UserProfiles> TransformData(List<FUserDetails> origin, Map... data) {
        return List.of();
    }


}
