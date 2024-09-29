package com.irb.migration.entity.to;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Screenings")
public class Screenings {

    @jakarta.persistence.Id
    @Column(name = "Id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer Id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="UserId", nullable=false)
    public AspNetUsers UserId;

    @Column(name = "Question1")
    public Integer Question1;

    @Column(name = "Question2")
    public Integer Question2;

    @Column(name = "Question3")
    public Integer Question3;

    @Column(name = "Question4")
    public String Question4;

    @Column(name = "CreatedDate", columnDefinition = "DATE")
    public Date CreatedDate;

}
