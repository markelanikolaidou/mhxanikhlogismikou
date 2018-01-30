package com.softeng.athleticsticket.controller;

import com.softeng.athleticsticket.model.Gate;
import com.softeng.athleticsticket.model.Ticket;
import com.softeng.athleticsticket.service.EventService;
import com.softeng.athleticsticket.service.GateService;
import com.softeng.athleticsticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@PreAuthorize("hasAuthority('CASHIER')")
@RequestMapping("/tickets")
@Controller
public class TicketController {

    @Autowired
    EventService eventService;
    @Autowired
    TicketService ticketService;
    @Autowired
    GateService gateService;

    public TicketController() {
        // Default constructor
    }

    @RequestMapping(value="",method=RequestMethod.GET)
    public ModelAndView index(){
        final ModelAndView modelAndView = new ModelAndView("auth/tickets");
        modelAndView.addObject("tickets",ticketService.getAll());
        return modelAndView;
    }

    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public ModelAndView ticket(@PathVariable("id")final int id){
        final ModelAndView modelAndView = new ModelAndView("auth/ticket");
        modelAndView.addObject("ticket",ticketService.findById(id));
        return modelAndView;
    }

    @RequestMapping(value="/issue",method=RequestMethod.GET)
    public ModelAndView issue(@RequestParam("name")final Optional<String> name){
        final ModelAndView modelAndView = new ModelAndView("auth/finder");
        modelAndView.addObject("url","/tickets/gates/");
        modelAndView.addObject("submit","/tickets/issue");
        if(name.isPresent()){
            modelAndView.addObject("events",eventService.searchUpcoming(name.get()));
        }
        return modelAndView;
    }

    @RequestMapping(value="/gates/{id}",method=RequestMethod.GET)
    public ModelAndView gates(@PathVariable("id")final int id){
        final ModelAndView modelAndView = new ModelAndView("auth/ticketEventGates");
        modelAndView.addObject("event",eventService.findById(id));
        return modelAndView;
    }

    @RequestMapping(value="/create/{id}",method=RequestMethod.GET)
    public ModelAndView create(@PathVariable("id")final int id){
        final ModelAndView modelAndView = new ModelAndView();
        final Gate gate = gateService.findById(id);
        if(gate.getCapacity()-gate.getTickets().size() == 0){
            modelAndView.setViewName("redirect:/issue");
        }else{
            modelAndView.setViewName("auth/ticketForm");
            modelAndView.addObject("ticket",new Ticket());
            modelAndView.addObject("url","/tickets/create/"+id);
            modelAndView.addObject("method","POST");
        }
        return  modelAndView;
    }

    @RequestMapping(value="/create/{id}",method=RequestMethod.POST)
    public ModelAndView createSubmit(@PathVariable("id")final int id, @Valid final Ticket ticket, final BindingResult bindingResult){
        final ModelAndView modelAndView = new ModelAndView();
        if(!bindingResult.hasErrors()){
            modelAndView.setViewName("auth/ticket");
            final Ticket t = ticketService.save(ticket,id);
            modelAndView.addObject("/auth/ticket",t);
        }else{
            modelAndView.setViewName("auth/ticketForm");
            modelAndView.addObject("url","/tickets/create/"+id);
            modelAndView.addObject("method","POST");
        }
        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id")final int id){
        final ModelAndView modelAndView = new ModelAndView("auth/ticketForm");
        final Ticket ticket = ticketService.findById(id);
        modelAndView.addObject("ticket",ticket);
        modelAndView.addObject("url","/tickets/edit/"+ticket.getGate().getId());
        modelAndView.addObject("method","put");

        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}",method=RequestMethod.PUT)
    public ModelAndView editSubmnit(@PathVariable("id")final int id,@Valid final Ticket ticket, final BindingResult bindingResult){
        final ModelAndView modelAndView = new ModelAndView("auth/ticketForm");
        if(!bindingResult.hasErrors()){
            final Ticket t = ticketService.save(ticket,id);
            modelAndView.addObject("ticket",t);
            modelAndView.addObject("url","/tickets/edit/"+ticket.getGate().getId());
            modelAndView.addObject("method","PUT");
            modelAndView.addObject("success","Ticket saved successful");
        }else{
            modelAndView.addObject("url","/tickets/edit/"+ticket.getGate().getId());
            modelAndView.addObject("method","PUT");
        }
        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
    public ModelAndView delete(@PathVariable("id")final int id){
        final ModelAndView modelAndView = new ModelAndView("redirect:/tickets");
        ticketService.delete(id);
        return modelAndView;
    }
}
