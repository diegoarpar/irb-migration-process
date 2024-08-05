package com.irb.migration.entity.to;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "DataHandlings")
public class DataHandling {

    @jakarta.persistence.Id
    @Column(name = "Id", columnDefinition = "NUMERIC(19,0)")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer Id;

    @ManyToOne
    @JoinColumn(name="IrbApplicationId", nullable=false)
    public IrbApplications IrbApplicationId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="UserId", nullable=false)
    public AspNetUsers  UserId;

    @Lob
    @Column(name = "CollectMethod")
    public String CollectMethod;

    @Lob
    @Column(name = "CollectMethodOther")
    public String CollectMethodOther;

    @Column(name = "Identifier")
    public Integer Identifier;

    @Column(name = "Analysis")
    public Integer Analysis;

    @Column(name = "Reporting")
    public Integer Reporting;

    @Lob
    @Column(name = "Disseminated")
    public String Disseminated;

    @Lob
    @Column(name = "Field58a")
    public String Field58a;

    @Lob
    @Column(name = "Field58b")
    public String Field58b;

    @Lob
    @Column(name = "Field58c")
    public String Field58c;

    @Lob
    @Column(name = "Field58d")
    public String Field58d;

    @Lob
    @Column(name = "Field58e")
    public String Field58e;

    @Column(name = "CreatedDate", columnDefinition = "DATE")
    public Date CreatedDate;

    @Column(name = "UpdatedDate", columnDefinition = "DATE")
    public Date UpdatedDate;

}
