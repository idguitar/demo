package com.guaranteed.demo.demo.service;

import com.guaranteed.demo.demo.records.Gender;
import com.guaranteed.demo.demo.records.Record;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class ParserTest {

    Parser parser;

    @Value("${commafile:#{null}}")
    String commaFile;

    @Value("${pipefile:#{null}}")
    String pipeFile;

    @Value("${spacefile:#{null}}")
    String spaceFile;


    @BeforeEach
    void setUp() {
        this.parser = new Parser();
    }

    @Test
    public void parseFileTest() throws IOException {

        Set<Record> records = this.parser.parseFile(this.commaFile);
        assertNotNull(records);

        Set<Record> records2 = this.parser.parseFile(this.pipeFile);
        assertNotNull(records2);

        Set<Record> records3 = this.parser.parseFile(this.spaceFile);
        assertNotNull(records3);
    }

    @Test
    public void parseLineTest(){

        String lineComma = "AAAA, BBBB, M, GREEN, 12/09/1990";
        Record r =this.parser.parseLine(lineComma);

        assertEquals("AAAA", r.getLastName());
        assertEquals("BBBB", r.getFirstName());
        assertEquals(Gender.M, r.getGender());
        assertEquals(LocalDate.of(1990, 12, 9), r.getBirthDate());

        String linePipe = "AAAA | BBBB | F| GREEN |12/09/1990";
        r = this.parser.parseLine(linePipe);

        assertEquals("AAAA", r.getLastName());
        assertEquals("AAAA", r.getLastName());
        assertEquals("BBBB", r.getFirstName());
        assertEquals(Gender.F, r.getGender());
        assertEquals(LocalDate.of(1990, 12, 9), r.getBirthDate());


        String lineSpace = "AAAA BBBB M GREEN 12/09/1990";
        r = this.parser.parseLine(lineSpace);

        assertEquals("AAAA", r.getLastName());
        assertEquals("AAAA", r.getLastName());
        assertEquals("BBBB", r.getFirstName());
        assertEquals(Gender.M, r.getGender());
        assertEquals(LocalDate.of(1990, 12, 9), r.getBirthDate());

    }

}
