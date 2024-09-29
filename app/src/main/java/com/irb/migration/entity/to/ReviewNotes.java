package com.irb.migration.entity.to;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ReviewNotes")
public class ReviewNotes {

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

    @Column(name = "CreatedDate", columnDefinition = "DATE")
    public Date CreatedDate;

    @Column(name = "UpdatedDate", columnDefinition = "DATE")
    public Date UpdatedDate;

    @Column(name = "Type")
    public String Type;

    @Lob
    @Column(name = "Note")
    public String Note;

    @Column(name = "[Order]", columnDefinition = "NUMERIC(1,0)")
    public int Order;

}
