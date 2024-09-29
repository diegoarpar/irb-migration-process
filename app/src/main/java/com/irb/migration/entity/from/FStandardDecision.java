package com.irb.migration.entity.from;


import jakarta.persistence.*;

import java.io.Serializable;


@Table(name = "tblirbappstandarddecision")
@Entity
public class FStandardDecision implements Serializable {

    private static final long serialVersionUID = -909206262878526790L;


    /*@Id
    @Column(name = "application_id")
    public String application_id;

    @Id
    @Column(name = "irbstaffemail")
    public String irbstaffemail;*/

    @EmbeddedId
    public FStandardDecisionKey id;

    @Column(name = "reason")
    public String reason;

    @Column(name = "decision")
    public String decision;
}
