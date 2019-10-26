package com.guaranteed.demo.demo;

import java.time.LocalDate;

enum Gender{
    MALE("M"), FEMALE("F");
    private  final String gender;

    private Gender(String g){
        this.gender = g;
    }
}
public class Record {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String favoriteColor;
    private LocalDate birthDate;

    public Record(String firstName, String lastName, Gender g, String favoriteColor, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = g;
        this.favoriteColor = favoriteColor;
        this.birthDate = birthDate;
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
        return "First Name:" + this.firstName + " Last Name:" + this.lastName + " gender:" + this.gender +
                " favorite Color:" + this.favoriteColor + " BirthDate:" + this.birthDate;
    }
}
