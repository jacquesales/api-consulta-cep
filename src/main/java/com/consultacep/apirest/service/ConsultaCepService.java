package com.consultacep.apirest.service;

import com.consultacep.apirest.entity.Contato;
import com.consultacep.apirest.entity.Endereco;

public interface ConsultaCepService {
    Endereco findByCep(String cep, Contato contato) throws Exception;
}
