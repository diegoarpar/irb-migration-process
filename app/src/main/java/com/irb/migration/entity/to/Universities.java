package com.irb.migration.entity.to;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Universities")
public class Universities {

    @jakarta.persistence.Id
    @Column(name = "Id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer Id;

    @Column(name = "Name")
    public String Name;


    @Column(name = "Logo")
    public String Logo;

    @Column(name = "Description")
    public String Description;

    @Column(name = "AdminId")
    public String AdminId;

    @Column(name = "CreatedDate", columnDefinition = "DATE")
    public Date CreatedDate;



}
