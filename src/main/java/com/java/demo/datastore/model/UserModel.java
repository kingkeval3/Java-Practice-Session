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
    private ObjectId id;
    private String name;
    private Integer age;
    private String job;

}
