package com.java.demo.datastore.models;

import javax.persistence.*;

@Entity
@Table(name = "securebox_user_session")
public class UserSessionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "us_id")
    private int id;
    @Column(name = "us_user_id")
    private int userId;
    @Column(name = "us_token")
    private String jsonToken;

    @Column(name = "us_code")
    private String userCode;

    @Column(name = "us_login_status")
    private boolean loginStatus;

    public String getUserCode()
    {
        return userCode;
    }

    public void setUserCode(String userCode)
    {
        this.userCode = userCode;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getJsonToken()
    {
        return jsonToken;
    }

    public void setJsonToken(String jsonToken)
    {
        this.jsonToken = jsonToken;
    }

    public boolean isLoginStatus()
    {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus)
    {
        this.loginStatus = loginStatus;
    }
}
