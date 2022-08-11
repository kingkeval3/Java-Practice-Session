package com.java.demo;

import com.java.demo.exceptions.DataException;
import com.java.demo.pojos.UIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AbstractRestService {

    protected ResponseEntity<UIResponse> buildSuccess(Object obj
            ,String message){

        UIResponse uiResponse = new UIResponse();
        uiResponse.setEntity(obj);
        uiResponse.setHttpStatus(HttpStatus.OK);
        uiResponse.setMessage(message);
        return new ResponseEntity<>(uiResponse, HttpStatus.OK);
    }

    protected ResponseEntity<DataException> buildError(DataException dataException){
        return new ResponseEntity<>(dataException, dataException.getHttpStatus());
    }

}
