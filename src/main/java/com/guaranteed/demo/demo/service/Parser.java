package com.guaranteed.demo.demo.service;

import com.guaranteed.demo.demo.records.Gender;
import com.guaranteed.demo.demo.records.Record;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class Parser {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    Parser(){}

    public Set<Record> parseFile(String filePath) throws IOException{
        return this.readFile(filePath);
    }

    protected Record parseLine(String line) {

        if(line.contains("|")){
            return getRecord(line, "\\|");
        }else if(line.contains(",")){
           return getRecord(line, ",");
        }else {
            return getRecord(line, "\\s+");
        }
    }

    protected Record getRecord(String line, String regex){
        String rc = line;

        if(!regex.equals("\\s+"))
        rc = line.replaceAll("\\s+","");

        String[] r = rc.split(regex);

        LocalDate localDate = LocalDate.parse(r[4],formatter);
        return new Record(r[1], r[0], Gender.lookUp(r[2])
                    ,r[3],localDate);
    }

    private Set<Record> readFile(String fileName) throws IOException {
        Stream<String> stream = Files.lines(Paths.get(fileName));
        Set<Record> records = new HashSet<Record>();

        stream.map(this::parseLine).forEach((a)->{
            records.add(a);
        });
        return records;
    }


}
