package com.irb.migration.entity.from;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "tblchangeusertype")
public class FChangeUserType {

    @Id
    @Column(name = "usertypeid")
    public String id;

    @Column(name = "userid_int")
    public Integer userid_int;

    @Column(name = "gu_email")
    public String gu_email;

    @Column(name = "gu_networkid")
    public String gu_networkid;

    @Column(name = "firstname")
    public String firstname;

    @Column(name = "lastname")
    public String lastname;

    @Column(name = "original_usertype")
    public String original_usertype;

    @Column(name = "requested_usertype")
    public String requested_usertype;

    @Column(name = "new_usertype")
    public String new_usertype;

    @Column(name = "request_decision")
    public String request_decision;

    @Column(name = "user_comment")
    public String user_comment;

    @Column(name = "admin_comment")
    public String admin_comment;

    @Column(name = "request_date")
    public String request_date;

    @Column(name = "decision_date")
    public String decision_date;
}
