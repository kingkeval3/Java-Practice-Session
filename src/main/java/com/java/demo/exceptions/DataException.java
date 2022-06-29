package com.java.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class DataException extends Exception{

    private String errorMessage;
    private HttpStatus httpStatus;

}
