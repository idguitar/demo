package com.guaranteed.demo.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guaranteed.demo.demo.Config;
import com.guaranteed.demo.demo.records.Gender;
import com.guaranteed.demo.demo.records.Record;
import com.guaranteed.demo.demo.repositories.RecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import java.time.LocalDate;
import java.util.TreeSet;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ContextConfiguration(classes = Config.class)
public class RecordApiTest {


    @Autowired
    Config config;

    @Mock
    RecordRepository recordRepository;

    RecordApi recordApi;
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();

        TreeSet<Record> recordsByGenderLastName = config.getRecordsByGenderLastName();
        TreeSet<Record> recordByDate = config.getRecordByDate();
        TreeSet<Record> recordsLastName = config.getRecordsLastName();

        loadData(recordsByGenderLastName);
        loadData(recordByDate);
        loadData(recordsLastName);

        recordApi = new RecordApi(this.recordRepository, recordsLastName,
                recordByDate, recordsByGenderLastName);
    }

    void loadData(TreeSet<Record> treeSet){
        Record r = new Record("First", "Bravo", Gender.F, "Blue", LocalDate.of(1999, 01, 1));
        Record r2 = new Record("Alpha", "Alpha", Gender.F, "Red", LocalDate.of(2001, 12, 2));
        Record r3 = new Record("First", "charlie", Gender.M, "Green", LocalDate.of(2002, 01, 1));
        Record r4 = new Record("First", "delta", Gender.M, "Black", LocalDate.of(1997, 12, 2));

        treeSet.add(r);
        treeSet.add(r2);
        treeSet.add(r3);
        treeSet.add(r4);
    }

    @Test
    void getRecordTest() throws Exception {
        assertEquals(recordApi.getRecord("demo"), "not found");

        String s = recordApi.getRecord("birthdate");
        Record[] records = objectMapper.readValue(s, Record[].class);;
        assertEquals(records[0].getFormatBirthDate(), "12/02/1997");

        s = recordApi.getRecord("name");
        records = objectMapper.readValue(s, Record[].class);;
        assertEquals(records[0].getLastName(), "delta");

        s = recordApi.getRecord("gender");
        records = objectMapper.readValue(s, Record[].class);;
        assertEquals(records[0].getGender(), Gender.F);
        assertEquals(records[0].getLastName(), "Alpha");
    }

    @Test
    void saveRecordTest() {
        Record r = new Record("First", "Bravo", Gender.F, "Blue", LocalDate.of(1999, 01, 1));

        recordApi.saveRecord(r);
        verify(recordRepository, times(1)).save(r);

    }
}