package com.griel.empms;

import com.griel.empms.controller.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class EmployeeMSApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(EmployeeMSApplication.class, args);

    }
}
