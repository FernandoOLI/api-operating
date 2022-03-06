package com.operating.api.controller;

import com.operating.api.model.Atuacao;
import com.operating.api.service.AtuacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@Transactional
@RequestMapping(path = "/atuacao")
public class AtuacaoController {

    @Autowired
    private AtuacaoService atuacaoService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Atuacao>> allList(){
        List<Atuacao> atuacoes = atuacaoService.getAll();
        if (atuacoes.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(atuacoes, HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<String> save(@RequestBody Atuacao atuacao) {
        return atuacaoService.insert(atuacao);
    }

}
