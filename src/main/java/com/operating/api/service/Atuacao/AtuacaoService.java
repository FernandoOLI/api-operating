package com.operating.api.service.Atuacao;


import com.operating.api.model.Atuacao;
import org.springframework.http.ResponseEntity;

/**
 * Service interface responsible for handling operations provided for Examples.
 */
public interface AtuacaoService {

    public ResponseEntity<String> insert(Atuacao atuacao);

}
