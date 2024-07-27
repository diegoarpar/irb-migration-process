/*
 * This source file was generated by the Gradle 'init' task
 */
package com.irb.migration;

import com.google.inject.Guice;
import com.irb.migration.service.ETL.*;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.setProperty("eclipselink.ddl-generation", "update");
        IETL etl = Guice.createInjector().getInstance(ETLUsers.class);
        etl.StartETL();

        etl = Guice.createInjector().getInstance(ETLUserClaims.class);
        etl.StartETL();

        etl = Guice.createInjector().getInstance(ETLApplications.class);
        etl.StartETL();

        etl = Guice.createInjector().getInstance(ETLSponsors.class);
        etl.StartETL();

        etl = Guice.createInjector().getInstance(ETLScreening.class);
        etl.StartETL();

        etl = Guice.createInjector().getInstance(ETLCoInvestigator.class);
        etl.StartETL();

        etl = Guice.createInjector().getInstance(ETLDatahandling.class);
        etl.StartETL();

        etl = Guice.createInjector().getInstance(ETLIssues.class);
        etl.StartETL();

        etl = Guice.createInjector().getInstance(ETLExpedited.class);
        etl.StartETL();

        etl = Guice.createInjector().getInstance(ETLExempted.class);
        etl.StartETL();

        etl = Guice.createInjector().getInstance(ETLTransactionsLogsEmail.class);
        etl.StartETL();

        etl = Guice.createInjector().getInstance(ETLTransactionsLogsChangeUserType.class);
        etl.StartETL();

        System.out.println(new App().getGreeting());
    }
}
