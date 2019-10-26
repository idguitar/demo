package com.guaranteed.demo.demo;


public enum Gender{
    M("M"), F("F"), UNKNOWN("UNKNOWN");
    private  final String gender;

    private Gender(String g){
        this.gender = g;
    }
    public static Gender lookUp(String str){
       Gender[] genders = Gender.values();

        for(Gender g : genders){
            //Comparing
            if(g.name().equalsIgnoreCase(str)) {
               return g;
            }
        }
        return UNKNOWN;
    }
}
