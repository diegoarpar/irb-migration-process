package com.irb.migration.entity.from;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "tblirbappformbasicdtl")
public class FApplicationFormBasic {

    @Id
    @Column(name = "application_id")
    public String application_id;

    @Column(name = "gu_email")
    public String gu_email;

    @Column(name = "typeofreview")
    public String typeofreview;

    @Column(name = "principalinvestigator")
    public String principalinvestigator;

    @Column(name = "date_of_submission")
    public String date_of_submission;

    @Column(name = "princi_investi_sign")
    public String princi_investi_sign;

    @Column(name = "title_of_research")
    public String title_of_research;

    @Column(name = "investigator_mail_add")
    public String investigator_mail_add;

    @Column(name = "investi_telephone")
    public String investi_telephone;

    @Column(name = "investi_email")
    public String investi_email;

    @Column(name = "status")
    public String status;

    @Column(name = "sponsor_name_degree")
    public String sponsor_name_degree;

    @Column(name = "faculty_sponsor_sign")
    public String faculty_sponsor_sign;

    @Column(name = "faculty_sponsor_office")
    public String faculty_sponsor_office;

    @Column(name = "mail_address")
    public String mail_address;

    @Column(name = "telephone")
    public String telephone;

    @Column(name = "email")
    public String email;

    @Column(name = "any_co_investi")
    public String any_co_investi;

    @Column(name = "appid_int")
    public Integer appid_int;

    @Column(name = "irbappstatus")
    public String irbappstatus;

    @Column(name = "factdtlvalidate")
    public String factdtlvalidate;

    @Column(name = "assignedstaff")
    public String assignedstaff;

    @Column(name = "month_of_submission")
    public String month_of_submission;

    @Column(name = "year_of_submission")
    public String year_of_submission;

    @Column(name = "coinvesti_name1")
    public String coinvesti_name1;

    @Column(name = "coinvesti_address1")
    public String coinvesti_address1;

    @Column(name = "coinvesti_sign_init1")
    public String coinvesti_sign_init1;

    @Column(name = "coinvesti_email1")
    public String coinvesti_email1;

    @Column(name = "coinvesti_phone1")
    public String coinvesti_phone1;

    @Column(name = "notes")
    public String notes;

    @Column(name = "description")
    public String description;

    @Column(name = "approved_with_conditions")
    public String approved_with_conditions;

    @Column(name = "approved_with_recommendations")
    public String approved_with_recommendations;

    @Column(name = "decision_date")
    public String decision_date;

    @Column(name = "reject_reason")
    public String reject_reason;

    @Column(name = "forwarded_date")
    public String forwarded_date;

    @Column(name = "irb_approval_expires")
    public String irb_approval_expires;

    @Column(name = "coinvesti_name2")
    public String coinvesti_name2;

    @Column(name = "coinvesti_address2")
    public String coinvesti_address2;

    @Column(name = "coinvesti_sign_init2")
    public String coinvesti_sign_init2;

    @Column(name = "coinvesti_email2")
    public String coinvesti_email2;

    @Column(name = "coinvesti_phone2")
    public String coinvesti_phone2;

    @Column(name = "coinvesti_name3")
    public String coinvesti_name3;

    @Column(name = "coinvesti_address3")
    public String coinvesti_address3;

    @Column(name = "coinvesti_sign_init3")
    public String coinvesti_sign_init3;

    @Column(name = "coinvesti_email3")
    public String coinvesti_email3;

    @Column(name = "coinvesti_phone3")
    public String coinvesti_phone3;

    @Column(name = "total_no_of_coinvesti")
    public Integer total_no_of_coinvesti;

    @Column(name = "editable_app")
    public String editable_app;

    @Column(name = "editable_app_reason")
    public String editable_app_reason;

    @Column(name = "approve_reason")
    public String approve_reason;

    @Column(name = "isphd")
    public String isphd;
}
