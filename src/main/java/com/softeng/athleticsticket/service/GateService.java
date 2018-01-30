package com.softeng.athleticsticket.service;

import com.softeng.athleticsticket.model.Gate;
import com.softeng.athleticsticket.repository.EventRepository;
import com.softeng.athleticsticket.repository.GateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GateService {

    @Autowired
    private GateRepository gateRepository;
    @Autowired
    private EventRepository eventRepository;

    public GateService() {
        // Default constructor
    }

    public List<Gate> getAll(){
        return gateRepository.findAll();
    }

    public Gate findById(final int id){
        return gateRepository.findById(id);
    }

    public Gate save(final Gate g){
        return gateRepository.save(g);
    }

    public Gate save(final Gate g,final  int id){
        g.setEvent(eventRepository.findById(id));
        return gateRepository.save(g);
    }

    public void delete(final int id){
        gateRepository.delete(id);
    }

}
