package com.irb.migration.entity.to;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "SubjectDefines")
public class SubjectDefines {

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

    @Column(name = "ResearchSubject")
    public String ResearchSubject;

    @Column(name = "Population")
    public String Population;

    @Column(name = "PopulationOther")
    public String PopulationOther;

    @Column(name = "SafeGuard")
    public String SafeGuard;

    @Column(name = "NumSubRecruited")
    public Integer NumSubRecruited;

    @Column(name = "SubRecruitedTypes")
    public String SubRecruitedTypes;

    @Column(name = "SubRecruitedOther")
    public String SubRecruitedOther;

    @Column(name = "confidentialityExplanation")
    public String confidentialityExplanation;

    @Column(name = "exclusionCriterial")
    public String exclusionCriterial;

    @Column(name = "inclusionCriterial")
    public String inclusionCriterial;

    @Column(name = "isAvailableEveryOne")
    public Integer isAvailableEveryOne;

    @Column(name = "isIncludeGannonStudents")
    public Integer isIncludeGannonStudents;

    @Column(name = "isInformationIdentifiable")
    public Integer isInformationIdentifiable;

    @Lob
    @Column(name = "subjectConfidentiality")
    public String subjectConfidentiality;

    @Lob
    @Column(name = "subjectLocation")
    public String subjectLocation;

    @Lob
    @Column(name = "whatIdentifiers")
    public String whatIdentifiers;

    @Lob
    @Column(name = "whenIdentifyData")
    public String whenIdentifyData;

    @Lob
    @Column(name = "whoHaveAccess")
    public String whoHaveAccess;

    @Column(name = "CreatedDate", columnDefinition = "DATE")
    public Date CreatedDate;

    @Column(name = "UpdatedDate", columnDefinition = "DATE")
    public Date UpdatedDate;

}
