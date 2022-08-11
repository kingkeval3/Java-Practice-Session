package com.java.demo.services.impl;

import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.java.demo.datastore.model.UserModel;
import com.java.demo.datastore.repositories.UserRepository;
import com.java.demo.pojos.Employee;
import com.java.demo.services.FilesService;
import com.java.demo.util.ExcelUtil;
import com.java.demo.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.csv.*;

import java.io.*;

import java.util.*;

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
    public Object excelToJson(MultipartFile file) throws Exception {


        File excelFile = new File(file.getOriginalFilename());

        if(excelFile.exists()){
            excelFile.delete();
        }

        excelFile.createNewFile();

        FileUtil.writeFile(excelFile, file);

        return ExcelUtil.fetchDataFromXlsDocument(excelFile);
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

    @Override
    public Object csvToJson(MultipartFile file) throws Exception {

        File csvFile = new File(file.getOriginalFilename());

        if(csvFile.exists()){
            csvFile.delete();
        }

        csvFile.createNewFile();

        FileUtil.writeFile(csvFile, file);

        CsvSchema csv = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        MappingIterator<Map<?, ?>> mappingIterator =  csvMapper.reader().forType(Map.class).with(csv).readValues(csvFile);
        List<Map<?, ?>> list = mappingIterator.readAll();


/*        List<String> fileTypes = Arrays.asList("Excel","CSV","PDF","");

        Iterator i = fileTypes.iterator();

        while (i.hasNext()){
            String currentFileType = i.next().toString();

            if(currentFileType.isBlank()){
                i.remove();
            }
        }*/

        return list;
    }


    @Override
    public ResponseEntity jsonToCsv() throws Exception {

        File usersExportFile = new File("CsvExport.csv");

        if(usersExportFile.exists()){
            usersExportFile.delete();
        }

        usersExportFile.createNewFile();

        CsvMapper csvMapper = new CsvMapper();

        CsvSchema schema = csvMapper.schemaFor(UserModel.class).withHeader();

        List<UserModel> userModels = userRepository.findAll();

        String csvStr = csvMapper.writer(schema).writeValueAsString(userModels);

        FileUtils.writeStringToFile(usersExportFile, csvStr);

        return setFileResponseEntity(usersExportFile);
    }

    @Override
    public ResponseEntity generatePDF() throws Exception {

        File usersExportFile = new File("PdfExport.pdf");

        if(usersExportFile.exists()){
            usersExportFile.delete();
        }

        usersExportFile.createNewFile();

        String k = "<html><body> This is my Project </body></html>";
        OutputStream file = new FileOutputStream(usersExportFile);
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, file);
        document.open();
        InputStream is = new ByteArrayInputStream(sampleHTMLCodeForInvoice().getBytes());
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
        document.close();
        file.close();

        return setFileResponseEntity(usersExportFile);
    }

    private ResponseEntity setFileResponseEntity(File file) throws FileNotFoundException {
        logger.info("file name: " + file.getName());

        //Prepare file response entity
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamResource resource = new InputStreamResource(fileInputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

        logger.info("File Length of "+file.getName()+": "+file.length());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    //Alternative to jackson mapper - JSON to CSV
    private void writeJsonArrayToCsvFile(File csvFile ,List<?> mapList) throws IOException {

        String dummyKeyForJsonArrayMap = "jsonArray";

        Map<String,List<?>> dummyMap = new HashMap<>(){
            {
                put(dummyKeyForJsonArrayMap, mapList);
            }
        };

        String convertToString = new ObjectMapper().writeValueAsString(dummyMap);

        JSONObject jsonObject = new JSONObject(convertToString);

        JSONArray jsonArrayVar = jsonObject.getJSONArray(dummyKeyForJsonArrayMap);

        String varForCsv = CDL.toString(jsonArrayVar);

        FileUtils.writeStringToFile(csvFile, varForCsv);
    }

    private String sampleHTMLCodeForInvoice(){
        StringBuilder sb = new StringBuilder();


        sb.append("<!DOCTYPE html>");
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<meta charset='utf-8' />");
        sb.append("<title>A simple, clean, and responsive HTML invoice template</title>");
        sb.append("<style>");
        sb.append(".invoice-box { max-width: 800px; margin: auto; padding: 30px; border: 1px solid #eee; box-shadow: 0 0 10px rgba(0, 0, 0, 0.15); font-size: 16px; line-height: 24px; font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif; color: #555;}");
        sb.append(".invoice-box table { width: 100%; line-height: inherit; text-align: left;}");
        sb.append(".invoice-box table td { padding: 5px; vertical-align: top;}");
        sb.append(".invoice-box table tr td:nth-child(2) { text-align: right;}");
        sb.append(".invoice-box table tr.top table td { padding-bottom: 20px;}");
        sb.append(".invoice-box table tr.top table td.title { font-size: 45px; line-height: 45px; color: #333;}");
        sb.append(".invoice-box table tr.information table td { padding-bottom: 40px;}");
        sb.append(".invoice-box table tr.heading td { background: #eee; border-bottom: 1px solid #ddd; font-weight: bold;}");
        sb.append(".invoice-box table tr.details td { padding-bottom: 20px;}");
        sb.append(".invoice-box table tr.item td { border-bottom: 1px solid #eee;}");
        sb.append(".invoice-box table tr.item.last td { border-bottom: none;}");
        sb.append(".invoice-box table tr.total td:nth-child(2) { border-top: 2px solid #eee; font-weight: bold;}");
        sb.append("@media only screen and (max-width: 600px) {"+
        ".invoice-box table tr.top table td { width: 100%; display: block; text-align: center;}"+
        ".invoice-box table tr.information table td { width: 100%; display: block; text-align: center;}}");

        /** RTL - Right to Left **/
        sb.append(".invoice-box.rtl { direction: rtl; font-family: Tahoma, 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;}");
        sb.append(".invoice-box.rtl table { text-align: right;}");
        sb.append(".invoice-box.rtl table tr td:nth-child(2) { text-align: left;}");
        sb.append("</style>");
        sb.append("</head>");

        sb.append("<body>");
        sb.append("<div class='invoice-box'>");

        sb.append("<table cellpadding='0' cellspacing='0'> <tr class='top'> <td colspan='2'> <table> <tr> <td class='title'> <img src='https://www.sparksuite.com/images/logo.png' style='width: 100%; max-width: 300px' /> </td> <td> Invoice #: 123<br /> Created: January 1, 2015<br /> Due: February 1, 2015 </td> </tr> </table> </td> </tr> <tr class='information'> <td colspan='2'> <table> <tr> <td> Sparksuite, Inc.<br />12345 Sunny Road<br /> Sunnyville, CA 12345 </td> <td> Acme Corp.<br /> John Doe<br /> john@example.com </td> </tr> </table> </td> </tr>");
        sb.append("<tr class='heading'> <td>Payment Method</td> <td>Check #</td> </tr> <tr class='details'> <td>Check</td> <td>1000</td> </tr> <tr class='heading'> <td>Item</td> <td>Price</td> </tr> <tr class='item'> <td>Website design</td> <td>$300.00</td> </tr> <tr class='item'> <td>Hosting (3 months)</td> <td>$75.00</td> </tr> <tr class='item last'> <td>Domain name (1 year)</td> <td>$10.00</td> </tr> <tr class='total'> <td></td> <td>Total: $385.00</td> </tr> </table>");
        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");


        return sb.toString();
    }

}
