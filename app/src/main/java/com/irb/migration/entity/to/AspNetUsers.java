package com.irb.migration.entity.to;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "AspNetUsers")
public class AspNetUsers {

    @jakarta.persistence.Id
    @Column(name = "Id")
    @GeneratedValue(strategy= GenerationType.UUID)
    public String Id;

    @Column(name = "UserName")
    public String UserName;

    @Column(name = "NormalizedUserName")
    public String NormalizedUserName;

    @Column(name = "Email")
    public String Email;

    @Column(name = "NormalizedEmail")
    public String NormalizedEmail;

    @Column(name = "EmailConfirmed")
    public boolean EmailConfirmed;

    @Column(name = "PasswordHash")
    public String PasswordHash;

    @Column(name = "SecurityStamp")
    public String SecurityStamp;

    @Column(name = "ConcurrencyStamp")
    public String ConcurrencyStamp;

    @Column(name = "PhoneNumber")
    public String PhoneNumber;

    @Column(name = "PhoneNumberConfirmed", nullable = false)
    public Boolean PhoneNumberConfirmed;

    @Column(name = "TwoFactorEnabled", nullable = false)
    public Boolean TwoFactorEnabled;

    @Column(name = "LockoutEnd", columnDefinition = "DATE")
    public Date LockoutEnd;

    @Column(name = "LockoutEnabled", nullable = false)
    public Boolean LockoutEnabled;

    @Column(name = "AccessFailedCount",  nullable = false)
    public Integer AccessFailedCount;

}
