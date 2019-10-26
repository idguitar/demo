package com.guaranteed.demo.demo.repositories;


import com.guaranteed.demo.demo.records.Record;
import org.springframework.data.repository.CrudRepository;

public interface RecordRepository extends CrudRepository<Record, Long> {


}
