package com.irb.migration.service.transforms;

import com.google.common.base.Strings;
import com.irb.migration.entity.from.FDataHandling;
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

public class TransformationDataHandling implements IETLTransformation<DataHandling, FDataHandling> {

    @Inject
    public Helper helper;
    public List<DataHandling> TransformData(List<FDataHandling> sourceData) {
        return List.of();
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(TransformationDataHandling.class.getName());

    @Override
    public List<DataHandling> TransformData(List<FDataHandling> origin, Map... data) {
        return origin.stream().map(source -> {

            IrbApplications application = (IrbApplications) data[1].get(source.application_id.toUpperCase());
            if (application == null) {
                LOGGER.error("MIGRATION: IRB does not exist when migrate data-handling " + source.application_id);
                return null;
            }
            DataHandling dataHandling = new DataHandling();

            dataHandling.IrbApplicationId = application;
            dataHandling.UserId = application.UserId;
            dataHandling.CollectMethod = getCollectMethod(source.data_collect_method);
            dataHandling.CollectMethodOther = source.data_collect_method + " " +source.field53other;
            dataHandling.Identifier = "yes".equalsIgnoreCase(source.data_collect_identi);
            dataHandling.Analysis = "yes".equalsIgnoreCase(source.data_retain_identi_analysis);
            dataHandling.Reporting = "yes".equalsIgnoreCase(source.data_retain_identi_reporting);
            dataHandling.Disseminated = source.research_dissem_sub;
            dataHandling.Field58a = source.storage_arrang;
            dataHandling.Field58b = source.acc_person;
            dataHandling.Field58c = source.sch_mthd_even_destruct;
            dataHandling.Field58d = source.sch_mthd_raw_data;
            dataHandling.Field58e = source.sch_mthd_even_destruct;
            dataHandling.CreatedDate = application.SubmittedDate != null? application.SubmittedDate: new Date();
            dataHandling.UpdatedDate = dataHandling.CreatedDate;

            return dataHandling;
        }).filter(Objects::nonNull).collect(Collectors.toList());

    }

    private String getCollectMethod(String dataCollectMethod) {
        String consent = "1|Questionnaire or Survey|%s, 2|Intervention|%s, 3|Interview|%s, 4|Focus Group|%s,5|Observation|%s, 6|Testing / Evaluation|%s, 7|Video or Audio Tapping|%s, 8|Instruction / Curriculum|%s,9|Computer Collected Data|%s, 10|Physical Tasks|%s, 11|Archival Data|%s, 12|Other(Specify)|%s";
        String survey1 = contains("Survey", dataCollectMethod);
        String intervention2 = contains("Intervention", dataCollectMethod);
        String interview3 = contains("Interview", dataCollectMethod);
        String focusgroup4 = contains("Focus", dataCollectMethod);
        String observation5 = contains("Observation", dataCollectMethod);
        String testing6 = contains("Testing", dataCollectMethod);
        String videoaudio7 = contains("Video", dataCollectMethod);
        String instructtion8 = contains("Instruction", dataCollectMethod);
        String collectdata9 = contains("Computer", dataCollectMethod);
        String physicaltask10 = contains("Physical", dataCollectMethod);
        String archivaldata11 = contains("Archival", dataCollectMethod);
        String other12= contains("Other", dataCollectMethod);
        return String.format(consent, survey1, intervention2, interview3, focusgroup4, observation5, testing6
                , videoaudio7, instructtion8, collectdata9, physicaltask10, archivaldata11, other12);
    }

    private String contains(String word, String typeOfConsent) {
        if (Strings.isNullOrEmpty(typeOfConsent)) {
            return "False";
        }
        return typeOfConsent.toLowerCase().contains(word.toLowerCase()) ? "True" : "False";
    }


}
