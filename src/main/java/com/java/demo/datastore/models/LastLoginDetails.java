package com.java.demo.datastore.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "securebox_last_login_details")
public class LastLoginDetails {

    @Id
    @Column(name = "l_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lastLoginId;

    @Column(name = "l_timestamp")
    private Timestamp lastUpdatedTime;

    @Column(name = "l_ip_address")
    private String ipAddress;

    public int getLastLoginId()
    {
        return lastLoginId;
    }

    public void setLastLoginId(int lastLoginId)
    {
        this.lastLoginId = lastLoginId;
    }

    public Timestamp getLastUpdatedTime()
    {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Timestamp lastUpdatedTime)
    {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public String getIpAddress()
    {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress)
    {
        this.ipAddress = ipAddress;
    }
}
