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
        ETLUsers etlUsers = Guice.createInjector().getInstance(ETLUsers.class);
        etlUsers.StartETL();


        ETLApplications etlApplications = Guice.createInjector().getInstance(ETLApplications.class);
        etlApplications.StartETL();

        ETLSponsors etlSponsors = Guice.createInjector().getInstance(ETLSponsors.class);
        etlSponsors.StartETL();

        ETLScreening etlSpcreening = Guice.createInjector().getInstance(ETLScreening.class);
        etlSpcreening.StartETL();

        ETLCoInvestigator etlCoInvestigator = Guice.createInjector().getInstance(ETLCoInvestigator.class);
        etlCoInvestigator.StartETL();

        System.out.println(new App().getGreeting());
    }
}
