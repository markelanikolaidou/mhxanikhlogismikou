package com.softeng.athleticsticket.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Gate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int capacity;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="eid",referencedColumnName="id")
    private Event event;
    @OneToMany(mappedBy="gate")
    private List<Ticket> tickets;

    public Gate() {
        // Default constructor
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(final int capacity) {
        this.capacity = capacity;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(final Event event){
        this.event = event;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(final List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
