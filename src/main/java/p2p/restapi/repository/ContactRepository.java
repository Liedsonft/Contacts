package p2p.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import p2p.restapi.model.Contact;

public interface ContactRepository  extends JpaRepository<Contact, Long>{
    
}
