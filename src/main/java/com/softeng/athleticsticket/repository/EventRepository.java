package com.softeng.athleticsticket.repository;

import com.softeng.athleticsticket.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findAll();
    List<Event> findByNameContainingIgnoreCaseOrLocationContainingIgnoreCaseOrTypeContainingIgnoreCase(String name,String location,String type);
    List<Event> findByDateAfterAndNameContainingIgnoreCaseOrLocationContainingIgnoreCaseOrTypeContainingIgnoreCase( Date date,String name, String location, String type);
    Event findById(int id);
}
