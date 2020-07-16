package com.example.table.service.controller;

import com.example.table.service.Person;
import com.example.table.service.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.zip.DataFormatException;

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
    public String save(@ModelAttribute("person") Person person)
           {
        dao.save(person);

        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") int id) {
        dao.delete(id);
        return "redirect:/";
    }
}
