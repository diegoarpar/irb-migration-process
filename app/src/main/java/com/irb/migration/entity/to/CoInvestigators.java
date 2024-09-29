package com.irb.migration.entity.to;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "CoInvestigators")
public class CoInvestigators {

    @jakarta.persistence.Id
    @Column(name = "Id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer Id;

    @ManyToOne
    @JoinColumn(name="IrbApplicationId", nullable=false)
    public IrbApplications IrbApplicationId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="IrbUserId", nullable=false)
    public AspNetUsers  IrbUserId;

    @Column(name = "Email")
    public String Email;

    @Column(name = "Firstname")
    public String Firstname;

    @Column(name = "Lastname")
    public String Lastname;

    @Column(name = "Title")
    public String Title;

    @Column(name = "CreatedDate", columnDefinition = "DATE")
    public Date CreatedDate;

    @Column(name = "UpdatedDate", columnDefinition = "DATE")
    public Date UpdatedDate;

    @Column(name = "Address")
    public String Address;

    @Column(name = "Phone")
    public String Phone;

    @Column(name = "Signature")
    public String Signature;

}
