package com.irb.migration.entity.to;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "AspNetUsers")
public class AspNetUsers {

    @jakarta.persistence.Id
    @Column(name = "Id", columnDefinition = "NUMERIC(19,0)")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer Id;

    @Column(name = "UserName")
    public String UserName;

    @Column(name = "NormalizedUserName")
    public String NormalizedUserName;

    @Column(name = "Email")
    public String Email;

    @Column(name = "NormalizedEmail")
    public String NormalizedEmail;

    @Column(name = "EmailConfirmed")
    public Integer EmailConfirmed;

    @Column(name = "PasswordHash")
    public String PasswordHash;

    @Column(name = "SecurityStamp")
    public String SecurityStamp;

    @Column(name = "ConcurrencyStamp")
    public String ConcurrencyStamp;

    @Column(name = "PhoneNumber")
    public String PhoneNumber;

    @Column(name = "PhoneNumberConfirmed")
    public String PhoneNumberConfirmed;

    @Column(name = "TwoFactorEnabled")
    public String TwoFactorEnabled;

    @Column(name = "LockoutEnd", columnDefinition = "DATE")
    public Date LockoutEnd;

    @Column(name = "LockoutEnabled")
    public Integer LockoutEnabled;

    @Column(name = "AccessFailedCount")
    public Integer AccessFailedCount;

}
