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

    
    @Column(name = "Action")
    public String Action;

    
    @Column(name = "Subject")
    public String Subject;

    @Column(name = "EventDateCreated", columnDefinition = "DATE" )
    public Date EventDateCreated;

    @Column(name = "EventDateVisited", columnDefinition = "DATE" )
    public Date EventDateVisited;

    @Column(name = "EventDateSolved", columnDefinition = "DATE" )
    public Date EventDateSolved;

    
    @Column(name = "EventName")
    public String EventName;

    
    @Column(name = "Description")
    public String Description;

    
    @Column(name = "Other")
    public String Other;

    
    @Column(name = "SourceUrl")
    public String SourceUrl;

    
    @Column(name = "DeviceInformation")
    public String DeviceInformation;

    
    @Column(name = "ResolutionDescription")
    public String ResolutionDescription;

    
    @Column(name = "Type")
    public String Type;

    
    @Column(name = "Status")
    public String Status;

    
    @Column(name = "Sent")
    public Boolean Sent ;

    
    @Column(name = "Visited")
    public Boolean Visited ;

}
