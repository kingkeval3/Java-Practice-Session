package com.java.demo.datastore.model;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(notes = "User full name", example = "Bruce Wayne", required = true)
    private String name;

    @ApiModelProperty(notes = "User Age", example = "24", required = false)
    private Integer age;

    private String job;

}
