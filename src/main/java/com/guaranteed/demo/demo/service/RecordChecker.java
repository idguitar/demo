package com.guaranteed.demo.demo.service;

import com.guaranteed.demo.demo.records.Record;
import com.guaranteed.demo.demo.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.TreeSet;

@Service
public class RecordChecker {



    private LinkedList<Record> linkedList;
    private TreeSet<Record> recordsByGenderLastName;
    private TreeSet<Record> recordByDate;
    private TreeSet<Record> recordsLastName;
    private RecordRepository recordRepository;

    public RecordChecker(@Autowired RecordRepository recordRepository,
                            @Autowired  @Qualifier("getRecordsLastName") TreeSet<Record> recordsLastName,
                            @Autowired  @Qualifier("getRecordByDate") TreeSet<Record> recordByDate,
                            @Qualifier("getRecordsByGenderLastName") TreeSet<Record> recordsByGenderLastName) {
        this.recordByDate = recordByDate;
        this.recordsByGenderLastName = recordsByGenderLastName;
        this.recordsLastName = recordsLastName;
        this.recordRepository = recordRepository;
        this.linkedList = new LinkedList<>();
    }

    public LinkedList<Record> getLinkedList() {
        return linkedList;
    }

    //Executes each 500 ms
    @Scheduled(fixedRate=500)
    public void checkRecords() {

        LinkedList<Record> records = new LinkedList<>();
        this.recordRepository.findAll().forEach((record -> {
           records.add(record);
        }));

        if(!this.linkedList.equals(records)){
            this.linkedList = records;
            System.out.println("Database has been updated.");
            updateConsole();
        }
    }

    private void updateConsole() {
        this.loadViewData();
        System.out.println();
        System.out.println("Loading list of records by Gender then LastName:");
        this.recordsByGenderLastName.forEach((record -> {
            System.out.print(record.getGender() + " ");
            System.out.print(record.getLastName() + " ");
            System.out.print(record.getLastName() + " ");
            System.out.print(record.getFormatBirthDate() + " ");
            System.out.println(record.getFavoriteColor());

        }));
        System.out.println();
        System.out.println("Loading list of records by birth date");
        this.recordsLastName.forEach((record -> {
            System.out.print(record.getFormatBirthDate() + " ");
            System.out.print( record.getLastName() + " ");
            System.out.print(record.getLastName() + " ");
            System.out.print(record.getGender() + " ");
            System.out.println(record.getFavoriteColor());

        }));
        System.out.println();
        System.out.println("Loading list of records by LastName");
        this.recordsLastName.forEach((record -> {
            System.out.print(record.getLastName() + " ");
            System.out.print(record.getLastName() + " ");
            System.out.print(record.getGender() + " ");
            System.out.print(record.getFormatBirthDate() + " ");
            System.out.println(record.getFavoriteColor());
        }));
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
