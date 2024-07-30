package com.irb.migration.entity.to;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "StandardVotes")
public class StandardVotes {

    @jakarta.persistence.Id
    @Column(name = "Id", columnDefinition = "NUMERIC(19,0)")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer Id;

    @Column(name = "IrbApplicationId")
    public Integer IrbApplicationId;

    @Column(name = "UserId")
    public String UserId;

    @Lob
    @Column(name = "Decision")
    public String Decision;


    @Lob
    @Column(name = "Reason")
    public String Reason;

    @Column(name = "CreatedDate", columnDefinition = "DATE")
    public Date CreatedDate;

    @Lob
    @Column(name = "UpdatedDate")
    public Date UpdatedDate;

}
