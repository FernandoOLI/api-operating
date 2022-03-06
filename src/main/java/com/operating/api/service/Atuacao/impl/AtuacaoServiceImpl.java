package com.operating.api.service.Atuacao.impl;

import com.operating.api.model.Atuacao;
import com.operating.api.repository.AtuacaoRepository;
import com.operating.api.service.Atuacao.AtuacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtuacaoServiceImpl implements AtuacaoService {

    @Autowired
    private AtuacaoRepository repository;

    @CachePut("Atuacao")
    public Atuacao getByRegion(String region) {
        return repository.findByRegion(region);
    }

    @CachePut("Atuacao")
    public List<Atuacao> getAll() {
        return repository.findAll().stream().collect(Collectors.toList());
    }

    @CachePut("Atuacao")
    public ResponseEntity<String> insert(Atuacao atuacao) {
        if (validateCreate(atuacao)) {
            repository.save(atuacao);
            return new ResponseEntity<>("Dados inseridos com sucesso!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Dados já existentes!", HttpStatus.CONFLICT);
    }


    private boolean validateCreate(Atuacao Atuacao) {
        return repository.findByRegion(Atuacao.getRegion()) == null;
    }


}
