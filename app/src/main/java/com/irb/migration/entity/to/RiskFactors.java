package com.irb.migration.entity.to;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "RiskFactors")
public class RiskFactors {

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

    @Column(name = "SubjectRisk")
    public Boolean SubjectRisk;

    @Column(name = "ExperimentalDrug")
    public Boolean ExperimentalDrug;

    @Column(name = "MedicalProblem")
    public Boolean MedicalProblem;

    @Column(name = "PhysicalDiscomfort")
    public Boolean PhysicalDiscomfort;

    @Column(name = "MentalDiscomfort")
    public Boolean MentalDiscomfort;

    @Column(name = "ElectricalEquipment")
    public Boolean ElectricalEquipment;

    @Column(name = "SubjectRecord")
    public Boolean SubjectRecord;

    @Column(name = "SubjectCoercion")
    public Boolean SubjectCoercion;

    @Column(name = "SubjectDeception")
    public Boolean SubjectDeception;

    @Lob
    @Column(name = "CoercionDeceptionDetail")
    public String CoercionDeceptionDetail;

    @Lob
    @Column(name = "PossibleBenefit")
    public String PossibleBenefit;

    @Lob
    @Column(name = "Field43")
    public String Field43;

    @Lob
    @Column(name = "Field44")
    public String Field44;

    @Column(name = "CreatedDate", columnDefinition = "DATE")
    public Date CreatedDate;

    @Column(name = "UpdatedDate", columnDefinition = "DATE")
    public Date UpdatedDate;

    @Lob
    @Column(name = "Field42")
    public String Field42;

    @Lob
    @Column(name = "GeneralKnowledge")
    public String GeneralKnowledge;

    @Lob
    @Column(name = "DegreeOfRisks")
    public String DegreeOfRisks;

    @Lob
    @Column(name = "Frequency")
    public String Frequency;

    @Column(name = "IncludeSomething")
    public Boolean IncludeSomething;

    @Lob
    @Column(name = "InvolvedResearch")
    public String InvolvedResearch;

    @Lob
    @Column(name = "OtherInvolvedResearch")
    public String OtherInvolvedResearch;

    @Lob
    @Column(name = "Precautions")
    public String Precautions;

    @Lob
    @Column(name = "RiskInTerms")
    public String RiskInTerms;

    @Lob
    @Column(name = "SampleDescription")
    public String SampleDescription;

    @Lob
    @Column(name = "Volume")
    public String Volume;

}
