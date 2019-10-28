package com.guaranteed.demo.demo.controllers;

import com.guaranteed.demo.demo.Config;
import com.guaranteed.demo.demo.repositories.RecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@ContextConfiguration(classes = Config.class)
public class RecordApiTest {


    @Autowired
    Config config;

    @Mock
    RecordRepository recordRepository;

    RecordApi recordApi;


    @BeforeEach
    void setUp() {
        recordApi = new RecordApi(this.recordRepository, config.getRecordsLastName(),
                config.getRecordByDate(), config.getRecordsByGenderLastName());
    }

    @Test
    void getRecordTest() throws Exception {
        assertEquals(recordApi.getRecord("demo"), "not found");
    }

    @Test
    void saveRecordTest() {
    }
}