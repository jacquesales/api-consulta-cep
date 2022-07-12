package com.consultacep.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.consultacep.apirest.entity.Contato;

public interface ContatoRepository extends JpaRepository<Contato, String> {

}
