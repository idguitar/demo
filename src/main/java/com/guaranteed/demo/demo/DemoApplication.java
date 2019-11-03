package com.guaranteed.demo.demo;

import com.guaranteed.demo.demo.service.Parser;
import com.guaranteed.demo.demo.records.Record;
import com.guaranteed.demo.demo.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.net.URI;
import java.util.*;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private Parser parseFile;

    @Autowired
    private RecordRepository recordRepository;

    @Value("${filename:#{null}}")
    String fileName;

    @Autowired
    @Qualifier("getRecordsByGenderLastName")
    TreeSet<Record> recordsByGenderLastName;

    @Autowired
    @Qualifier("getRecordByDate")
    TreeSet<Record> recordByDate;

    @Autowired
    @Qualifier("getRecordsLastName")
    TreeSet<Record> recordsLastName;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Set<Record> records = null;

         if(args.length > 0) {
            System.out.println("Loading File:" +args[0]);
            records = parseFile.parseFile(args[0]);
        } else{
            System.out.println("Loading File:" + this.fileName);
            records =parseFile.parseFile(this.fileName);
        }

        for (Record r : records) {
            this.recordRepository.save(r);
        }
        loadData();
    }

    protected void loadData() {
        this.recordRepository.findAll().forEach((record -> {
            recordsByGenderLastName.add(record);
            recordByDate.add(record);
            recordsLastName.add(record);
        }));
    }
}
