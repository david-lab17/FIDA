package com.deltacode.kcb.helper;

import com.deltacode.kcb.entity.UserApp;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "username","password","email","firstName","lastName","phoneNumber","middleName","dateOfBirth" };
    static String SHEET = "Users";
    public static boolean hasExcelFormat(MultipartFile file) {

        return TYPE.equals(file.getContentType());
    }

    public static List<UserApp> excelToUsers(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<UserApp> userApps = new ArrayList<UserApp>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                UserApp userApp = new UserApp();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        //first name,Middle name,Last name,E-mail,Date of birth,Phone number,Password,Username.
                        case 0 -> userApp.setFirstName(currentCell.getStringCellValue());
                        case 1 -> userApp.setMiddleName(currentCell.getStringCellValue());
                        case 2 -> userApp.setLastName(currentCell.getStringCellValue());
                        case 3 -> userApp.setEmail(currentCell.getStringCellValue());
                        case 4 -> userApp.setDateOfBirth(currentCell.getDateCellValue());
                        case 5 -> userApp.setPhoneNumber(currentCell.getStringCellValue());
                        case 6 -> userApp.setPassword(currentCell.getStringCellValue());
                        case 7 -> userApp.setUsername(currentCell.getStringCellValue());
                        default -> {
                        }
                    }

                    cellIdx++;
                }

                userApps.add(userApp);
            }

            workbook.close();

            return userApps;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}



