package com.softeng.athleticsticket.service;

import com.softeng.athleticsticket.model.Ticket;
import com.softeng.athleticsticket.repository.GateRepository;
import com.softeng.athleticsticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    GateRepository gateRepository;

    public TicketService() {
        // Default constructor
    }

    public List<Ticket> getAll(){
        return ticketRepository.findAll();
    }

    public Ticket save(final Ticket ticket){
        return ticketRepository.save(ticket);
    }

    public Ticket save(final Ticket ticket,final int id){
        ticket.setGate(gateRepository.findById(id));
        return ticketRepository.save(ticket);
    }

    public Ticket findById(final int id){
        return ticketRepository.findById(id);
    }

    public void delete(final int id){
        ticketRepository.delete(id);
    }

}
