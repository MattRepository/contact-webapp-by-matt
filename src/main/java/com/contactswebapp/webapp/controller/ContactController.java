package com.contactswebapp.webapp.controller;

import com.contactswebapp.webapp.model.Contact;
import com.contactswebapp.webapp.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ContactController {

    @Autowired
    ContactRepository contactRepository;

    @GetMapping("/")
    public ModelAndView showContacts() {
        ModelAndView mav = new ModelAndView("index");
        List<Contact> allContacts = contactRepository.findAll();
        mav.addObject("allContacts", allContacts);
        return mav;
    }

    @GetMapping("/add")
    public ModelAndView addContact() {
        ModelAndView mav = new ModelAndView("add");
        Contact newContact = new Contact();
        mav.addObject("adding",newContact);
        return mav;
    }

    @PostMapping("/save")
    public String saveContact(@ModelAttribute Contact contact) {
        contactRepository.save(contact);
        return "redirect:/";
    }

    @GetMapping("/update")
    public ModelAndView update(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("update");
        Contact contact = contactRepository.findById(id).get();
        mav.addObject("adding", contact);
        return mav;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam long id) {
        contactRepository.deleteById(id);
        return "redirect:/";
    }
}
