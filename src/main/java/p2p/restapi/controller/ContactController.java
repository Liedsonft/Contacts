package p2p.restapi.controller;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<?> save(@RequestBody @Valid Contact contact, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        try {
            Contact savedContact = repository.save(contact);
            return new ResponseEntity<>(savedContact, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping
    public ResponseEntity<Contact> update(@RequestBody Contact contact) {
        if (contact.getId() == null || contact.getId() <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return repository.existsById(contact.getId()) ?
                new ResponseEntity<>(repository.save(contact), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
                return new ResponseEntity<>("Contato excluído com sucesso", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Contato não encontrado com o ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao excluir o contato", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }







}
