package com.softeng.athleticsticket.repository;

import com.softeng.athleticsticket.model.Ticket;
import com.softeng.athleticsticket.model.Gate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findAll();
    List<Ticket> findByGate(Gate gate);
    Ticket findById(int id);
}
