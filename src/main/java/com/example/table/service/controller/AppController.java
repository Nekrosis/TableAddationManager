package com.example.table.service.controller;

import com.example.table.service.Person;
import com.example.table.service.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    private PersonDAO dao;

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Person> personList = dao.list();
        model.addAttribute("personList", personList);
        return "index";
    }

    @RequestMapping("/new")
    public String ShowNewForm(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return "new_form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("person") Person person) {
        dao.save(person);

        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") int id) {
        dao.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/info/{id}")
    public ModelAndView information(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("person_info");
        Person person = dao.getPerson(id);
        mav.addObject("person", person);
        return mav;
    }

    @RequestMapping("/married")
    public String married(Model model) {
        List<Person> personList = dao.spouse();
        model.addAttribute("personList", personList);
        return "spouse";
    }

    @RequestMapping(value = "/save_married", method = RequestMethod.GET)
//            params = {"id=partner", "partner =id"}, method = RequestMethod.GET)
    public String saveMarried(@RequestParam("id") int id,
                              @RequestParam("partner") Integer partnerId) {
        dao.saveMarried(id, partnerId);
        System.out.println("id: "+id+"partner: "+partnerId);
        return "redirect:/";
    }
//    @GetMapping("/save_married")
//    public String saveMarried(@RequestParam("id") int id,
//                              @RequestParam("partner") int partnerId){
//        dao.saveMarried(id,partnerId);
//        System.out.println("id: "+id+"partner: "+partnerId);
//        return "redirect:/";
//    }

}

