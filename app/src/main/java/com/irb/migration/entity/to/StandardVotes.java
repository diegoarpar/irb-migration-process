package com.irb.migration.entity.to;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "StandardVotes")
public class StandardVotes {

    @jakarta.persistence.Id
    @Column(name = "Id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer Id;

    @Column(name = "IrbApplicationId")
    public Integer IrbApplicationId;

    @Column(name = "UserId")
    public String UserId;

    
    @Column(name = "Decision")
    public String Decision;


    
    @Column(name = "Reason")
    public String Reason;

    @Column(name = "CreatedDate", columnDefinition = "DATE")
    public Date CreatedDate;

    
    @Column(name = "UpdatedDate")
    public Date UpdatedDate;

}
