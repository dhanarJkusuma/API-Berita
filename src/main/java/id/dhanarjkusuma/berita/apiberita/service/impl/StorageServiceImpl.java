package id.dhanarjkusuma.berita.apiberita.service.impl;

import id.dhanarjkusuma.berita.apiberita.StorageProperties;
import id.dhanarjkusuma.berita.apiberita.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageServiceImpl implements StorageService {
    private final StorageProperties storageProperties;
    private Path rootLocation;

    @Autowired
    public StorageServiceImpl(StorageProperties storageProperties) {
        this.storageProperties = storageProperties;
        this.rootLocation = Paths.get(this.storageProperties.getStorageBasePath());
    }

    @Override
    public Path store(MultipartFile file, String filename) {
        String originFileName = StringUtils.cleanPath(file.getOriginalFilename());
        filename = String.join(".", filename, getOriginalExtenstion(originFileName));

        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file " + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, rootLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to store file " + filename, e);
        }
        return load(filename);
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    private String getOriginalExtenstion(String filename){
        int dotExtenstion = filename.indexOf(".", -0);
        if(dotExtenstion == -1){
            throw new RuntimeException("Invalid filename !!!");
        }
        return filename.substring(dotExtenstion + 1, filename.length());
    }
}
