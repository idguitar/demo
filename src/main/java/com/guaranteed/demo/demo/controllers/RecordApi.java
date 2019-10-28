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

    TreeSet<Record> recordsByGenderLastName;
    TreeSet<Record> recordByDate;
    TreeSet<Record> recordsLastName;

    ObjectMapper objectMapper = new ObjectMapper();

    private RecordRepository recordRepository;

    public RecordApi(@Autowired RecordRepository recordRepository,
                     @Autowired  @Qualifier("getRecordsLastName") TreeSet<Record> recordsLastName,
                     @Autowired  @Qualifier("getRecordByDate") TreeSet<Record> recordByDate,
                     @Autowired @Qualifier("getRecordsByGenderLastName") TreeSet<Record> recordsByGenderLastName) {
        this.recordByDate = recordByDate;
        this.recordsByGenderLastName = recordsByGenderLastName;
        this.recordsLastName = recordsLastName;
        this.recordRepository = recordRepository;
    }


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
