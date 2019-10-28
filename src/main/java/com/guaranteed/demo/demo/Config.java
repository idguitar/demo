package com.guaranteed.demo.demo;

import com.guaranteed.demo.demo.records.Record;
import com.guaranteed.demo.demo.records.RecordSorter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Comparator;
import java.util.TreeSet;

@Configuration
public class Config {

    Comparator<Record> c = Comparator.comparing(Record::getBirthDate).thenComparing(Record::getLastName)
            .thenComparing(Record::getFirstName);
    Comparator<Record> lastNameComp = Comparator.comparing(Record::getLastName).thenComparing(Record::getFirstName)
            .thenComparing(Record::getBirthDate).reversed();


    TreeSet<Record> recordsByGenderLastName;
    TreeSet<Record> recordByDate;
    TreeSet<Record> recordsLastName;

    Config(){
        this.recordsByGenderLastName = new TreeSet<>(RecordSorter::compareByGenderThenLastName);
        this.recordByDate = new TreeSet<>(c);
        this.recordsLastName = new TreeSet<>(lastNameComp);
    }

    @Bean
    public TreeSet<Record> getRecordsLastName(){
        return this.recordsLastName;
    }

    @Bean
    public TreeSet<Record> getRecordByDate(){
        return this.recordByDate;
    }

    @Bean
    public TreeSet<Record> getRecordsByGenderLastName(){
        return this.recordsByGenderLastName;
    }
}
