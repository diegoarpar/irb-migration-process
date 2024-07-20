package com.irb.migration.entity.from;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;


@Entity
@Table(name = "user_details")
public class FUserDetails {

    @Column(name = "admin_approval")
    public String admin_approval;

    @Id
    @Column(name = "gu_email")
    public String gu_email;

    @Column(name = "password")
    public String password;

    @Column(name = "title")
    public String title;

    @Column(name = "firstname")
    public String firstname;

    @Column(name = "middle_name")
    public String middle_name;

    @Column(name = "last_name")
    public String last_name;

    @Column(name = "gender")
    public String gender;

    @Column(name = "contact_no")
    public String contact_no;

    @Column(name = "college_name")
    public String college_name;

    @Column(name = "department")
    public String department;

    @Column(name = "user_type")
    public String user_type;

    @Column(name = "research_area")
    public String research_area;

    @Column(name = "IsUserAdmin")
    public String IsUserAdmin;

    @Column(name = "HasAdminPrivilages")
    public String HasAdminPrivilages;

    @Column(name = "gunetworkid")
    public String gunetworkid;

    @Column(name = "currentirbappassigned")
    public Integer  currentirbappassigned;

    @Column(name = "lastassignedirbstaff")
    public String lastassignedirbstaff;

    @Column(name = "lastassigneddate")
    public String lastassigneddate;

    @Column(name = "irbchairemail")
    public String irbchairemail;

    @Column(name = "last_visit")
    public String last_visit;

    @Column(name = "campus")
    public String campus;

    @Column(name = "img")
    public String img;

    @Column(name = "reg_date", columnDefinition = "DATE")
    public Date reg_date;
}
