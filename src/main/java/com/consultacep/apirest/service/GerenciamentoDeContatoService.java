package com.consultacep.apirest.service;

import com.consultacep.apirest.entity.Contato;
import java.util.List;

public interface GerenciamentoDeContatoService {

    List<Contato> findAll();

    Contato findById(String id);

    Contato create(Contato contato) throws Exception;

    Contato update(Contato contato) throws Exception;

    void delete(String id);

}
