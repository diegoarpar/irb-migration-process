package com.irb.migration.service.transforms;

import com.irb.migration.entity.from.FScreening;
import com.irb.migration.entity.to.AspNetUsers;
import com.irb.migration.entity.to.Screenings;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransformationScreening implements IETLTransformation<Screenings, FScreening> {

    @Inject
    public Helper helper;
    public List<Screenings> TransformData(List<FScreening> sourceData) {
        return List.of();
    }

    @Override
    public List<Screenings> TransformData(List<FScreening> origin, Map... data) {
        return origin.stream().map(source -> {
            Screenings screenings = new Screenings();

            screenings.UserId = (AspNetUsers) data[0].get(source.guemail.toUpperCase());
            screenings.Question1 = source.q1;
            screenings.Question2 = source.q2;
            screenings.Question3 = source.q3;
            screenings.Question4 = source.q4;
            screenings.CreatedDate = source.submitdate;

            return screenings;
        }).collect(Collectors.toList());
    }


}
