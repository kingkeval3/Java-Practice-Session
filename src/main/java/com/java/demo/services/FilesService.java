package com.java.demo.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.java.demo.pojos.Employee;
import com.java.demo.pojos.UIResponse;
import com.java.demo.pojos.XlsResponsePojo;
import org.apache.coyote.Response;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FilesService {


    public ResponseEntity serializeObject() throws IOException;

    public Employee deSerializeObject(MultipartFile objectFile) throws IOException, ClassNotFoundException;

    public Object excelToJson(MultipartFile file) throws Exception;

    public ResponseEntity jsonToExcel() throws Exception;

    public Object csvToJson(MultipartFile file) throws Exception;

    public ResponseEntity jsonToCsv() throws Exception;

    public ResponseEntity generatePDF() throws Exception;

}
