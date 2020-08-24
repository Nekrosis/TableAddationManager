package com.example.table.service.controller;

import com.example.table.service.Person;
import com.example.table.service.PersonEntity;
import com.example.table.service.PersonMapper;
import com.example.table.service.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@Transactional
@RequiredArgsConstructor
public class AppController {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        var personList = personRepository.findAll();
        var list = personList.stream().map(personMapper::toDto).collect(Collectors.toList());
        model.addAttribute("personList", list);
        return "index";
    }

    @RequestMapping("/new")
    public String ShowNewForm(Model model) {
        PersonEntity person = new PersonEntity();
        model.addAttribute("person", person);
        return "new_form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("person") Person person) {
        personRepository.save(personMapper.toEntity(person));
        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") int id) {
        personRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/info/{id}")
    public ModelAndView information(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("person_info");
        var person = personRepository.findById(id).get();
        mav.addObject("person", personMapper.toDto(person));
        return mav;
    }

    @RequestMapping("/married")
    public String married(Model model) {
        var personList = personRepository.findAllByPartnerIdIsNull();
        var list = personList.stream().map(personMapper::toDto).collect(Collectors.toList());
        model.addAttribute("personList", list);
        return "spouse";
    }

    @GetMapping(value = "/save_married", params = {"id", "partner_id"})
    public String saveMarried(@RequestParam("id") int id,
                              @RequestParam("partner_id") Integer partnerId) {
        log.info("id: {}, partner_id: {}", id, partnerId);
        personRepository.setMarried(id, partnerId);
        personRepository.setMarried(partnerId, id);
        return "redirect:/";
    }

}
