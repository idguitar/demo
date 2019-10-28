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

    @Qualifier("getRecordsByGenderLastName")
    TreeSet<Record> recordsByGenderLastName;

    @Qualifier("getRecordByDate")
    TreeSet<Record> recordByDate;

    @Qualifier("getRecordsLastName")
    TreeSet<Record> recordsLastName;

    private RecordRepository recordRepository;

    public RecordController(@Autowired RecordRepository recordRepository,
                            @Autowired  @Qualifier("getRecordsLastName") TreeSet<Record> recordsLastName,
                            @Autowired  @Qualifier("getRecordByDate") TreeSet<Record> recordByDate,
                            @Qualifier("getRecordsByGenderLastName") TreeSet<Record> recordsByGenderLastName) {
        this.recordByDate = recordByDate;
        this.recordsByGenderLastName = recordsByGenderLastName;
        this.recordsLastName = recordsLastName;
        this.recordRepository = recordRepository;
    }

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
