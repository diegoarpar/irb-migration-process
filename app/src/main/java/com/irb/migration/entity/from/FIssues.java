package com.irb.migration.entity.from;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;


@Entity
@Table(name = "tblbugreport")
public class FIssues {



    @Column(name = "bug_id")
    public String bug_id;

    @Column(name = "guemailid")
    public String guemailid;

    @Column(name = "fullname")
    public String fullname;

    @Column(name = "subject")
    public String subject;

    @Column(name = "bug_type")
    public String bug_type;

    @Column(name = "bug_type_other")
    public String bug_type_other;

    @Column(name = "source_url")
    public String source_url;

    @Column(name = "description")
    public String description;

    @Column(name = "bug_status")
    public String bug_status;

    @Column(name = "os_name_version")
    public String os_name_version;

    @Column(name = "browser_name_version")
    public String browser_name_version;

    @Id
    @Column(name = "bugid_int")
    public Integer bugid_int;

    @Column(name = "admin_dev_comments")
    public String admin_dev_comments;

    @Column(name = "solved_by")
    public String solved_by;

    @Column(name = "bug_close_date")
    public String bug_close_date;
}
