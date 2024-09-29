package com.irb.migration.entity.to;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "FacultySponsors")
public class FacultySponsors {

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

    @Lob
    @Column(name = "SponsorDegree")
    public String SponsorDegree;

    @Lob
    @Column(name = "Email")
    public String Email;

    @Lob
    @Column(name = "Fullname")
    public String Fullname;

    @Lob
    @Column(name = "ReseachDescription")
    public String ReseachDescription;

    @Column(name = "CreatedDate", columnDefinition = "DATE")
    public Date CreatedDate;

    @Column(name = "UpdatedDate", columnDefinition = "DATE")
    public Date UpdatedDate;

    @Column(name = "IsApproved")
    public Boolean IsApproved ;

    @Column(name = "DecisionDate", columnDefinition = "DATE")
    public Date DecisionDate ;

    @Lob
    @Column(name = "Signature")
    public String Signature;

    @Column(name = "Address")
    public String Address;

}
