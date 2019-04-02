package com.lextersoft.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    String storeFile(MultipartFile file);
    Resource loadFileAsResource(String fileName);
    Path getPathFile();
}
