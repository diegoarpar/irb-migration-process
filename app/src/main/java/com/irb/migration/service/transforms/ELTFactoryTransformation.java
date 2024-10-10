package com.irb.migration.service.transforms;

import com.google.inject.Guice;

public class ELTFactoryTransformation {

    public IETLTransformation getTransformation(String key) {

        switch (key) {
            case "application" : return Guice.createInjector().getInstance(TransformationApplication.class);
            case "user" : return Guice.createInjector().getInstance(TransformationUserProfiles.class);
            case "user_co" : return Guice.createInjector().getInstance(TransformationUserCoInv.class);
            case "user_app" : return Guice.createInjector().getInstance(TransformationUserApp.class);
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
            case "risk" : return Guice.createInjector().getInstance(TransformationRiskFactors.class);
            case "documents" : return Guice.createInjector().getInstance(TransformationDocuments.class);
            case "notificationforms" : return Guice.createInjector().getInstance(TransformationNotificationForms.class);
            case "notes" : return Guice.createInjector().getInstance(TransformationNotes.class);
            case "subject" : return Guice.createInjector().getInstance(TransformationSubjectDefines.class);
            case "research" : return Guice.createInjector().getInstance(TransformationResearchStudy.class);
            case "recruitment" : return Guice.createInjector().getInstance(TransformationRecruitment.class);
        }

        return null;

    }
}
