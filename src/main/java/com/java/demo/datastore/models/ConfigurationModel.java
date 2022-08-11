package com.java.demo.datastore.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "securebox_configuration")
public class ConfigurationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private int id;

    @Column(name = "c_type")
    private String type;

    @Column(name = "c_properties")
    private String properties;

    @Column(name = "c_is_active")
    private boolean isActive;

    @Column(name = "c_created_date")
    private Timestamp createdDate;

    @Column(name = "c_modified_date")
    private Timestamp modifiedDate;

    @Column(name = "c_created_by")
    private int createdBy;

    @Column(name = "c_modified_by")
    private int modifiedBy;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getProperties()
    {
        return properties;
    }

    public void setProperties(String properties)
    {
        this.properties = properties;
    }

    public boolean isActive()
    {
        return isActive;
    }

    public void setActive(boolean active)
    {
        isActive = active;
    }

    public Timestamp getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate)
    {
        this.createdDate = createdDate;
    }

    public Timestamp getModifiedDate()
    {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate)
    {
        this.modifiedDate = modifiedDate;
    }

    public int getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(int createdBy)
    {
        this.createdBy = createdBy;
    }

    public int getModifiedBy()
    {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy)
    {
        this.modifiedBy = modifiedBy;
    }
}
