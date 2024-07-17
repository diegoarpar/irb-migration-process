package com.irb.migration.entity.to;


import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "AspNetUserClaims")
public class AspNetUserClaims {

    @Id
    @Column(name = "Id", columnDefinition = "NUMERIC(19,0)")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer Id;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="UserId", nullable=false)
    public AspNetUsers UserId;

    @Column(name = "ClaimType")
    public String ClaimType;

    @Column(name = "ClaimValue")
    public String ClaimValue;


}
