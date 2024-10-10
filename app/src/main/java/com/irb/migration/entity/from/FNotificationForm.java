package com.irb.migration.entity.from;


import jakarta.persistence.*;


@Entity
@Table(name = "tblirbappnotificationform")
public class FNotificationForm {

    @Id
    @Column(name = "application_id")
    public String application_id;

    @Column(name = "date_submited")
    public String date_submited;

    @Column(name = "date_approved")
    public String date_approved;

    @Column(name = "check_box")
    public String check_box;

    @Column(name = "check_box_other")
    public String check_box_other;

    @Column(name = "sign_init")
    public String sign_init;

    @Column(name = "name_printed")
    public String name_printed;

    @Column(name = "position")
    public String position;

    @Column(name = "received_by_irb_on")
    public String received_by_irb_on;

    @Column(name = "received_by_irb")
    public String received_by_irb;

    @Column(name = "app_status")
    public String app_status;

    
    @Column(name = "reason")
    public String reason;
}
