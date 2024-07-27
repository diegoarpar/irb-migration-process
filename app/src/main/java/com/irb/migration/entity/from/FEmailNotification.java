package com.irb.migration.entity.from;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "email_notifications")
public class FEmailNotification {

    @Id
    @Column(name = "id")
    public Integer id;

    @Column(name = "application_id")
    public String application_id;

    @Column(name = "fullname")
    public String fullname;

    @Column(name = "expdate")
    public String expdate;

    @Column(name = "toemail")
    public String toemail;

    @Column(name = "tosubject")
    public String tosubject;

    @Column(name = "tobody")
    public String tobody;

    @Column(name = "status", columnDefinition = "NCHAR")
    public String status;

    @Column(name = "sentdate", columnDefinition = "DATE")
    public Date sentdate;

    @Column(name = "type")
    public String type;

    @Column(name = "sentdesc")
    public String sentdesc;
}
