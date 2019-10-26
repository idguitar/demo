package com.guaranteed.demo.demo;

import com.guaranteed.demo.demo.records.Parser;
import com.guaranteed.demo.demo.records.Record;
import com.guaranteed.demo.demo.records.RecordSorter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

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
        Set<Record> records = parseFile.parseFile(this.fileName);

        if(!(this.fileName == null)){
           records =parseFile.parseFile(this.fileName);
        }else if(args.length > 0) {
            records = parseFile.parseFile(args[0]);
        }

        Comparator<Record> c = Comparator.comparing(Record::getBirthDate);
        Comparator<Record> lastNameComp = Comparator.comparing(Record::getLastName).reversed();

        TreeSet<Record> recordsByGenderLastName = new TreeSet<>(RecordSorter::compareByGenderThenLastName);
        TreeSet<Record> recordByDate = new TreeSet<>(c);
        TreeSet<Record> recordsLastName = new TreeSet<>(lastNameComp);
    }
}
