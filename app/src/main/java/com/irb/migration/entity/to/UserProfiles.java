package com.irb.migration.entity.to;


import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "UserProfiles")
public class UserProfiles {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer Id;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="UserId", nullable=false)
    public AspNetUsers UserId;

    @Column(name = "FirstName")
    public String FirstName;

    @Column(name = "MiddleName")
    public String MiddleName;

    @Column(name = "LastName")
    public String LastName;

    @Column(name = "Title")
    public String Title;

    @Column(name = "Picture")
    public String Picture;

    @Column(name = "DateOfBirth", columnDefinition = "DATE")
    public Date DateOfBirth;

    @Column(name = "CreatedDate", columnDefinition = "DATE")
    public Date CreatedDate;

    @Column(name = "ApprovedDate", columnDefinition = "DATE")
    public Date ApprovedDate;

    @Column(name = "LastLogin", columnDefinition = "DATE")
    public Date LastLogin;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name="University", nullable=false)
    public Universities University;

    @Column(name = "Address")
    public String Address;

    @Column(name = "Campus")
    public String Campus;

    @Column(name = "College")
    public String College;

    @Column(name = "Department")
    public String Department;

    @Column(name = "ResearchArea")
    public String ResearchArea;

    @Column(name = "Signature")
    public String Signature;

    @Column(name = "Role")
    public String Role;

    @Column(name = "Gender")
    public String Gender;
}
