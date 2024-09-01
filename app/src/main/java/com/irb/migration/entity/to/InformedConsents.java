package com.irb.migration.entity.to;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "InformedConsents")
public class InformedConsents {

    @jakarta.persistence.Id
    @Column(name = "Id", columnDefinition = "NUMERIC(19,0)")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer Id;

    @ManyToOne
    @JoinColumn(name="IrbApplicationId", nullable=false)
    public IrbApplications IrbApplicationId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="UserId", nullable=false)
    public AspNetUsers  UserId;


    @Column(name = "ConsentType")
    public String ConsentType;

    @Lob
    @Column(name = "FormDistribution")
    public String FormDistribution;

    @Column(name = "SubjectWithdraw")
    public Integer SubjectWithdraw;

    @Column(name = "SubjectWithdrawOther")
    public String SubjectWithdrawOther;

    @Column(name = "WrittenConsent")
    public Integer WrittenConsent;

    @Column(name = "WrittenConsentOther")
    public String WrittenConsentOther;

    @Column(name = "OralConsent")
    public Integer OralConsent;

    @Column(name = "OralConsentIOther")
    public String OralConsentIOther;

    @Column(name = "MinorParticipate")
    public Integer MinorParticipate;

    @Column(name = "MinorParticipateOther")
    public String MinorParticipateOther;

    @Column(name = "MinorSubject")
    public Integer MinorSubject;

    @Column(name = "MinorSubjectOther")
    public String MinorSubjectOther;

    @Column(name = "PrincipalConcent")
    public Integer PrincipalConcent;

    @Column(name = "PrincipalConcentOther")
    public String PrincipalConcentOther;

    @Column(name = "R01")
    public Integer R01;

    @Column(name = "R02")
    public Integer R02;

    @Column(name = "R03")
    public Integer R03;

    @Column(name = "R04")
    public Integer R04;

    @Column(name = "R05")
    public Integer R05;

    @Column(name = "R06")
    public Integer R06;

    @Column(name = "R07")
    public Integer R07;

    @Column(name = "R08")
    public Integer R08;

    @Column(name = "R09")
    public Integer R09;

    @Column(name = "R10")
    public Integer R10;

    @Column(name = "R11")
    public Integer R11;

    @Column(name = "R12")
    public Integer R12;

    @Column(name = "R13")
    public Integer R13;

    @Column(name = "R14")
    public Integer R14;

    @Column(name = "R15")
    public Integer R15;

    @Column(name = "R16")
    public Integer R16;

    @Column(name = "R17")
    public Integer R17;

    @Column(name = "R01R17Other")
    public String R01R17Other;

    @Column(name = "R18")
    public Integer R18;

    @Column(name = "R19")
    public Integer R19;

    @Column(name = "R20")
    public Integer R20;

    @Column(name = "R21")
    public Integer R21;

    @Column(name = "R22")
    public Integer R22;

    @Column(name = "R23")
    public Integer R23;

    @Column(name = "R24")
    public Integer R24;

    @Column(name = "R25")
    public Integer R25;

    @Column(name = "R26")
    public Integer R26;

    @Column(name = "R27")
    public Integer R27;

    @Column(name = "R28")
    public Integer R28;

    @Column(name = "R29")
    public Integer R29;

    @Column(name = "R30")
    public Integer R30;

    @Column(name = "R31")
    public Integer R31;

    @Column(name = "CreatedDate", columnDefinition = "DATE")
    public Date CreatedDate;

    @Column(name = "UpdatedDate", columnDefinition = "DATE")
    public Date UpdatedDate;

}
