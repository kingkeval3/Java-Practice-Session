package com.java.demo.datastore.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "securebox_user_group")
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ug_id")
    private Integer ugId;

    @Column(name = "ug_name")
    private String ugName;

    @Column(name = "ug_description")
    private String ugDescription;

    @Column( name="ug_location", columnDefinition = "JSON")
    private String ugLocation;

    @Column(name = "ug_is_active")
    private Boolean ugIsActive;

    @Column(name = "ug_createdby")
    private Integer ugCreatedBy;

    @Column(name = "ug_modifiedby")
    private Integer ugModifiedBy;

    @Column(name = "ug_created_date")
    private Timestamp ugCreatedDate;

    @Column(name = "ug_modified_date")
    private Timestamp ugModifiedDate;
}
