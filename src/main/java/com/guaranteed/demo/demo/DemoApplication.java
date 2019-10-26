package com.guaranteed.demo.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    Parser parseFile;

    @Value("${filename:#{null}}")
    String fileName;



    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if(!(this.fileName == null)){
            parseFile.parseFile(this.fileName);
        }else if(args.length > 0) {
            parseFile.parseFile(args[0]);
        }


    }
}
