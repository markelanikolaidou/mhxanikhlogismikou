package com.softeng.athleticsticket.service;

import com.softeng.athleticsticket.model.Event;
import com.softeng.athleticsticket.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public EventService() {
        // Default constructor
    }

    public List<Event> getAll(){
        return eventRepository.findAll();
    }

    public List<Event> search(final String searchString){
        return eventRepository.findByNameContainingIgnoreCaseOrLocationContainingIgnoreCaseOrTypeContainingIgnoreCase(searchString,searchString,searchString);
    }

    public List<Event> searchUpcoming(final String searchString){
        return eventRepository.findByDateAfterAndNameContainingIgnoreCaseOrLocationContainingIgnoreCaseOrTypeContainingIgnoreCase(new Date(Calendar.getInstance().getTimeInMillis()),searchString,searchString,searchString);
    }

    public Event findById(final int id){
        return eventRepository.findById(id);
    }

    public Event save(final Event event){
        return eventRepository.save(event);
    }

    public void delete(final int id){
        eventRepository.delete(id);
    }
}
