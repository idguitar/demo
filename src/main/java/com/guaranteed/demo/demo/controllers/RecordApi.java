package com.guaranteed.demo.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guaranteed.demo.demo.records.Record;
import com.guaranteed.demo.demo.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.TreeSet;

@RestController
public class RecordApi {

    @Autowired
    @Qualifier("getRecordsByGenderLastName")
    TreeSet<Record> recordsByGenderLastName;

    @Autowired
    @Qualifier("getRecordByDate")
    TreeSet<Record> recordByDate;

    @Autowired
    @Qualifier("getRecordsLastName")
    TreeSet<Record> recordsLastName;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private RecordRepository recordRepository;

    @GetMapping("/records/{type}")
    public String getRecord(@PathVariable String type) throws IOException {

        if(type.equals("gender")){
             this.recordRepository.findAll().forEach((record -> {
                 recordsByGenderLastName.add(record);
            }));
            return this.objectMapper.writeValueAsString(this.recordsByGenderLastName.toArray());
        } else if(type.equals("birthdate")){
             this.recordRepository.findAll().forEach((record -> {
                 recordByDate.add(record);
            }));
            return this.objectMapper.writeValueAsString(this.recordByDate.toArray());
        } else if(type.equals("name")){
            this.recordRepository.findAll().forEach((record -> {
                recordsLastName.add(record);
            }));
            return this.objectMapper.writeValueAsString(this.recordsLastName.toArray());
        }
        return "not found";
    }

    @PostMapping("/records")
    public Record saveRecord(@RequestBody Record record) {
        return this.recordRepository.save(record);
    }

}
