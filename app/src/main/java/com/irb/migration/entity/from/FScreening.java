package com.irb.migration.entity.from;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;


@Entity
@Table(name = "tblirbappscreening")
public class FScreening {

    @Id
    @Column(name = "id")
    public Integer id;

    @Column(name = "guemail")
    public String guemail;

    @Column(name = "submitdate", columnDefinition = "DATE")
    public Date submitdate ;

    @Column(name = "q1")
    public Integer q1;

    @Column(name = "q2")
    public Integer q2;

    @Column(name = "q3")
    public Integer q3;

    @Column(name = "q4")
    public String q4 ;

    @Column(name = "status")
    public String status ;

    @Column(name = "irbapp_id")
    public String irbapp_id;
}
