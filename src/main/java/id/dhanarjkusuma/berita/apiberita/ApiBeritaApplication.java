package id.dhanarjkusuma.berita.apiberita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ StorageProperties.class })
public class ApiBeritaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiBeritaApplication.class, args);
	}
}
