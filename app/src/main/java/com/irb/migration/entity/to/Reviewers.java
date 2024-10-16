package com.irb.migration.entity.to;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Reviewers")
public class Reviewers {

    @jakarta.persistence.Id
    @Column(name = "Id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer Id;

    @Column(name = "IrbApplicationId")
    public Integer IrbApplicationId;

    @Column(name = "UserId")
    public String UserId;

    @Column(name = "CreatedDate", columnDefinition = "DATE")
    public Date CreatedDate;

    @Column(name = "UpdatedDate", columnDefinition = "DATE")
    public Date UpdatedDate;

    @Column(name = "Status")
    public String Status;

    
    @Column(name = "Signature")
    public String Signature;

    @Column(name = "DecisionDate", columnDefinition = "DATE")
    public Date DecisionDate;

    
    @Column(name = "Description")
    public String Description;

    
    @Column(name = "Decision")
    public String Decision;

    
    @Column(name = "ApprovalLetter")
    public String ApprovalLetter;

}
