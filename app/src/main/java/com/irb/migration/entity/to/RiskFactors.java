package com.irb.migration.entity.to;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "RiskFactors")
public class RiskFactors {

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

    @Column(name = "SubjectRisk")
    public Integer SubjectRisk;

    @Column(name = "ExperimentalDrug")
    public Integer ExperimentalDrug;

    @Column(name = "MedicalProblem")
    public Integer MedicalProblem;

    @Column(name = "PhysicalDiscomfort")
    public Integer PhysicalDiscomfort;

    @Column(name = "MentalDiscomfort")
    public Integer MentalDiscomfort;

    @Column(name = "ElectricalEquipment")
    public Integer ElectricalEquipment;

    @Column(name = "SubjectRecord")
    public Integer SubjectRecord;

    @Column(name = "SubjectCoercion")
    public Integer SubjectCoercion;

    @Column(name = "SubjectDeception")
    public Integer SubjectDeception;

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
    public Integer IncludeSomething;

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
