package com.softeng.athleticsticket.service;

import com.softeng.athleticsticket.model.Event;
import com.softeng.athleticsticket.model.Gate;
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
public class GateServiceTest {

    @Autowired
    private GateService gateService;
    @Autowired
    private EventService eventService;

    public GateServiceTest() {
        // Default constructor
    }

    @Test
    public void getAll() throws Exception {
        //TESTS IF ALL THE GATES IN THE DATABASE HAVE THE REQUIRED INFORMATION
        List<Gate> gateList = gateService.getAll();
        gateList.forEach((gate)->{
            assertNotEquals(0,gate.getId());
            assertNotEquals(0,gate.getCapacity());
            assertNotEquals(null,gate.getEvent());
            assertNotEquals(null,gate.getName());
        });
    }

    @Test
    public void crud() throws Exception {
        //TESTS IF THE GATES ARE CREATED UPDATED AND DELETED
        Gate gate = createGate();
        Event event = createEvent();
        event = eventService.save(event);
        gate = gateService.save(gate,event.getId());
        assertNotEquals(0,gate.getId());
        gate = editGate(gate);
        Gate g = gateService.save(gate);
        assertEquals(g.getId(),gate.getId());
        assertEquals(200,g.getCapacity());
        assertEquals("EDITED",g.getName());
        gateService.delete(g.getId());
        assertEquals(null,gateService.findById(g.getId()));
        eventService.delete(event.getId());
    }

    private Gate createGate(){
        Gate gate = new Gate();
        gate.setCapacity(100);
        gate.setName("TEST");
        return gate;
    }

    private Gate editGate(final Gate gate){
        gate.setCapacity(200);
        gate.setName("EDITED");
        return gate;
    }

    private Event createEvent(){
        Event event = new Event();
        event.setName("TestEvent");
        event.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
        event.setLocation("Nowhere");
        event.setType("Football");
        return event;
    }

}