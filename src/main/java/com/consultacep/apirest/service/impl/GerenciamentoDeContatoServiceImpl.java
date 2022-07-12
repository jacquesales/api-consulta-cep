package com.consultacep.apirest.service.impl;

import com.consultacep.apirest.service.GerenciamentoDeContatoService;
import com.consultacep.apirest.entity.Contato;
import com.consultacep.apirest.repository.ContatoRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.webjars.NotFoundException;
import static java.util.Objects.requireNonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GerenciamentoDeContatoServiceImpl implements GerenciamentoDeContatoService {

    private final ContatoRepository repository;
    private final ConsultaCepServiceImpl enderecoService;

    @Override
    public List<Contato> findAll() {
        return repository.findAll();
    }

    @Override
    public Contato findById(String id) {
        requireNonNull(id);
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Email não existe"));
    }

    @Override
    public Contato create(Contato contato) throws Exception {
        requireNonNull(contato, "O corpo da solicitação é obrigatório e não pode ser nulo");
        contato.setEndereco(enderecoService.findByCep(contato.getEndereco().getCode(), contato));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String date = sdf.format(new Date());
        contato.setDataDeCadastro(sdf.parse(date));
        return repository.save(contato);
    }

    @Override
    public Contato update(Contato contato) throws Exception {
        requireNonNull(contato, "O corpo da solicitação é obrigatório e não pode ser nulo");
        Contato contatoExists = repository.findById(contato.getEmail())
                .orElseThrow(() -> new NotFoundException("Este contato não existe"));

        contatoExists.setEmail(contato.getEmail());
        contatoExists.setNome(contato.getNome());
        contatoExists.setTelefone(contato.getTelefone());
        contato.setEndereco(enderecoService.findByCep(contato.getEndereco().getCode(), contato));
        contato.setDataDeCadastro(contato.getDataDeCadastro());
        return repository.save(contatoExists);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

}

