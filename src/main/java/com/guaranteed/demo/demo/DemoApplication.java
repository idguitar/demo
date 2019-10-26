package com.guaranteed.demo.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    @Value("fiileName")
    String fileName;
    public static void main(String[] args) {






        SpringApplication.run(DemoApplication.class, args);

    }

}
