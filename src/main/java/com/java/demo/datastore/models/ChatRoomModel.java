package com.java.demo.datastore.models;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "securebox_chat_room")
public class ChatRoomModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cr_id")
    private BigInteger id;

    @Column(name = "cr_name")
    private String name;

    @Column(name = "cr_type")
    private String type;

    @Column(name = "cr_created_date")
    private Timestamp createdDate;

    @Column(name = "cr_modified_date")
    private Timestamp modifiedDate;

    @Column(name = "cr_is_active")
    private boolean isActive;

    public BigInteger getId()
    {
        return id;
    }

    public void setId(BigInteger id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
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

    public boolean isActive()
    {
        return isActive;
    }

    public void setActive(boolean active)
    {
        isActive = active;
    }
}
