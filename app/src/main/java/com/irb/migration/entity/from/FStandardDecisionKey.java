package com.irb.migration.entity.from;


import java.io.Serializable;
import java.util.Objects;

public class FStandardDecisionKey implements Serializable {

    private static final long serialVersionUID = -9092062628785267880L;

    public String application_id;
    public String irbstaffemail;

    public FStandardDecisionKey(String applicationId, String irbstaffemail) {
        this.application_id = applicationId;
        this.irbstaffemail = irbstaffemail;
    }

    public FStandardDecisionKey() {
    }

    public String getApplication_id() {
        return application_id;
    }

    public void setApplication_id(String application_id) {
        this.application_id = application_id;
    }

    public String getIrbstaffemail() {
        return irbstaffemail;
    }

    public void setIrbstaffemail(String irbstaffemail) {
        this.irbstaffemail = irbstaffemail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FStandardDecisionKey that = (FStandardDecisionKey) o;
        return Objects.equals(application_id, that.application_id) && Objects.equals(irbstaffemail, that.irbstaffemail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(application_id, irbstaffemail);
    }
}
