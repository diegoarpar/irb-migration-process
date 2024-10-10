package com.irb.migration.entity.from;


import jakarta.persistence.*;

import java.sql.Clob;


@Entity
@Table(name = "tblirbappformuploadfiles")
public class FDocuments {

    @Id
    @Column(name = "application_id")
    public String application_id;

    
    @Column(name = "file_name")
    public String file_name;


    @Column(name = "content_type")
    public String content_type;

    @Column(name = "data", columnDefinition = "CLOB")
    public byte[] data;

    
    @Column(name = "notes")
    public String notes;

    
    @Column(name = "hyperlink1")
    public String hyperlink1;

    
    @Column(name = "hyperlink2")
    public String hyperlink2;

    
    @Column(name = "hyperlink3")
    public String hyperlink3;

    public FDocuments(String application_id, String file_name, String content_type,  String notes, String hyperlink1, String hyperlink2, String hyperlink3) {
        this.application_id = application_id;
        this.file_name = file_name;
        this.content_type = content_type;
        this.notes = notes;
        this.hyperlink1 = hyperlink1;
        this.hyperlink2 = hyperlink2;
        this.hyperlink3 = hyperlink3;
    }

    public FDocuments(byte[] data) {
        this.data = data;
    }
}
