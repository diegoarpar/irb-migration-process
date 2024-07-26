package com.irb.migration.entity.from;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "tblirbapp_resr")
public class FExempted {


    @Id
    @Column(name = "application_id")
    public String application_id;

    @Column(name = "que1")
    public String que1;

    @Column(name = "que2")
    public String que2;

    @Column(name = "notes")
    public String notes;
}
