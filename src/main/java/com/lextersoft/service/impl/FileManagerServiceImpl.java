package com.lextersoft.service.impl;

import com.lextersoft.service.FileManagerService;
import com.lextersoft.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;

@Service
public class FileManagerServiceImpl implements FileManagerService {

    private final Logger log = LoggerFactory.getLogger(FileManagerServiceImpl.class);

    private final StorageService storageService;

    public FileManagerServiceImpl(StorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public String readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        Path targetLocation = storageService.getPathFile().resolve(fileName);
        File file = targetLocation.toFile();

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            String line;
            while((line = br.readLine()) != null){
                //process the line
                stringBuilder.append(line);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return  stringBuilder.toString();
    }
}
