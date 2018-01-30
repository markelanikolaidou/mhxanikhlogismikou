package com.softeng.athleticsticket.service;

import com.softeng.athleticsticket.model.Event;
import com.softeng.athleticsticket.model.Gate;
import com.softeng.athleticsticket.model.SimpleGate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsService {

    public StatisticsService() {
        // Default constructor
    }

    public List<SimpleGate> getSimpleGates(final Event event){
        final List<SimpleGate> simpleGates = new ArrayList<SimpleGate>();
        final List<Gate> gates = event.getGates();
        gates.forEach((g)->{
            SimpleGate simpleGate = new SimpleGate();
            simpleGate.setId(g.getId());
            simpleGate.setName(g.getName());
            simpleGate.setCapacity(g.getCapacity());
            simpleGate.setOccupied(g.getTickets().size());
            simpleGates.add(simpleGate);
        });
        return simpleGates;
    }

}

