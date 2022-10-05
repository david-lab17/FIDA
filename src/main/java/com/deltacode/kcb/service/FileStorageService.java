package com.deltacode.kcb.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileStorageService {
    List<String>uploadOneFile(MultipartFile file);

    List<String>multipleFiles(MultipartFile[] files);
    public Resource load(String filename);
    List<String>multipleFilesWithDifferentParams(MultipartFile file1, MultipartFile file2, MultipartFile file3);
    String saveFileWithSpecificFileName(String fileName, MultipartFile file);
    List<String>saveMultipleFileWithSpecificFileName(String module, MultipartFile[] files);
}
