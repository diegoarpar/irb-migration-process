package com.irb.migration.entity.from;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "tblresearchstddtl")
public class FResearchStudy {

    @Id
    @Column(name = "application_id")
    public String application_id;

    @Column(name = "research_std_proposal")
    public String research_std_proposal;

    @Column(name = "fund_agency")
    public String fund_agency;

    @Column(name = "irb_appvr_req")
    public String irb_appvr_req;

    @Column(name = "data_start_day")
    public String data_start_day;

    @Column(name = "data_end_day")
    public String data_end_day;

    @Column(name = "projected_std_dur")
    public String projected_std_dur;

    @Column(name = "purpose")
    public String purpose;

    @Column(name = "methodology")
    public String methodology;

    @Column(name = "notes")
    public String notes;
}
