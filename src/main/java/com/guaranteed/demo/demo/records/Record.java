package com.guaranteed.demo.demo.records;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

@Entity
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String favoriteColor;

    private LocalDate birthDate;

    public Record(){

    }

    public Record(String firstName, String lastName, Gender g, String favoriteColor, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = g;
        this.favoriteColor = favoriteColor;
        this.birthDate = birthDate;

    }

    @JsonSetter("birthDate")
    public void setBirthDate(LinkedHashMap birthDate) {
        int year = Integer.parseInt(birthDate.get("year").toString());
        int month = Integer.parseInt(birthDate.get("monthValue").toString());

        int day = Integer.parseInt(birthDate.get("dayOfMonth").toString());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate localDate = LocalDate.of(year,month,day);
        localDate.format(formatter);
        this.birthDate = localDate;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public String getFavoriteColor() {
        return favoriteColor;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    @JsonIgnore
    public String getFormatBirthDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return this.birthDate.format(formatter);
    }

    @Override
    public boolean equals(Object o) {
       if(!(o instanceof Record))
       {
           return false;
       }
        Record obj = (Record)o;
        return this.firstName.equals(obj.firstName) && this.lastName.equals(obj.lastName) &&
                this.gender.equals(obj.gender) && this.favoriteColor.equals(obj.favoriteColor) &&
                this.birthDate.equals(obj.gender);
    }

    @Override
    public String toString() {
        return "FirstName:" + this.firstName + " LastName:" + this.lastName + " gender:" + this.gender +
                " favoriteColor:" + this.favoriteColor + " BirthDate:" + this.birthDate;
    }
}
