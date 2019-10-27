package com.guaranteed.demo.demo.controllers;

import com.guaranteed.demo.demo.records.Record;
import com.guaranteed.demo.demo.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.TreeSet;

@Controller
public class RecordController {

    @Autowired
    @Qualifier("getRecordsByGenderLastName")
    TreeSet<Record> recordsByGenderLastName;

    @Autowired
    @Qualifier("getRecordByDate")
    TreeSet<Record> recordByDate;

    @Autowired
    @Qualifier("getRecordsLastName")
    TreeSet<Record> recordsLastName;

    @Autowired
    private RecordRepository recordRepository;

    @GetMapping("/")
   public String geIndex(Model model) {
       this.loadViewData();
       model.addAttribute("recordsByGenderLastName", this.recordsByGenderLastName);
       model.addAttribute("recordByDate", this.recordByDate);
       model.addAttribute("recordsLastName", this.recordsLastName);
       return "views";
   }

    protected void loadViewData() {

        this.recordsLastName.clear();
        this.recordByDate.clear();
        this.recordsByGenderLastName.clear();

        this.recordRepository.findAll().forEach((record -> {
            recordsByGenderLastName.add(record);
            recordByDate.add(record);
            recordsLastName.add(record);
        }));
    }
}
