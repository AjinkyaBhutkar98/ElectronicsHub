package com.ajinkyabhutkar.electronicstore.services.impl;

import com.ajinkyabhutkar.electronicstore.exceptions.BadApiRequestException;
import com.ajinkyabhutkar.electronicstore.services.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FIleUploadServiceImpl implements FileUploadService {

    private Logger logger= LoggerFactory.getLogger(FIleUploadServiceImpl.class);

    @Override
    public String uploadFile(MultipartFile multipartFile, String path) throws IOException {

        String originalFileName=multipartFile.getOriginalFilename();
        logger.info("file name : {}",originalFileName);
        String fileName= UUID.randomUUID().toString();
        String extension=originalFileName.substring(originalFileName.lastIndexOf("."));
        String finalFileName=fileName+extension;
        String fullPathWithFileName=path+ File.separator+finalFileName;

        if(extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpeg")){
            //Create folder
            File folder=new File(path);
            if(!folder.exists()){

                folder.mkdirs();
            }
            Files.copy(multipartFile.getInputStream(), Paths.get(fullPathWithFileName));
            return finalFileName;
        }else{
            throw new BadApiRequestException("Invalid image type");
        }

    }

    @Override
    public InputStream getFile(String path, String name) throws FileNotFoundException {

        String fullPath=path+File.separator+name;

        InputStream inputStream=new FileInputStream(fullPath);

        return inputStream;
    }
}
