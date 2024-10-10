package com.irb.migration.entity.to;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "DataHandlings")
public class DataHandling {

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

    
    @Column(name = "CollectMethod")
    public String CollectMethod;

    
    @Column(name = "CollectMethodOther")
    public String CollectMethodOther;

    @Column(name = "Identifier")
    public Boolean Identifier;

    @Column(name = "Analysis")
    public Boolean Analysis;

    @Column(name = "Reporting")
    public Boolean Reporting;

    
    @Column(name = "Disseminated")
    public String Disseminated;

    
    @Column(name = "Field58a")
    public String Field58a;

    
    @Column(name = "Field58b")
    public String Field58b;

    
    @Column(name = "Field58c")
    public String Field58c;

    
    @Column(name = "Field58d")
    public String Field58d;

    
    @Column(name = "Field58e")
    public String Field58e;

    @Column(name = "CreatedDate", columnDefinition = "DATE")
    public Date CreatedDate;

    @Column(name = "UpdatedDate", columnDefinition = "DATE")
    public Date UpdatedDate;

}
