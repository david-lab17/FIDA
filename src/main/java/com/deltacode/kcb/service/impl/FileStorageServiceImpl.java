package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.service.FileStorageService;
import com.deltacode.kcb.utils.Utility;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
@Service
public class FileStorageServiceImpl implements FileStorageService {
    private String pathString = "fileupload/";
    private final Path root = Paths.get(pathString);
    private Logger log = Logger.getLogger(FileStorageService.class.getName());
    private String uploadPath = "fileupload/";


    @Override
    public List<String> uploadOneFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("Failed to store empty file");
        }
        try {
            String fileName = new File(uploadPath + file.getOriginalFilename()).getName();
            file.transferTo(new File(fileName));
        } catch (IOException e) {
            log.info("Error is {} " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<String> multipleFiles(MultipartFile[] files) {
        List<String> fileNames = new ArrayList<>();
        try {
            Arrays.asList(files).stream().forEach(file -> {
                save(file);
                fileNames.add(pathString + "" + file.getOriginalFilename());
            });
            return fileNames;
        } catch (Exception e) {
            log.info("Error in method " + e.getMessage());
            return fileNames;
        }
    }

    @Override
    public Resource load(String filename) {
        return null;
    }

    @Override
    public List<String> multipleFilesWithDifferentParams(MultipartFile file1, MultipartFile file2, MultipartFile file3) {
        try {
            saveFileWithSpecificFileName("file1.PNG", file1);
            saveFileWithSpecificFileName("file2.PNG", file2);
            saveFileWithSpecificFileName("file3.PNG", file3);
        } catch (Exception e) {
            log.info("Error in method " + e.getMessage());
        }
        return null;
    }

    @Override
    public String saveFileWithSpecificFileName(String fileName, MultipartFile file) {
        try {
            fileName = new File(pathString + fileName).getName();
            file.transferTo(new File(fileName));
            log.info("Path is " + fileName);
            return fileName;
        } catch (Exception e) {
            log.info("Could not store the file. Error in saveFileWithSpecificFileName: "
                    + e.getMessage());
            return "";
        }
    }

    @Override
    public List<String> saveMultipleFileWithSpecificFileName(String module, MultipartFile[] files) {
        List<String> listFilePath = new ArrayList<>();
        try {
            Arrays.stream(files).forEach(file -> {
                String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
                String fileName = module.concat(Utility.generateUniqueNoByDate()).
                        concat(".").concat(fileExtension);
                byte[] bytes = new byte[0];
                Path path = null;
                try {
                    bytes = file.getBytes();
                    path = Files.write(Paths.get(pathString, fileName), bytes);
                } catch (IOException e) {
                    log.info("Error is ");
                }
                String filePath = path.toString();
                listFilePath.add(filePath);
//                log.info("Path is " + filePath);

            });
            return listFilePath;
        } catch (Exception e) {
            log.info("Could not store the file. Error in saveFileWithSpecificFileName: "
                    + e.getMessage());
            return listFilePath;

        }
    }
        //save file
        public void save(MultipartFile file){
            try {
                Files.copy(file.getInputStream(),
                        this.root.resolve(file.getOriginalFilename()));
            } catch (Exception e) {
                throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
            }
        }

    }



