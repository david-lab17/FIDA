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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ExcelHelper {
    @Autowired
     static RoleRepository roleRepository;
    @Autowired
      static UserRepository userRepository;

    public static final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "firstName","middleName","lastName","email","dateOfBirth","phoneNumber","password","username" };
    static String SHEET = "Users";






    public static boolean hasExcelFormat(MultipartFile file) {

        return TYPE.equals(file.getContentType());
    }

    public static List<UserApp> excelToUsers(InputStream is) {



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
                Optional<Role> role = roleRepository.findByName("ROLE_USER");
                userApp.setRoles(new HashSet<>(Arrays.asList(role.get())));
                userApps.add(userApp);
//               userApps.add(userApp);

//



            }

            workbook.close();

            return userApps;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}



