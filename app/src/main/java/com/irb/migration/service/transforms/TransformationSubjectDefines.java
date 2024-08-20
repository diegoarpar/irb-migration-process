package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FSubjectDef;
import com.irb.migration.entity.to.IrbApplications;
import com.irb.migration.entity.to.SubjectDefines;
import com.irb.migration.service.transforms.helpers.Helper;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransformationSubjectDefines implements IETLTransformation<SubjectDefines, FSubjectDef> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransformationSubjectDefines.class.getName());


    @Inject
    public Helper helper;
    public List<SubjectDefines> TransformData(List<FSubjectDef> sourceData) {
        return List.of();
    }

    @Override
    public List<SubjectDefines> TransformData(List<FSubjectDef> origin, Map... data) {
        return origin.stream().map(source -> {

            IrbApplications application = (IrbApplications) data[1].get(source.application_id.toUpperCase());
            if (application == null) {
                LOGGER.error("MIGRATION: IRB does not exist when migrate subject def " + source.application_id);
                return null;
            }
            SubjectDefines subjectDefines = new SubjectDefines();

            subjectDefines.IrbApplicationId = application;
            subjectDefines.UserId = application.UserId;
            subjectDefines.ResearchSubject = source.research_sub;
            subjectDefines.Population = getPopulation(source.research_sub_population);
            subjectDefines.PopulationOther = source.research_sub_population_other;
            subjectDefines.SafeGuard = source.safeguard;
            subjectDefines.NumSubRecruited = Objects.isNull(source.approx_sub_recruited)? -1: source.approx_sub_recruited;
            subjectDefines.SubRecruitedTypes = getSubRecrutType(source.sub_recruited);
            subjectDefines.SubRecruitedOther = source.sub_recruited_other;
            subjectDefines.isAvailableEveryOne = 0;
            subjectDefines.isIncludeGannonStudents = source.research_sub_population != null && source.research_sub_population.contains("Students")? 1: 0;
            subjectDefines.isInformationIdentifiable = 0;


            subjectDefines.CreatedDate = application.SubmittedDate != null? application.SubmittedDate: new Date();
            subjectDefines.UpdatedDate = subjectDefines.CreatedDate;

            return subjectDefines;
        }).filter(Objects::nonNull).collect(Collectors.toList());

    }

    private String getPopulation(String typeOfConsent) {
        String consent = "1|Pregnant Individuals/Fetuses|%s, 2|Prisoners|%s, 3|Children (anyone under the age of 18)|%s, 4|Persons with Mental health/Developmental/Intellectual|%s, 5|DisabilitiesPersons with Mentally/Emotionally/Developmentally Disabilities |%s, 6|Minority Group(s) and Non-English Speakers|%s, 7|Elderly Subjects (65+)|%s, 8|Inpatients (e.g. Hospital setting / inpatient rehabilitation)|%s, 9|Residential Facilities (e.g. Assisted living / long term nursing home facility)|%s, 10|Not applicable|%s, 11|Employees|%s, 12|Other|%s";
        String pregnant1 = contains("pregnant", typeOfConsent);
        String prisoners2 = contains("Prisioners", typeOfConsent);
        String children3 = contains("minor", typeOfConsent);
        String mental4 = contains("cognitive", typeOfConsent);
        String emotion5 = contains("physically", typeOfConsent);
        String noenglish6 = contains("speakers", typeOfConsent);
        String elder7 = contains("Senior", typeOfConsent);
        String impatients8 = contains("Terminal", typeOfConsent);
        String residential9 = contains("residents", typeOfConsent);
        String noapply10 = contains("N/A", typeOfConsent);
        String employees11 = contains("Employees", typeOfConsent);
        String other12= contains("other", typeOfConsent);
        return String.format(consent, pregnant1, prisoners2, children3, mental4, emotion5, noenglish6
        , elder7, impatients8, residential9, noapply10, employees11, other12);
    }

    private String getSubRecrutType(String typeOfConsent) {
        String consent = "1|Advertisements|%s, 2|Telephone Lists|%s, 3|Letters|%s, 4|Notices|%s, 5|Random Calls|%s, 6|Direct Solicitation|%s, 7|Other (Specify)|%se";
        String adv1 = contains("Advertisements", typeOfConsent);
        String telephone2 = contains("Lists", typeOfConsent);
        String letters3 = contains("Letters", typeOfConsent);
        String notices4 = contains("Notices", typeOfConsent);
        String random5 = contains("Random", typeOfConsent);
        String direct6 = contains("Direct", typeOfConsent);
        String other7 = contains("Other", typeOfConsent);
        return String.format(consent, adv1, telephone2, letters3, notices4, random5, direct6, other7);
    }

    private String contains(String word, String typeOfConsent) {
        if (Strings.isNullOrEmpty(typeOfConsent)) {
            return "False";
        }
        return typeOfConsent.contains(word) ? "True" : "False";
    }

}
