package com.guaranteed.demo.demo.records;

public class RecordSorter {

    public RecordSorter() {
        super();
    }

    static public int compareByGenderThenLastName(Record r1, Record r2){
        if(r1.getGender() == r2.getGender() )
        {
            return r1.getLastName().compareTo(r2.getLastName()) ;
        }
        else if(r1.getGender() == Gender.F){
            return -1;
        }
        else {
            return 1;
        }

    }
}
