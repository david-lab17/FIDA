package com.deltacode.kcb.helper;

import com.deltacode.kcb.entity.Role;
import com.deltacode.kcb.entity.UserApp;
import com.deltacode.kcb.repository.RoleRepository;
import com.deltacode.kcb.repository.UserRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ExcelHelper {
    private static final int FIRST_NAME_COLUMN_INDEX = 0;
    private static final int MIDDLE_NAME_COLUMN_INDEX = 1;
    private static final int LAST_NAME_COLUMN_INDEX = 2;
    private static final int EMAIL_COLUMN_INDEX = 3;
    private static final int DATE_OF_BIRTH_COLUMN_INDEX = 4;
    private final static int PHONE_NUMBER_COLUMN_INDEX = 5;
    private final static int PASSWORD_COLUMN_INDEX = 6;
    private final static int USER_NAME_COLUMN_INDEX = 7;
    @Autowired
    static PasswordEncoder passwordEncoder;
    @Autowired
     static RoleRepository roleRepository;
    public static final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "firstName","middleName","lastName","email","dateOfBirth","phoneNumber","password","username" };
    static String SHEET = "Users";






    public static boolean hasExcelFormat(MultipartFile file) {

        return TYPE.equals(file.getContentType());
    }

    public static List<UserApp> excelToUsers(InputStream is) {

//
//
//
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<UserApp> userApps = new ArrayList<>();

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
                        case FIRST_NAME_COLUMN_INDEX -> userApp.setFirstName(currentCell.getStringCellValue());
                        case MIDDLE_NAME_COLUMN_INDEX -> userApp.setMiddleName(currentCell.getStringCellValue());
                        case LAST_NAME_COLUMN_INDEX -> userApp.setLastName(currentCell.getStringCellValue());
                        case EMAIL_COLUMN_INDEX -> userApp.setEmail(currentCell.getStringCellValue());
                        case DATE_OF_BIRTH_COLUMN_INDEX -> userApp.setDateOfBirth(String.valueOf(currentCell.getDateCellValue()));
                        case PHONE_NUMBER_COLUMN_INDEX -> userApp.setPhoneNumber(currentCell.getStringCellValue());
                        case PASSWORD_COLUMN_INDEX -> userApp.setPassword(passwordEncoder.encode(currentCell.getStringCellValue()));
                        case USER_NAME_COLUMN_INDEX -> userApp.setUsername(currentCell.getStringCellValue());

                        default -> {
                        }
                    }

                    cellIdx++;
                }
                //add user to db and assign default  role
                Optional<Role> role = roleRepository.findByName("ROLE_USER");
                //set role
                userApp.setRoles(new HashSet<>(Arrays.asList(role.get())));
                //add to list
                userApps.add(userApp);//add user to list



            }

            workbook.close();

            return userApps;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }


    }


}



