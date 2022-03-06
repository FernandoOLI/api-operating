package com.operating.api.repository;

import com.operating.api.model.Atuacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtuacaoRepository extends JpaRepository<Atuacao, Long> {

    List<Atuacao> findAll();

    Atuacao findByRegion(String region);

}
