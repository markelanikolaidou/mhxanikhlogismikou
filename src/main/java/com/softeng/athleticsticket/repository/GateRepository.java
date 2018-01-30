package com.softeng.athleticsticket.repository;

import com.softeng.athleticsticket.model.Gate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GateRepository extends JpaRepository<Gate, Integer> {

    List<Gate> findAll();
    Gate findById(int id);

}
