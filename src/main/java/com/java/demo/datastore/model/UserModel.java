package com.java.demo.datastore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "users")
public class UserModel {

    @Id
    private String id;
    private String userName;
    private String password;
    private String roles;
    private Boolean isActive;

}
