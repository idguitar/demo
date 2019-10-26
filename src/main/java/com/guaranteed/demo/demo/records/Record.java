package com.guaranteed.demo.demo.records;

import java.time.LocalDate;


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
        return birthDate;
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
