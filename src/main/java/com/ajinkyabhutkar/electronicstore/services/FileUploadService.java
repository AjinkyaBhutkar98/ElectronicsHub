package com.ajinkyabhutkar.electronicstore.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileUploadService {

    public String uploadFile(MultipartFile multipartFile,String path) throws IOException;

    public InputStream getFile(String path,String name) throws FileNotFoundException;
}
