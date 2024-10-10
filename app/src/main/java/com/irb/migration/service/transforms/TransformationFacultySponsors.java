package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FApplicationFormBasic;
import com.irb.migration.entity.to.*;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransformationFacultySponsors implements IETLTransformation<FacultySponsors, FApplicationFormBasic> {

    @Inject
    public Helper helper;
    public List<FacultySponsors> TransformData(List<FApplicationFormBasic> sourceData) {
        return List.of();
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(TransformationDocuments.class.getName());

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
            UserProfiles sponsor = (UserProfiles) data[0].get(source.email.toUpperCase());
            if (application == null) {
                LOGGER.error("MIGRATION: IRB does not exist when migrate sponsor " + source.application_id);
                return null;
            }

            FacultySponsors facultySponsors = new FacultySponsors();

            facultySponsors.IrbApplicationId = application;
            facultySponsors.UserId = sponsor.UserId;
            facultySponsors.SponsorDegree = source.sponsor_name_degree;
            facultySponsors.Email = source.email;
            facultySponsors.Fullname = String.format("%s %s", sponsor.FirstName, sponsor.UserId.NormalizedEmail);
            facultySponsors.ReseachDescription = source.description;
            facultySponsors.Address = source.faculty_sponsor_office;
            facultySponsors.Signature = source.faculty_sponsor_sign;
            facultySponsors.DecisionDate = application.SubmittedDate;
            facultySponsors.IsApproved = "approved".equalsIgnoreCase(source.factdtlvalidate);
            facultySponsors.CreatedDate = application.SubmittedDate;
            return facultySponsors;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }


}
