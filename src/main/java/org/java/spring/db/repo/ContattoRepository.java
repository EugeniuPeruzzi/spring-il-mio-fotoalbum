package org.java.spring.db.repo;

import org.java.spring.db.pojo.Contatto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContattoRepository extends JpaRepository<Contatto, Integer> {
}
