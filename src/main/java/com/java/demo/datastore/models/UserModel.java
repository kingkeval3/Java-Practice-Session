package com.java.demo.datastore.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "securebox_user")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private int id;

    @Column(name = "u_firstname")
    private String firstName;

    @Column(name = "u_lastname")
    private String lastName;

    @Column(name = "u_username")
    private String userName;

    @Column(name = "u_email")
    private String emailId;

    @Column(name = "u_phonenumber")
    private String phoneNumber;

    @Column(name = "u_middlename")
    private String middleName;

    @Column(name = "u_company")
    private String companyName;

    @Column(name = "u_job_title")
    private String jobTitle;

    @Column(name = "u_is_active")
    private boolean isActive;

    @Column(name = "u_online_date")
    private Timestamp onlineDate;

    @Column(name = "u_password")
    private String password;

    @Column(name = "u_createdby")
    private int createdBy;


    @Column(name = "u_modifiedby")
    private int modifiedBy;

    @Column(name = "u_created_date")
    private Timestamp createdDate;

    @Column(name = "u_modified_date")
    private Timestamp modifiedDate;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "securebox_last_login_details_l_id", referencedColumnName = "l_id")
    private LastLoginDetails lastLoginDetails;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "securebox_role_role_id", referencedColumnName = "r_id")
    private RoleEntity roleEntity;

    @Column(name = "profile_pic_url")
    private String profilePicture;

    @Column(name = "u_password_date")
    private Timestamp passwordModifiedDate;

    @Column(name = "u_mac_address")
    private String macAddress;

    @Column(name="u_firebase_token")
    private String firebaseToken;

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Timestamp getPasswordModifiedDate()
    {
        return passwordModifiedDate;
    }

    public void setPasswordModifiedDate(Timestamp passwordModifiedDate)
    {
        this.passwordModifiedDate = passwordModifiedDate;
    }

    public Timestamp getOnlineDate()
    {
        return onlineDate;
    }

    public void setOnlineDate(Timestamp onlineDate)
    {
        this.onlineDate = onlineDate;
    }

    public String getProfilePicture()
    {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture)
    {
        this.profilePicture = profilePicture;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getEmailId()
    {
        return emailId;
    }

    public void setEmailId(String emailId)
    {
        this.emailId = emailId;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getJobTitle()
    {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle)
    {
        this.jobTitle = jobTitle;
    }

    public boolean isActive()
    {
        return isActive;
    }

    public void setActive(boolean active)
    {
        isActive = active;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
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

    public LastLoginDetails getLastLoginDetails()
    {
        return lastLoginDetails;
    }

    public void setLastLoginDetails(LastLoginDetails lastLoginDetails)
    {
        this.lastLoginDetails = lastLoginDetails;
    }

    public RoleEntity getRoleEntity()
    {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity)
    {
        this.roleEntity = roleEntity;
    }


}