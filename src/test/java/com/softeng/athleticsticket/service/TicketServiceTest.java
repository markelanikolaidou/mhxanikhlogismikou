package com.softeng.athleticsticket.service;

import com.softeng.athleticsticket.model.Event;
import com.softeng.athleticsticket.model.Gate;
import com.softeng.athleticsticket.model.Ticket;

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
public class TicketServiceTest {

    @Autowired
    private GateService gateService;
    @Autowired
    private EventService eventService;
    @Autowired
    private TicketService ticketService;

    public TicketServiceTest() {
        // Default constructor
    }

    @Test
    public void getAll() throws Exception {
        List<Ticket> ticketList = ticketService.getAll();
        ticketList.forEach((ticket)->{
            assertNotEquals(0,ticket.getId());
            assertNotEquals(0,ticket.getPhone());
            assertNotEquals(null,ticket.getGate());
            assertNotEquals(null,ticket.getEmail());
            assertNotEquals(null,ticket.getFirst_name());
            assertNotEquals(null,ticket.getLast_name());
        });
    }

    @Test
    public void crud() throws Exception {
        //TESTS IF THE TICKETS ARE CREATED UPDATED AND DELETED
        Event event = createEvent();
        Gate gate = createGate(event);
        Ticket ticket = createTicket();
        ticket = ticketService.save(ticket,gate.getId());
        assertNotEquals(0,ticket.getId());
        ticket = editTicket(ticket);
        Ticket t = ticketService.save(ticket);
        assertEquals(t.getId(),ticket.getId());
        assertEquals("edited@test.com",t.getEmail());
        assertEquals("EditedFirstName",t.getFirst_name());
        assertEquals("EditedLastName",t.getLast_name());
        assertEquals(4321,t.getPhone());
        ticketService.delete(ticket.getId());
        assertEquals(null,ticketService.findById(t.getId()));
        gateService.delete(gate.getId());
        eventService.delete(event.getId());
    }

    private Ticket editTicket(final Ticket ticket){
        ticket.setEmail("edited@test.com");
        ticket.setFirst_name("EditedFirstName");
        ticket.setLast_name("EditedLastName");
        ticket.setPhone(4321);
        return ticket;
    }

    private Ticket createTicket(){
        Ticket ticket = new Ticket();
        ticket.setEmail("test@test.com");
        ticket.setFirst_name("FirstName");
        ticket.setLast_name("LastName");
        ticket.setPhone(1234);
        return ticket;
    }

    private Gate createGate(final Event event){
        Gate gate = new Gate();
        gate.setCapacity(100);
        gate.setName("TEST");
        gate.setEvent(event);
        return gateService.save(gate);
    }

    private Event createEvent(){
        Event event = new Event();
        event.setName("TestEvent");
        event.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
        event.setLocation("Nowhere");
        event.setType("Football");
        return eventService.save(event);
    }

}