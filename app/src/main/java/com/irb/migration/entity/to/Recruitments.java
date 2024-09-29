package com.irb.migration.entity.to;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Recruitments")
public class Recruitments {

    @jakarta.persistence.Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer Id;

    @Column(name = "IrbApplicationId", nullable = false)
    public Integer IrbApplicationId;

    @Column(name = "UserId", nullable = false)
    public String  UserId;

    @Column(name = "Strategy")
    public String Strategy;

    @Column(name = "AreChosenFromRecord", nullable = false)
    public Boolean AreChosenFromRecord;

    @Column(name = "ApprovedPerson")
    public String ApprovedPerson;

    @Column(name = "HasIdentifiableInformation", nullable = false)
    public Boolean HasIdentifiableInformation;

    @Column(name = "IdentifiableInformation")
    public String IdentifiableInformation;

    @Column(name = "HasAccess", nullable = false)
    public Boolean HasAccess;

    @Column(name = "WillAccessFromRecord", nullable = false)
    public Boolean WillAccessFromRecord;

    @Column(name = "AccessType")
    public String AccessType;

    @Column(name = "AccessTypeOther")
    public String AccessTypeOther;

    @Column(name = "WillSubjectsReceiveAnything", nullable = false)
    public Boolean WillSubjectsReceiveAnything;

    @Column(name = "ReceiveDetail")
    public String ReceiveDetail;

    @Column(name = "CreatedDate", columnDefinition = "DATE")
    public Date CreatedDate;

    @Column(name = "UpdatedDate", columnDefinition = "DATE")
    public Date UpdatedDate;
}
