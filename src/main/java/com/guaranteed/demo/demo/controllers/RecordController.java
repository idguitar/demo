package com.guaranteed.demo.demo.controllers;

import com.guaranteed.demo.demo.records.Record;
import com.guaranteed.demo.demo.records.RecordSorter;
import com.guaranteed.demo.demo.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.TreeSet;

@Controller
public class RecordController {

    @Autowired
    private RecordRepository recordRepository;
    private String message = "hello world";

    Comparator<Record> c = Comparator.comparing(Record::getBirthDate);
    Comparator<Record> lastNameComp = Comparator.comparing(Record::getLastName).reversed();

    TreeSet<Record> recordsByGenderLastName = new TreeSet<>(RecordSorter::compareByGenderThenLastName);
    TreeSet<Record> recordByDate = new TreeSet<>(c);
    TreeSet<Record> recordsLastName = new TreeSet<>(lastNameComp);


   @GetMapping("/")
   public String geIndex(Model model) {

       this.recordRepository.findAll().forEach((record -> {
             recordsByGenderLastName.add(record);
             recordByDate.add(record);
             recordsLastName.add(record);
       }));

       model.addAttribute("recordsByGenderLastName", this.recordsByGenderLastName);
       model.addAttribute("recordByDate", this.recordByDate);
       model.addAttribute("recordsLastName", this.recordsLastName);

       return "views";
   }

}
