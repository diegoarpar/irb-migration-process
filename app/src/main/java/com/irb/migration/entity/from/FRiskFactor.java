package com.irb.migration.entity.from;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "tblirbappformriskfactdtl")
public class FRiskFactor {

    @Id
    @Column(name = "application_id")
    public String application_id;

    @Column(name = "sub_risk")
    public String sub_risk;

    @Column(name = "exp_drug")
    public String exp_drug;

    @Column(name = "medical_prob")
    public String medical_prob;

    @Column(name = "sub_phys_discomfort")
    public String sub_phys_discomfort;

    @Column(name = "sub_mental_discomfort")
    public String sub_mental_discomfort;

    @Column(name = "elec_equip")
    public String elec_equip;

    @Column(name = "sub_record")
    public String sub_record;

    @Column(name = "coercion_sub")
    public String coercion_sub;

    @Column(name = "sub_deception")
    public String sub_deception;

    @Column(name = "exp_dtl")
    public String exp_dtl;

    @Column(name = "possible_benefits")
    public String possible_benefits;

    @Column(name = "contri_to_gk")
    public String contri_to_gk;

    @Column(name = "sub_rewarded_compen")
    public String sub_rewarded_compen;

    @Column(name = "field43")
    public String field43;

    @Column(name = "field44")
    public String field44;


    @Column(name = "notes")
    public String notes;
}
