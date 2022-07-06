package com.java.demo.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.java.demo.datastore.repositories.UserRepository;
import com.java.demo.pojos.Employee;
import com.java.demo.services.FilesService;
import com.java.demo.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Service
public class FilesServiceImpl implements FilesService {

    Logger logger = LoggerFactory.getLogger(FilesServiceImpl.class);

    @Autowired
    UserRepository userRepository;


    @Override
    public ResponseEntity serializeObject() throws IOException {

        File byteFile = new File("byteFile.txt");

        if(byteFile.exists()){
            byteFile.delete();
        }

        byteFile.createNewFile();

        FileOutputStream fileOutputStream = new FileOutputStream(byteFile.getAbsolutePath());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(
                new Employee("Anthony", 40, "Stark Industries"));

        objectOutputStream.close();
        fileOutputStream.close();

        return setFileResponseEntity(byteFile);
    }

    @Override
    public Employee deSerializeObject(MultipartFile objectFile) throws IOException, ClassNotFoundException {

        ObjectInputStream objectInputStream = new ObjectInputStream(objectFile.getInputStream());

        Employee employee = (Employee) objectInputStream.readObject();

        return employee;
    }

    @Override
    public JsonNode excelToJson(MultipartFile file) throws Exception {


        File excelFile = new File(file.getOriginalFilename());

        if(excelFile.exists()){
            excelFile.delete();
        }

        excelFile.createNewFile();

        FileUtil.writeFile(excelFile, file);

        return FileUtil.excelToJson(excelFile);
    }

    @Override
    public ResponseEntity jsonToExcel() throws Exception {

        File usersExportFile = new File("UsersExport.xlsx");

        if(usersExportFile.exists()){
            usersExportFile.delete();
        }

        usersExportFile.createNewFile();

        FileUtil.writeObjects2ExcelFile(userRepository.findAll(), usersExportFile.getAbsolutePath());

        return setFileResponseEntity(usersExportFile);
    }

    private ResponseEntity setFileResponseEntity(File file) throws FileNotFoundException {
        logger.info("file name: " + file.getName());

        //Prepare file response entity
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamResource resource = new InputStreamResource(fileInputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
