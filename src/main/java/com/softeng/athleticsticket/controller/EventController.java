package com.softeng.athleticsticket.controller;

import com.softeng.athleticsticket.model.Event;
import com.softeng.athleticsticket.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@PreAuthorize("hasAuthority('ADMINISTRATOR')")
@RequestMapping(value="/events")
@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    public EventController() {
        // Default constructor
    }

    @RequestMapping(value="",method= RequestMethod.GET)
    public ModelAndView all(){
        final ModelAndView modelAndView = new ModelAndView("auth/events");
        modelAndView.addObject("events",eventService.getAll());
        return modelAndView;
    }

    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public ModelAndView view(@PathVariable("id") final int id){
        final ModelAndView modelAndView = new ModelAndView("auth/event");
        final Event event = eventService.findById(id);
        modelAndView.addObject("event",event);
        return modelAndView;
    }

    @RequestMapping(value="/create",method=RequestMethod.GET)
    public ModelAndView create(){
        final ModelAndView modelAndView = new ModelAndView("auth/admin/eventForm");
        modelAndView.addObject("url","/events/create");
        modelAndView.addObject("method","POST");
        modelAndView.addObject("event",new Event());
        return modelAndView;
    }

    @RequestMapping(value="/create",method=RequestMethod.POST)
    public ModelAndView createSubmit(@Valid final Event event, final BindingResult bindingResult){
        final ModelAndView modelAndView = new ModelAndView("auth/admin/eventForm");
        if(!bindingResult.hasErrors()){
            final Event e = eventService.save(event);
            modelAndView.addObject("success","The event was saved!");
            modelAndView.addObject("event",e);
            modelAndView.addObject("method","PUT");
            modelAndView.addObject("url","/events/edit");
        }else{
            modelAndView.addObject("method","POST");
            modelAndView.addObject("url","/events/create");
        }
        return modelAndView;
    }

    @RequestMapping(value="/edit",method=RequestMethod.PUT)
    public ModelAndView storeEdited(@Valid final Event event, final BindingResult bindingResult){
        final ModelAndView modelAndView = new ModelAndView("auth/admin/eventForm");
        if(!bindingResult.hasErrors()){
            final Event e = eventService.save(event);
            modelAndView.addObject("success","The event was saved!");
            modelAndView.addObject("event",e);
            modelAndView.addObject("method","PUT");
            modelAndView.addObject("url","/events/edit");
        }else{
            modelAndView.addObject("method","PUT");
            modelAndView.addObject("url","/events/edit");
        }
        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id")final int id){
        final ModelAndView modelAndView = new ModelAndView("auth/admin/eventForm");
        final Event e = eventService.findById(id);
        modelAndView.addObject("event",e);
        modelAndView.addObject("method","PUT");
        modelAndView.addObject("url","/events/edit");
        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
    public ModelAndView delete(@PathVariable("id")final int id){
        eventService.delete(id);
        return new ModelAndView("redirect:/events");
    }
}
