package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FApplicationFormBasic;
import com.irb.migration.entity.to.*;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransformationFacultySponsors implements ETLTransformation<FacultySponsors, FApplicationFormBasic> {

    @Inject
    public Helper helper;
    public List<FacultySponsors> TransformData(List<FApplicationFormBasic> sourceData) {
        return List.of();
    }

    @Override
    public List<FacultySponsors> TransformData(List<FApplicationFormBasic> origin, Map... data) {
        return origin.stream().map(source -> {
            if (Strings.isNullOrEmpty(source.email) || "Not Required".equalsIgnoreCase(source.factdtlvalidate)
                    || "N/A".equalsIgnoreCase(source.sponsor_name_degree)
                    || "N/A".equalsIgnoreCase(source.faculty_sponsor_sign)
                    || "N/A".equalsIgnoreCase(source.email)) {
                return null;
            }
            IrbApplications application = (IrbApplications) data[1].get(source.application_id.toUpperCase());
            AspNetUsers sponsor = (AspNetUsers) data[0].get(source.email.toUpperCase());
            if (application == null) {
                return null;
            }
            if (sponsor == null) {
                sponsor = new AspNetUsers();
                sponsor.NormalizedEmail  = source.email.toUpperCase();
                sponsor.Email = source.email;
                sponsor.UserName = source.email;
                data[0].put(sponsor.NormalizedEmail, sponsor);
            }

            FacultySponsors facultySponsors = new FacultySponsors();

            facultySponsors.IrbApplicationId = application;
            facultySponsors.UserId = sponsor;
            facultySponsors.SponsorDegree = source.sponsor_name_degree;
            facultySponsors.Email = source.email;
            facultySponsors.Fullname = String.format("%s %s", sponsor.NormalizedEmail, sponsor.NormalizedEmail);
            facultySponsors.ReseachDescription = source.description;
            facultySponsors.Address = source.faculty_sponsor_office;
            facultySponsors.Signature = source.faculty_sponsor_sign;
            facultySponsors.IsApproved = "approved".equalsIgnoreCase(source.factdtlvalidate)? 1: 0;
            return facultySponsors;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }


}
