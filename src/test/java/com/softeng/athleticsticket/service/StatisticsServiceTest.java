package com.softeng.athleticsticket.service;

import com.softeng.athleticsticket.model.Event;
import com.softeng.athleticsticket.model.Gate;
import com.softeng.athleticsticket.model.SimpleGate;
import com.softeng.athleticsticket.model.Ticket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticsServiceTest {

    @Autowired
    private StatisticsService statisticsService;

    public StatisticsServiceTest() {
        // Default constructor
    }

    @Test
    public void getSimpleGates() throws Exception {
        Event event = createEvent();
        List<Gate> gateList = event.getGates();
        List<SimpleGate> simpleGateList = statisticsService.getSimpleGates(event);
        assertEquals(gateList.size(),simpleGateList.size());
        for(int i = 0; i < simpleGateList.size(); i++){
            Gate gate = gateList.get(i);
            SimpleGate simpleGate = simpleGateList.get(i);
            assertEquals(gate.getCapacity(),simpleGate.getCapacity());
            assertEquals(gate.getTickets().size(),simpleGate.getOccupied());
            assertEquals(gate.getName(),simpleGate.getName());
        }
    }

    private Event createEvent(){
        Event event = new Event();
        event.setName("TestEvent");
        event.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
        event.setLocation("Nowhere");
        event.setType("Football");

        List<Gate> gateList = new ArrayList<Gate>();
        for(int i = 0; i < 5; i++){
            Gate g = createGate(event,i);
            g.setTickets(createTickets(g,i));
            gateList.add(g);
        }

        event.setGates(gateList);
        return event;
    }

    private Gate createGate(final Event event,final int i){
        Gate gate = new Gate();
        gate.setCapacity(10+i);
        gate.setName("TEST"+i);
        gate.setEvent(event);
        return gate;
    }

    private List<Ticket> createTickets(final Gate gate,final  int j){
        List<Ticket> ticketList = new ArrayList<Ticket>();
        for(int i = 0; i < 5+j; i++){
            ticketList.add(createTicket(gate));
        }
        return ticketList;
    }

    private Ticket createTicket(final Gate gate) {
        Ticket ticket = new Ticket();
        ticket.setEmail("test@test.com");
        ticket.setFirst_name("FirstName");
        ticket.setLast_name("LastName");
        ticket.setPhone(1234);
        ticket.setGate(gate);
        return ticket;
    }
}