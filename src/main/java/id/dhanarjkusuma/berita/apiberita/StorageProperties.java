package id.dhanarjkusuma.berita.apiberita;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class StorageProperties {

    @Value("${api-berita.storage.base-path}")
    private String storageBasePath;

    public String getStorageBasePath() {
        return storageBasePath;
    }
}
