package com.guaranteed.demo.demo.controllers;

import com.guaranteed.demo.demo.Config;
import com.guaranteed.demo.demo.records.Gender;
import com.guaranteed.demo.demo.records.Record;
import com.guaranteed.demo.demo.repositories.RecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.TreeSet;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = Config.class)
class RecordControllerTest {

    @Autowired
    Config config;
    @Mock
    RecordRepository recordRepository;

    RecordController recordController;

    TreeSet<Record> recordsByGenderLastName;
    TreeSet<Record> recordByDate;
    TreeSet<Record> recordsLastName;

    @BeforeEach
    void setUp() {
        recordsByGenderLastName = config.getRecordsByGenderLastName();
        recordByDate = config.getRecordByDate();
        recordsLastName = config.getRecordsLastName();

        loadData(recordsByGenderLastName);
        loadData(recordByDate);
        loadData(recordsLastName);

        this.recordController = new RecordController(this.recordRepository, recordsLastName,
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
    void geIndex() {
        Model model = Mockito.mock(Model.class);
        assertEquals("views", this.recordController.geIndex(model));
    }

    @Test
    void loadViewData() {

        this.recordController.loadViewData();
        assertEquals(0,this.recordsByGenderLastName.size());
        assertEquals(0,this.recordByDate.size());
        assertEquals(0,this.recordsLastName.size());
    }
}