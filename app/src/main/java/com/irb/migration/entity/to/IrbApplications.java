package com.irb.migration.entity.to;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "IrbApplications")
public class IrbApplications {

    @jakarta.persistence.Id
    @Column(name = "Id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer Id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="UserId")
    public AspNetUsers UserId;

    
    @Column(name = "Title")
    public String Title;

    @Column(name = "TypeOfReview")
    public String TypeOfReview;

    @Column(name = "ApplicationCode")
    public String ApplicationCode;

    @Column(name = "AppStatus")
    public String AppStatus;

    @Column(name = "CreatedDate", columnDefinition = "DATE")
    public Date CreatedDate;

    @Column(name = "SubmittedDate", columnDefinition = "DATE")
    public Date SubmittedDate;

    @Column(name = "IrbStatus")
    public String IrbStatus;

    @Column(name = "IrbDate", columnDefinition = "DATE")
    public Date IrbDate;

    @Column(name = "UpdatedDate", columnDefinition = "DATE")
    public Date UpdatedDate;

    @Column(name = "Completion")
    public Integer Completion;

    
    @Column(name = "Description")
    public String Description;

    @Column(name = "IrbExpireDate", columnDefinition = "DATE")
    public Date IrbExpireDate;

}
