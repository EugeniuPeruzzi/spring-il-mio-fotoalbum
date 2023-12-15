package org.java.spring.db.serv;

import java.util.List;

import org.java.spring.db.pojo.Contatto;
import org.java.spring.db.repo.ContattoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContattoService {
    @Autowired
    private ContattoRepository contattoRepository;
    
    public List<Contatto> findAll() {
        
        return contattoRepository.findAll();
    }
    public Contatto findById(int id) {
        
        return contattoRepository.findById(id).get();
    }
    public void save(Contatto category) {
        
    	contattoRepository.save(category);
    }
    public void delete(Contatto category) {
        
    	contattoRepository.delete(category);
    }
}
