package org.java.spring.controler;

import java.util.List;

import org.java.spring.db.pojo.Contatto;
import org.java.spring.db.serv.ContattoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContattoControlle {

	@Autowired
	private ContattoService contattoService;
	
	
    @GetMapping("/contacts")
    public String getContacts(Model model) {
        
        List<Contatto> contatti = contattoService.findAll();
        
        model.addAttribute("contatti", contatti);
        
        return "contattoHTML/contattoIndex";
    }
    
}
