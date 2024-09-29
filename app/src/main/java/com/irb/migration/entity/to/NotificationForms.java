package com.irb.migration.entity.to;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "NotificationForms")
public class NotificationForms {

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

    @Column(name = "Type")
    public String Type;

    @Column(name = "Remarks")
    public String Remarks;

    @Column(name = "CreatedDate", columnDefinition = "DATE")
    public Date CreatedDate;

    @Column(name = "UpdatedDate", columnDefinition = "DATE")
    public Date UpdatedDate;

    @Column(name = "Status")
    public String Status;

    @Column(name = "AssignedUserId")
    public String AssignedUserId;

    @Lob
    @Column(name = "Signature")
    public String Signature;

    @Column(name = "DecisionDate", columnDefinition = "DATE")
    public Date DecisionDate;

    @Lob
    @Column(name = "Observation")
    public String Observation;

    @Column(name = "Position")
    public String Position;

    @Column(name = "ReceivedBy")
    public String ReceivedBy;

    @Lob
    @Column(name = "ReceivedByDate", columnDefinition = "DATE")
    public Date ReceivedByDate;

    @Column(name = "NamePrinted")
    public String NamePrinted;

}
