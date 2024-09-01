package com.irb.migration.entity.to;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ResearchStudies")
public class ResearchStudies {

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

    @Lob
    @Column(name = "ResearchProposals")
    public String ResearchProposals;

    @Column(name = "FundingAgency")
    public String FundingAgency;

    @Column(name = "AgencyOther")
    public String agencyOther;

    @Column(name = "IsApprovalRequired")
    public Integer IsApprovalRequired;

    @Column(name = "StartDate", columnDefinition = "DATE")
    public Date StartDate;

    @Column(name = "EndDate", columnDefinition = "DATE")
    public Date EndDate;

    @Lob
    @Column(name = "Purpose")
    public String Purpose;

    @Lob
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
    public Integer HasConflict;

    @Column(name = "InvestigatorId")
    public String InvestigatorId;

    @Column(name = "IsRequiredGrant")
    public Integer IsRequiredGrant;

    @Column(name = "IsReviewed")
    public Integer IsReviewed;

    @Column(name = "IsUtilizeAcademicRecord")
    public Integer IsUtilizeAcademicRecord;

    @Column(name = "ReviewerName")
    public String ReviewerName;

    @Column(name = "IsAnyIntervention")
    public Integer IsAnyIntervention;

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
