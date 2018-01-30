package com.softeng.athleticsticket.service;

import com.softeng.athleticsticket.model.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventServiceTest {

    @Autowired
    private EventService eventService;

    public EventServiceTest() {
        // Default constructor
    }

    @Test
    public void getAll() throws Exception {
        //TESTS IF ALL THE EVENTS IN THE DATABASE HAVE THE REQUIRED INFORMATION
        final List<Event> eventList = eventService.getAll();
        eventList.forEach((event)->{
            assertNotEquals(0,event.getId());
            assertNotEquals(null,event.getDate());
            assertNotEquals(null,event.getLocation());
            assertNotEquals(null,event.getName());
            assertNotEquals(null,event.getType());
        });
    }

    @Test
    public void search() throws Exception {
        //TEST THE search METHOD OF THE EVENT SERVICE IF RETURNS CORRECT RESULTS
        Calendar calendar = Calendar.getInstance();
        Event event = new Event();
        event.setName("TEST");
        event.setDate(new Date(calendar.getTimeInMillis()));
        event.setLocation("TEST");
        event.setType("TEST");
        event = eventService.save(event);
        List<Event> eventList = eventService.search("test");
        assertNotEquals(0,eventList.size());
        eventService.delete(event.getId());
    }

    @Test
    public void searchUpcoming() throws Exception {
        //TESTS IF THE searchUpcoming METHOD OF THE EVENT SERVICE RETURNS ONLY UPCOMING EVENTS
        Calendar calendar = Calendar.getInstance();
        Date today = new Date(calendar.getTimeInMillis());
        calendar.add(Calendar.DATE,1);
        Date eventDate = new Date(calendar.getTimeInMillis());
        Event event = new Event();
        event.setName("TEST");
        event.setDate(eventDate);
        event.setLocation("TEST");
        event.setType("TEST");
        event = eventService.save(event);
        List<Event> eventList = eventService.searchUpcoming("test");
        assertNotEquals(0,eventList.size());
        eventList.forEach( (e) -> {
            assertTrue(today.compareTo(e.getDate()) < 0);
        });
        eventService.delete(event.getId());
    }

    @Test
    public void crud() throws Exception {
        //TESTS IF THE EVENTS ARE CREATED UPDATED AND DELETED
        Event event = createEvent();
        assertNotEquals(0,event.getId());
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018,3,31);
        Date date = new Date(calendar.getTimeInMillis());
        Event e = updateEvent(event,date);
        assertEquals(e.getId(),event.getId());
        assertEquals("EditedTestEvent",e.getName());
        assertEquals(date,e.getDate());
        assertEquals("EditedNowhere",e.getLocation());
        assertEquals("Boxing",e.getType());
        eventService.delete(e.getId());
        assertEquals(null,eventService.findById(event.getId()));
    }

    private Event createEvent(){
        Event event = new Event();
        event.setName("TestEvent");
        event.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
        event.setLocation("Nowhere");
        event.setType("Football");
        return eventService.save(event);
    }

    private Event updateEvent(final Event event,final Date date){
        event.setName("EditedTestEvent");
        event.setDate(date);
        event.setLocation("EditedNowhere");
        event.setType("Boxing");
        return eventService.save(event);
    }

}