package com.irb.migration.entity.to;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "IrbIssues")
public class Issues {

    @jakarta.persistence.Id
    @Column(name = "Id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer Id;

    @Column(name = "IrbApplicationId")
    public Integer IrbApplicationId;

    @Column(name = "UserIdSolver")
    public String  UserIdSolver;

    @Column(name = "NotificationFormId")
    public Integer NotificationFormId;

    @Column(name = "UserIdReporter")
    public String  UserIdReporter;

    @Lob
    @Column(name = "Action")
    public String Action;

    @Lob
    @Column(name = "Subject")
    public String Subject;

    @Column(name = "EventDateCreated", columnDefinition = "DATE" )
    public Date EventDateCreated;

    @Column(name = "EventDateVisited", columnDefinition = "DATE" )
    public Date EventDateVisited;

    @Column(name = "EventDateSolved", columnDefinition = "DATE" )
    public Date EventDateSolved;

    @Lob
    @Column(name = "EventName")
    public String EventName;

    @Lob
    @Column(name = "Description")
    public String Description;

    @Lob
    @Column(name = "Other")
    public String Other;

    @Lob
    @Column(name = "SourceUrl")
    public String SourceUrl;

    @Lob
    @Column(name = "DeviceInformation")
    public String DeviceInformation;

    @Lob
    @Column(name = "ResolutionDescription")
    public String ResolutionDescription;

    @Lob
    @Column(name = "Type")
    public String Type;

    @Lob
    @Column(name = "Status")
    public String Status;

    @Lob
    @Column(name = "Sent")
    public Boolean Sent ;

    @Lob
    @Column(name = "Visited")
    public Boolean Visited ;

}
