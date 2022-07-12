package com.consultacep.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.consultacep.apirest.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
