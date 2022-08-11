package com.java.demo.datastore.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "securebox_user_to_usergroup")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserToUserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "u_id")
    private Integer userId;

    @Column(name = "ug_id")
    private Integer userGroupId;

    @Column(name = "is_ag_admin")
    private Boolean isBoxGroupAdmin;

    @Column(name = "is_active")
    private Boolean isActive;

    //Update only user-group id and return current class object
    public UserToUserGroup getUserGroupUpdatedModel(Integer userGroupId){
        this.userGroupId = userGroupId;
        return this;
    }

    public UserToUserGroup(Integer userId, Integer userGroupId, Boolean isActive, Boolean isBoxGroupAdmin){
        this.userId = userId;
        this.userGroupId = userGroupId;
        this.isActive = isActive;
        this.isBoxGroupAdmin = isBoxGroupAdmin;
    }
}
