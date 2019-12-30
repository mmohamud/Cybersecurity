/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Contact;
import sec.project.domain.User;
import sec.project.repository.ContactRepository;
import sec.project.repository.UserRepository;

/**
 *
 * @author mmohamud
 */
@Controller
public class ContactController {
    
    @Autowired
    private UserRepository userRepository;   
    @Autowired
    private ContactRepository contactRepository;
    
    @RequestMapping(value = "/{id}/contacts", method = RequestMethod.GET)
    public String contacts(Model model, @PathVariable Long id) {
        User user = userRepository.getOne(id);
        model.addAttribute("contacts", contactRepository.findByUser(user));
        return "contacts";
    }
    
    
    @RequestMapping(value = "/{id}/contacts/addContact", method = RequestMethod.GET)
    public String newContact(@PathVariable Long id) {
        return "addContact";
    }
    
    @RequestMapping(value = "/{id}/contacts/addContact", method = RequestMethod.POST)
    public String addContact(@RequestParam String name, @RequestParam String number, @PathVariable Long id) {
        Contact contact = new Contact();
        contact.setName(name);
        contact.setNumber(number);
        contact.setUser(userRepository.getOne(id));
        contactRepository.save(contact);
        return "redirect:/" + id + "/contacts";
    }
}
