package doha.java.dohanguyen_java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class DoHaNguyenJavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(DoHaNguyenJavaApplication.class, args);
    }

}
