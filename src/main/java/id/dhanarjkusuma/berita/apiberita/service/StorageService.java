package id.dhanarjkusuma.berita.apiberita.service;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {
    Path store(MultipartFile file, String filename);
    Path load(String filename);
}
