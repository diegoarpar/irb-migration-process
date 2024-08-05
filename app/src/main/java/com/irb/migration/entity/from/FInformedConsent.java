package com.irb.migration.entity.from;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "tblirbappforminfoconsentdtl")
public class FInformedConsent {

    @Id
    @Column(name = "application_id")
    public String application_id;

    @Column(name = "type_of_consent")
    public String type_of_consent;

    @Column(name = "consent_form_distri")
    public String consent_form_distri;

    @Column(name = "sub_inf_withdraw")
    public String sub_inf_withdraw;

    @Column(name = "writ_consent")
    public String writ_consent;

    @Column(name = "oral_consent")
    public String oral_consent;

    @Column(name = "minor_participate")
    public String minor_participate;

    @Column(name = "minor_sub_obtain")
    public String minor_sub_obtain;

    @Column(name = "princi_consent_waive")
    public String princi_consent_waive;

    @Column(name = "r01")
    public String r01;

    @Column(name = "r02")
    public String r02;

    @Column(name = "r03")
    public String r03;

    @Column(name = "r04")
    public String r04;

    @Column(name = "r05")
    public String r05;

    @Column(name = "r06")
    public String r06;

    @Column(name = "r07")
    public String r07;

    @Column(name = "r08")
    public String r08;

    @Column(name = "r09")
    public String r09;

    @Column(name = "r10")
    public String r10;

    @Column(name = "r11")
    public String r11;

    @Column(name = "r12")
    public String r12;

    @Column(name = "r13")
    public String r13;

    @Column(name = "r14")
    public String r14;

    @Column(name = "r15")
    public String r15;

    @Column(name = "r16")
    public String r16;

    @Column(name = "r17")
    public String r17;

    @Column(name = "exp_dtl_r1_to_r17")
    public String exp_dtl_r1_to_r17;

    @Column(name = "r18")
    public String r18;

    @Column(name = "r19")
    public String r19;

    @Column(name = "r20")
    public String r20;

    @Column(name = "r21")
    public String r21;

    @Column(name = "r22")
    public String r22;

    @Column(name = "r23")
    public String r23;

    @Column(name = "r24")
    public String r24;

    @Column(name = "r25")
    public String r25;

    @Column(name = "r26")
    public String r26;

    @Column(name = "r27")
    public String r27;

    @Column(name = "r28")
    public String r28;

    @Column(name = "r29")
    public String r29;

    @Column(name = "r30")
    public String r30;

    @Column(name = "r31")
    public String r31;

    @Column(name = "notes")
    public String notes;

    @Column(name = "sub_inf_withdraw_other")
    public String sub_inf_withdraw_other;

    @Column(name = "writ_consent_other")
    public String writ_consent_other;

    @Column(name = "oral_consent_other")
    public String oral_consent_other;

    @Column(name = "minor_participate_other")
    public String minor_participate_other;

    @Column(name = "minor_sub_obtain_other")
    public String minor_sub_obtain_other;

    @Column(name = "princi_consent_waive_other")
    public String princi_consent_waive_other;
}
