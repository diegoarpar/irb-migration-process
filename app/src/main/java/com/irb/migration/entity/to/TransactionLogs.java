package com.irb.migration.entity.to;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "TransactionLogs")
public class TransactionLogs {

    @jakarta.persistence.Id
    @Column(name = "Id", columnDefinition = "NUMERIC(19,0)")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer Id;

    @Column(name = "IrbApplicationId")
    public Integer IrbApplicationId;

    @Column(name = "UserId")
    public String UserId;

    @Column(name = "Action")
    public String Action;

    @Column(name = "EventDate", columnDefinition = "DATE")
    public Date EventDate;

    @Column(name = "EventName")
    public String EventName;

    @Lob
    @Column(name = "Info")
    public String Info;

}
