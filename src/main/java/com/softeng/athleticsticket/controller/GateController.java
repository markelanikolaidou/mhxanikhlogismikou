package com.softeng.athleticsticket.controller;

import com.softeng.athleticsticket.model.Gate;
import com.softeng.athleticsticket.service.EventService;
import com.softeng.athleticsticket.service.GateService;
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
@RequestMapping("/gates")
@Controller
public class GateController {

    @Autowired
    GateService gateService;
    @Autowired
    EventService eventService;

    public GateController() {
        // Default constructor
    }

    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public ModelAndView getGate(@PathVariable("id")final int id){
        final ModelAndView modelAndView = new ModelAndView("auth/gate");
        modelAndView.addObject("gate",gateService.findById(id));
        return  modelAndView;
    }

    @RequestMapping(value="/create/{id}",method=RequestMethod.GET)
    public ModelAndView create(@PathVariable("id")final int id){
        final ModelAndView modelAndView = new ModelAndView("auth/admin/gateForm");
        modelAndView.addObject("method","POST");
        modelAndView.addObject("url","/gates/create/"+id);
        modelAndView.addObject("gate",new Gate());
        return modelAndView;
    }

    @RequestMapping(value="/create/{id}",method=RequestMethod.POST)
    public ModelAndView createSubmit(@PathVariable("id")final int id, @Valid final Gate gate,final BindingResult bindingResult){
        final ModelAndView modelAndView = new ModelAndView("auth/admin/gateForm");
        if(!bindingResult.hasErrors()){
            final Gate g = gateService.save(gate,id);
            modelAndView.addObject("success","The gate was saved!");
            modelAndView.addObject("gate",g);
            modelAndView.addObject("method","PUT");
            modelAndView.addObject("url","/gates/edit/"+id);
        }else{
            modelAndView.addObject("method","PUT");
            modelAndView.addObject("/gates/create/"+id);
        }
        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id")final int id){
        final ModelAndView modelAndView = new ModelAndView("auth/admin/gateForm");
        final Gate g = gateService.findById(id);
        modelAndView.addObject("gate",g);
        modelAndView.addObject("method","PUT");
        modelAndView.addObject("url","/gates/edit/"+g.getEvent().getId());
        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}",method=RequestMethod.PUT)
    public ModelAndView editSubmit(@PathVariable("id")final int id,@Valid final Gate gate, final BindingResult bindingResult){
        final ModelAndView modelAndView = new ModelAndView("auth/admin/gateForm");
        if(!bindingResult.hasErrors()){
            final Gate g = gateService.save(gate,id);
            modelAndView.addObject("success","The gate was saved!");
            modelAndView.addObject("gate",g);
            modelAndView.addObject("method","PUT");
            modelAndView.addObject("url","/gates/edit/"+id);
        }else{
            modelAndView.addObject("method","PUT");
            modelAndView.addObject("/gates/edit/");
        }
        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
    public ModelAndView delete(@PathVariable("id")final int id){
        gateService.delete(id);
        return new ModelAndView("redirect:/gates");
    }

}
