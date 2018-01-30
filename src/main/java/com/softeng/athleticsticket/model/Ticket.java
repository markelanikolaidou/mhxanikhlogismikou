package com.softeng.athleticsticket.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String first_name;
    private String last_name;
    @Email
    private String email;
    private int phone;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gid",referencedColumnName="id")
    private Gate gate;

    public Ticket() {
        // Default constructor
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(final String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(final String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(final int phone) {
        this.phone = phone;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(final Gate gate) {
        this.gate = gate;
    }
}
