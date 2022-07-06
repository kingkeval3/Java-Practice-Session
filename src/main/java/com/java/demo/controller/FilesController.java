package com.java.demo.controller;

import com.java.demo.AbstractRestService;
import com.java.demo.services.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value="/files")
public class FilesController extends AbstractRestService {


    @Autowired
    FilesService filesService;

    @GetMapping(value="/serialization")
    public ResponseEntity<?> serializeObject() throws IOException {
        try {
            return filesService.serializeObject();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("File generation failed!");
        }
    }

    @GetMapping(value="/de-serialize")
    public ResponseEntity<?> deSerializeObject(@RequestParam("file") MultipartFile objectFile) throws IOException {
        try {
            return buildSuccess(filesService.deSerializeObject(objectFile), "successfully deserialized");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("File deserialize failed!");
        }
    }


    @GetMapping(value="/excel")
    public ResponseEntity<?> excelToJson(@RequestParam("file") MultipartFile excelFile) throws IOException {
        try {
            return buildSuccess(filesService.excelToJson(excelFile), "successfully converted");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("File conversion failed!");
        }
    }

    @GetMapping(value ="/excel-export")
    public ResponseEntity<?> jsonToExcel() throws IOException {
        try {
            return filesService.jsonToExcel();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("File export failed!");
        }
    }

}
