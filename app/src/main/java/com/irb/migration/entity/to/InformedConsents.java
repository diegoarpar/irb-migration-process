package com.irb.migration.entity.to;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "InformedConsents")
public class InformedConsents {

    @jakarta.persistence.Id
    @Column(name = "Id")
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
    public Boolean SubjectWithdraw;

    @Column(name = "SubjectWithdrawOther")
    public String SubjectWithdrawOther;

    @Column(name = "WrittenConsent")
    public Boolean WrittenConsent;

    @Column(name = "WrittenConsentOther")
    public String WrittenConsentOther;

    @Column(name = "OralConsent")
    public Boolean OralConsent;

    @Column(name = "OralConsentIOther")
    public String OralConsentIOther;

    @Column(name = "MinorParticipate")
    public Boolean MinorParticipate;

    @Column(name = "MinorParticipateOther")
    public String MinorParticipateOther;

    @Column(name = "MinorSubject")
    public Boolean MinorSubject;

    @Column(name = "MinorSubjectOther")
    public String MinorSubjectOther;

    @Column(name = "PrincipalConcent")
    public Boolean PrincipalConcent;

    @Column(name = "PrincipalConcentOther")
    public String PrincipalConcentOther;

    @Column(name = "R01")
    public Boolean R01;

    @Column(name = "R02")
    public Boolean R02;

    @Column(name = "R03")
    public Boolean R03;

    @Column(name = "R04")
    public Boolean R04;

    @Column(name = "R05")
    public Boolean R05;

    @Column(name = "R06")
    public Boolean R06;

    @Column(name = "R07")
    public Boolean R07;

    @Column(name = "R08")
    public Boolean R08;

    @Column(name = "R09")
    public Boolean R09;

    @Column(name = "R10")
    public Boolean R10;

    @Column(name = "R11")
    public Boolean R11;

    @Column(name = "R12")
    public Boolean R12;

    @Column(name = "R13")
    public Boolean R13;

    @Column(name = "R14")
    public Boolean R14;

    @Column(name = "R15")
    public Boolean R15;

    @Column(name = "R16")
    public Boolean R16;

    @Column(name = "R17")
    public Boolean R17;

    @Column(name = "R01R17Other")
    public String R01R17Other;

    @Column(name = "R18")
    public Boolean R18;

    @Column(name = "R19")
    public Boolean R19;

    @Column(name = "R20")
    public Boolean R20;

    @Column(name = "R21")
    public Boolean R21;

    @Column(name = "R22")
    public Boolean R22;

    @Column(name = "R23")
    public Boolean R23;

    @Column(name = "R24")
    public Boolean R24;

    @Column(name = "R25")
    public Boolean R25;

    @Column(name = "R26")
    public Boolean R26;

    @Column(name = "R27")
    public Boolean R27;

    @Column(name = "R28")
    public Boolean R28;

    @Column(name = "R29")
    public Boolean R29;

    @Column(name = "R30")
    public Boolean R30;

    @Column(name = "R31")
    public Boolean R31;

    @Column(name = "CreatedDate", columnDefinition = "DATE")
    public Date CreatedDate;

    @Column(name = "UpdatedDate", columnDefinition = "DATE")
    public Date UpdatedDate;

}
