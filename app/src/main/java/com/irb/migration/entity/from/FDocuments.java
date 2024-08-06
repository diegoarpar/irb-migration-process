package com.irb.migration.entity.from;


import jakarta.persistence.*;

import java.sql.Clob;


@Entity
@Table(name = "tblirbappformuploadfiles")
public class FDocuments {

    @Id
    @Column(name = "application_id")
    public String application_id;

    @Lob
    @Column(name = "file_name")
    public String file_name;


    @Column(name = "content_type")
    public String content_type;

    @Column(name = "data", columnDefinition = "CLOB")
    public byte[] data;

    @Lob
    @Column(name = "notes")
    public String notes;

    @Lob
    @Column(name = "hyperlink1")
    public String hyperlink1;

    @Lob
    @Column(name = "hyperlink2")
    public String hyperlink2;

    @Lob
    @Column(name = "hyperlink3")
    public String hyperlink3;
}
