package p2p.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import p2p.restapi.model.Contact;
import p2p.restapi.repository.ContactRepository;

@RestController
@RequestMapping("/contact")
public class ContactController {
    
    @Autowired
    private ContactRepository repository;

    @GetMapping
    public List<Contact> listAll(){
       return repository.findAll();
    }

    @PostMapping
    public Contact save(@RequestBody Contact contact){
      return  repository.save(contact);
    }

    @PutMapping
    public Contact update(@RequestBody Contact contact){
        if(contact.getId()> 0){
         return repository.save(contact);
        } return null;
    }

    @DeleteMapping
    public void delete(@RequestBody Contact contact){
     if(contact.getId()> 0) {
        repository.delete(contact);
     }
    }
}
