package com.irb.migration.entity.from;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "tblirbappsubdefindtl")
public class FSubjectDef {

    @Id
    @Column(name = "application_id")
    public String application_id;

    @Column(name = "research_sub")
    public String research_sub;

    @Column(name = "research_sub_population")
    public String research_sub_population;

    @Column(name = "safeguard")
    public String safeguard;

    @Column(name = "approx_sub_recruited")
    public Integer approx_sub_recruited;

    @Column(name = "sub_recruited")
    public String sub_recruited;

    @Column(name = "notes")
    public String notes;

    @Column(name = "research_sub_population_other")
    public String research_sub_population_other;

    @Column(name = "sub_recruited_other")
    public String sub_recruited_other;
}
