package com.irb.migration.entity.from;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "tblirbappformdatahanddtl")
public class FDataHandling {

    @Id
    @Column(name = "application_id")
    public String application_id;

    @Column(name = "data_collect_method")
    public String data_collect_method;

    @Column(name = "field53other")
    public String field53other;

    @Column(name = "data_collect_identi")
    public String data_collect_identi;

    @Column(name = "data_retain_identi_analysis")
    public String data_retain_identi_analysis;

    @Column(name = "data_retain_identi_reporting")
    public String data_retain_identi_reporting;

    @Column(name = "research_dissem_sub")
    public String research_dissem_sub;

    @Column(name = "storage_arrang")
    public String storage_arrang;

    @Column(name = "acc_person")
    public String acc_person;

    @Column(name = "sch_mthd_deidenti_data")
    public String sch_mthd_deidenti_data;

    @Column(name = "sch_mthd_raw_data")
    public String sch_mthd_raw_data;

    @Column(name = "sch_mthd_even_destruct")
    public String sch_mthd_even_destruct;

    @Column(name = "notes")
    public String notes;
}
