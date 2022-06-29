package com.java.demo.pojos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class UIResponse implements Serializable {

    private Object entity;
    private String message;
    private HttpStatus httpStatus;
}
