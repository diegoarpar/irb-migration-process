package com.irb.migration.entity.from;


import jakarta.persistence.*;


@Entity
@Table(name = "tblnotes")
public class FNotes {

    @Id
    @Column(name = "notes_id")
    public Integer notes_id;

    @Column(name = "user_fid")
    public String user_fid;

    @Column(name = "application_fid")
    public String application_fid;

    @Column(name = "notes_text")
    public String notes_text;

    @Column(name = "notes_subdefinedtl")
    public String notes_subdefinedtl;

    @Column(name = "notes_riskfactdtl")
    public String notes_riskfactdtl;

    @Column(name = "notes_researchdtl")
    public String notes_researchdtl;

    @Column(name = "notes_consentdtl")
    public String notes_consentdtl;

    @Column(name = "notes_datahandlingdtl")
    public String notes_datahandlingdtl;

    @Column(name = "notes_uploads")
    public String notes_uploads;
}
