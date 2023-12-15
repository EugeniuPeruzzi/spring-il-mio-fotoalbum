package org.java.spring.restcontroller;

import org.java.spring.db.pojo.Contatto;
import org.java.spring.db.serv.ContattoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contatto")
public class ContattoRestController {

    @Autowired
    private ContattoService contattoService;

    @PostMapping
    public ResponseEntity<String> inviaMessaggio(@RequestBody Contatto contatto) {

            contattoService.save(contatto);
            return new ResponseEntity<>("Messaggio inviato con successo!", HttpStatus.OK);

    }
}
