package com.softeng.athleticsticket.controller;

import com.softeng.athleticsticket.model.Event;
import com.softeng.athleticsticket.service.EventService;
import com.softeng.athleticsticket.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@PreAuthorize("hasAuthority('ADMINISTRATOR')")
@RequestMapping("/statistics")
@Controller
public class StatisticsController {

    @Autowired
    EventService eventService;
    @Autowired
    StatisticsService statisticsService;

    public StatisticsController() {
        // Default constructor
    }

    @RequestMapping(value="",method=RequestMethod.GET)
    public ModelAndView search(@RequestParam("name")final Optional<String> name){
        final ModelAndView modelAndView = new ModelAndView("auth/finder");
        modelAndView.addObject("url","/statistics/event/");
        modelAndView.addObject("submit","/statistics/");
        if(name.isPresent()){
            modelAndView.addObject("events",eventService.search(name.get()));
        }
        return modelAndView;
    }

    @RequestMapping(value="/event/{id}",method= RequestMethod.GET)
    public ModelAndView event(@PathVariable("id")final int id){
        final Event e = eventService.findById(id);
        final ModelAndView modelAndView = new ModelAndView("auth/admin/eventStats");
        modelAndView.addObject("event",e);
        modelAndView.addObject("simpleGates",statisticsService.getSimpleGates(e));
        return modelAndView;
    }

}
