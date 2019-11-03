package com.guaranteed.demo.demo.service;

import com.guaranteed.demo.demo.Config;
import com.guaranteed.demo.demo.records.Gender;
import com.guaranteed.demo.demo.records.Record;
import com.guaranteed.demo.demo.repositories.RecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.TreeSet;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = Config.class)
class RecordCheckerTest {

    @Autowired
    Config config;

    RecordRepository recordRepository;

    TreeSet<Record> recordsByGenderLastName;
    TreeSet<Record> recordByDate;
    TreeSet<Record> recordsLastName;

    RecordChecker recordChecker;

    @BeforeEach
    void setUp() {
        recordsByGenderLastName = config.getRecordsByGenderLastName();
        recordByDate = config.getRecordByDate();
        recordsLastName = config.getRecordsLastName();

        recordRepository = Mockito.mock(RecordRepository.class);
        recordChecker = new RecordChecker(recordRepository, recordsLastName,
               recordByDate, recordsByGenderLastName);
    }

    @Test
    void checkRecords() {
        Record r = new Record("First", "Bravo", Gender.F, "Blue", LocalDate.of(1999, 01, 1));
        Record r2 = new Record("First2", "Charlie", Gender.M, "Blue", LocalDate.of(1999, 01, 1));

        LinkedList<Record> records = new LinkedList<>();
        records.add(r);

        when(recordRepository.findAll()).thenReturn(records);
        this.recordChecker.checkRecords();

        assertEquals(records, this.recordChecker.getLinkedList());

        records.add(r2);
        when(recordRepository.findAll()).thenReturn(records);
        this.recordChecker.checkRecords();
        assertEquals(records, this.recordChecker.getLinkedList());

    }
}