package com.xyz.xyz.controller;

import com.xyz.xyz.model.Client;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/client")
public class ImportExcelController {

    @RequestMapping(value = "/import-excel", method = RequestMethod.POST)
    public ResponseEntity<List<Client>> importExcelFile(@RequestParam("file") MultipartFile files) throws IOException {
        HttpStatus status = HttpStatus.OK;
        List<Client> clientList = new ArrayList<>();

        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                Client client = new Client();

                XSSFRow row = worksheet.getRow(index);
                Integer id = (int) row.getCell(0).getNumericCellValue();

                client.setId(id);
                client.setTypeIdentification(row.getCell(1).getStringCellValue());
                client.setIdentification(Double.toString(row.getCell(2).getNumericCellValue()));
                client.setName(row.getCell(3).getStringCellValue());
                client.setCity(row.getCell(4).getStringCellValue());
                client.setAddress(row.getCell(5).getStringCellValue());
                client.setPhone(Double.toString(row.getCell(6).getNumericCellValue()));
                clientList.add(client);
            }
        }

        return new ResponseEntity<>(clientList, status);
    }
}