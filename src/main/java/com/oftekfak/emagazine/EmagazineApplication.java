package com.oftekfak.emagazine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
@AutoConfigureBefore
public class EmagazineApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmagazineApplication.class, args);
    }

}
