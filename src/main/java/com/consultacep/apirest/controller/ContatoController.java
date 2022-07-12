package com.consultacep.apirest.controller;

import com.consultacep.apirest.service.impl.GerenciamentoDeContatoServiceImpl;
import com.consultacep.apirest.entity.Contato;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contato")
@RequiredArgsConstructor
public class ContatoController {

    private final GerenciamentoDeContatoServiceImpl service;

    @GetMapping
    public ResponseEntity<List<Contato>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> getById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Contato> create(@RequestBody Contato contato) throws Exception {
        return new ResponseEntity<>(service.create(contato), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Contato> update(@RequestBody Contato contato) throws Exception {
        Optional<Contato> optionalContato = Optional.ofNullable(service.findById(contato.getEmail()));
        if (optionalContato.isPresent()) {
            Contato contatoSaved = service.create(contato);
            return ResponseEntity.ok(service.update(contatoSaved));
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
