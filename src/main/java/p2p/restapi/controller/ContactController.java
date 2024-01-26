package p2p.restapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import p2p.restapi.ContactNotFoundException;
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

    @GetMapping("/{id}")
    public Contact findById(@PathVariable Long id) {
        Optional<Contact> optionalContact = repository.findById(id);

        if (optionalContact.isPresent()) {
            return optionalContact.get();
        } else {
            throw new ContactNotFoundException("Contato não encontrado com o ID: " + id);
        }
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
