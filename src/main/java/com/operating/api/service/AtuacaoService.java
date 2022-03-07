package com.operating.api.service;

import com.operating.api.model.Atuacao;
import com.operating.api.repository.AtuacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h2> Service</h2>
 * Atuacao controler possue somente o método post,
 * entretanto é feita a validação se existe a região antes de salvar
 * caso já exista é retornado o code 204
 */

@Service
public class AtuacaoService {

    @Autowired
    private AtuacaoRepository repository;

    @CachePut("Atuacao")
    public Atuacao getByRegion(String region) {
        return repository.findByRegion(region);
    }

    @CachePut("Atuacao")
    public ResponseEntity<String> insert(Atuacao atuacao) {
        if (getByRegion(atuacao.getRegion()) == null) {
            repository.save(atuacao);
            return new ResponseEntity<>("Dados inseridos com sucesso!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Dados já existentes!", HttpStatus.NO_CONTENT);
    }

}
