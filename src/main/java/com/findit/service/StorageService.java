package com.findit.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Service
public class StorageService {
    private final Path root;
    public StorageService(@Value("${app.upload-dir:uploads/items}") String dir) throws IOException {
        root=Paths.get(dir).toAbsolutePath().normalize(); Files.createDirectories(root);
    }
    public String store(MultipartFile file) throws IOException {
        if(file==null || file.isEmpty()) return null;
        String original=StringUtils.cleanPath(Objects.requireNonNullElse(file.getOriginalFilename(),""));
        if(original.contains("..") || !original.contains(".")) throw new IOException("Invalid filename.");
        String ext=original.substring(original.lastIndexOf('.')+1).toLowerCase();
        if(!Set.of("jpg","jpeg","png","webp").contains(ext)) throw new IOException("Only JPG, JPEG, PNG and WEBP images are allowed.");
        String name=UUID.randomUUID()+"."+ext;
        Files.copy(file.getInputStream(),root.resolve(name),StandardCopyOption.REPLACE_EXISTING);
        return name;
    }
}