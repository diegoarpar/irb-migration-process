package com.irb.migration.entity.to;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ResearchStudies")
public class ResearchStudies {

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

    
    @Column(name = "ResearchProposals")
    public String ResearchProposals;

    @Column(name = "FundingAgency")
    public String FundingAgency;

    @Column(name = "IsApprovalRequired")
    public Boolean IsApprovalRequired;

    @Column(name = "StartDate", columnDefinition = "DATE")
    public Date StartDate;

    @Column(name = "EndDate", columnDefinition = "DATE")
    public Date EndDate;

    
    @Column(name = "Purpose")
    public String Purpose;

    
    @Column(name = "Method")
    public String Method;

    @Column(name = "[Procedure]")
    public String Procedure;

    @Column(name = "Question")
    public String Question;

    @Column(name = "Agency")
    public String Agency;

    @Column(name = "AgencyOther")
    public String AgencyOther;

    @Column(name = "AgencyType")
    public String AgencyType;

    @Column(name = "AllAcademicRecords")
    public String AllAcademicRecords;

    @Column(name = "CoiDescription")
    public String CoiDescription;

    @Column(name = "Description")
    public String Description;

    @Column(name = "Duration")
    public String Duration;

    @Column(name = "HasConflict")
    public Boolean HasConflict;

    @Column(name = "InvestigatorId")
    public String InvestigatorId;

    @Column(name = "IsRequiredGrant")
    public Boolean IsRequiredGrant;

    @Column(name = "IsReviewed")
    public Boolean IsReviewed;

    @Column(name = "IsUtilizeAcademicRecord")
    public Boolean IsUtilizeAcademicRecord;

    @Column(name = "ReviewerName")
    public String ReviewerName;

    @Column(name = "IsAnyIntervention")
    public Boolean IsAnyIntervention;

    @Column(name = "OtherResearchDesign")
    public String OtherResearchDesign;

    @Column(name = "Outcomes")
    public String Outcomes;

    @Column(name = "ResearchDesign")
    public String ResearchDesign;

    @Column(name = "SumarizeIntervention")
    public String SumarizeIntervention;

    @Column(name = "CreatedDate", columnDefinition = "DATE")
    public Date CreatedDate;

    @Column(name = "UpdatedDate", columnDefinition = "DATE")
    public Date UpdatedDate;

}
