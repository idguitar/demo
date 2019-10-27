package com.guaranteed.demo.demo.controllers;

import com.guaranteed.demo.demo.records.Record;
import com.guaranteed.demo.demo.records.RecordSorter;
import com.guaranteed.demo.demo.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

@RestController
public class RecordApi {

    Comparator<Record> c = Comparator.comparing(Record::getBirthDate);
    Comparator<Record> lastNameComp = Comparator.comparing(Record::getLastName).reversed();

    TreeSet<Record> recordsByGenderLastName = new TreeSet<>(RecordSorter::compareByGenderThenLastName);
    TreeSet<Record> recordByDate = new TreeSet<>(c);
    TreeSet<Record> recordsLastName = new TreeSet<>(lastNameComp);

    @Autowired
    private RecordRepository recordRepository;

    @GetMapping("/records/{type}")
    public String getRecord(@PathVariable String type){
        if(type.equals("name")){
             this.recordRepository.findAll().forEach((record -> {
                recordsLastName.add(record);
            }));
            return Arrays.toString(this.recordsLastName.toArray());
        } else if(type.equals("birthdate")){
             this.recordRepository.findAll().forEach((record -> {
                recordsLastName.add(record);
            }));
            return Arrays.toString(this.recordsLastName.toArray());
        } else if(type.equals("birthdate")){
            this.recordRepository.findAll().forEach((record -> {
                recordsLastName.add(record);
            }));
            return Arrays.toString(this.recordsLastName.toArray());
        }
        return "not found";
    }
}
