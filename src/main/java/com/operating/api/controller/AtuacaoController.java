package com.operating.api.controller;

import com.operating.api.model.Atuacao;
import com.operating.api.service.AtuacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Transactional
@RequestMapping(path = "/atuacao")
public class AtuacaoController {

    @Autowired
    private AtuacaoService atuacaoService;

    @PostMapping(path = "/")
    public ResponseEntity<String> save(@RequestBody Atuacao atuacao) {
        return atuacaoService.insert(atuacao);
    }

}
