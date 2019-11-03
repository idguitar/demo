package com.guaranteed.demo.demo;

import com.guaranteed.demo.demo.records.Record;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Comparator;
import java.util.TreeSet;

@Configuration
@EnableAsync
@EnableScheduling
public class Config {

    Comparator<Record> c = Comparator.comparing(Record::getBirthDate).thenComparing(Record::getLastName)
            .thenComparing(Record::getFirstName);

    Comparator<Record> lastNameComp = Comparator.comparing(Record::getLastName).thenComparing(Record::getFirstName)
            .thenComparing(Record::getBirthDate).reversed();

    Comparator<Record> genderLastName = Comparator.comparing(Record::getGender).reversed()
            .thenComparing(Record::getLastName).thenComparing(Record::getFirstName)
            .thenComparing(Record::getBirthDate).thenComparing(Record::getFavoriteColor);

    TreeSet<Record> recordsByGenderLastName;
    TreeSet<Record> recordByDate;
    TreeSet<Record> recordsLastName;

    Config(){
        this.recordsByGenderLastName = new TreeSet<>(genderLastName);
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
