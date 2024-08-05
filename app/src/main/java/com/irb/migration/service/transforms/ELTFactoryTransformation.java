package com.irb.migration.service.transforms;

import com.google.inject.Guice;

public class ELTFactoryTransformation {

    public IETLTransformation getTransformation(String key) {

        switch (key) {
            case "application" : return Guice.createInjector().getInstance(TransformationApplication.class);
            case "user" : return Guice.createInjector().getInstance(TransformationUserProfiles.class);
            case "sponsor" : return Guice.createInjector().getInstance(TransformationFacultySponsors.class);
            case "screening" : return Guice.createInjector().getInstance(TransformationScreening.class);
            case "coinvestigator" : return Guice.createInjector().getInstance(TransformationCoInvestigator.class);
            case "datahandling" : return Guice.createInjector().getInstance(TransformationDataHandling.class);
            case "userclaim" : return Guice.createInjector().getInstance(TransformationUserClaims.class);
            case "issues" : return Guice.createInjector().getInstance(TransformationIssues.class);
            case "expedited" : return Guice.createInjector().getInstance(TransformationExpedited.class);
            case "exempted" : return Guice.createInjector().getInstance(TransformationExempted.class);
            case "email" : return Guice.createInjector().getInstance(TransformationTransactionLogsEmail.class);
            case "changeuser" : return Guice.createInjector().getInstance(TransformationTransactionLogsChangeUser.class);
            case "reviewersapp" : return Guice.createInjector().getInstance(TransformationReviewersApp.class);
            case "vote" : return Guice.createInjector().getInstance(TransformationVote.class);
            case "informed" : return Guice.createInjector().getInstance(TransformationInformedConsent.class);
        }

        return null;

    }
}
