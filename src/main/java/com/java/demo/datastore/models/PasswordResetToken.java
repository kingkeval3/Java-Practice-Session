package com.java.demo.datastore.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "securebox_user_authentication_table")
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private int id;

    @Column(name = "t_reset_token")
    private String resetToken;

    @Column(name = "t_expiry_date")
    private Timestamp expDate;


    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "t_user_id", referencedColumnName = "u_id")
    private UserModel userId;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getResetToken()
    {
        return resetToken;
    }

    public void setResetToken(String resetToken)
    {
        this.resetToken = resetToken;
    }

    public Timestamp getExpDate()
    {
        return expDate;
    }

    public void setExpDate(Timestamp expDate)
    {
        this.expDate = expDate;
    }

    public UserModel getUserId()
    {
        return userId;
    }

    public void setUserId(UserModel userId)
    {
        this.userId = userId;
    }
}
