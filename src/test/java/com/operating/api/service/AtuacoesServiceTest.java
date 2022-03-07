package com.operating.api.service;

import com.operating.api.model.Atuacao;
import com.operating.api.model.Vendedor;
import com.operating.api.model.VendedorReponseList;
import com.operating.api.model.VendedorReponseUnit;
import com.operating.api.repository.AtuacaoRepository;
import com.operating.api.repository.VendedoresRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AtuacoesServiceTest {

    @InjectMocks
    AtuacaoService service;

    @Mock
    private AtuacaoRepository atuacaoRepository;

    private Atuacao atuacao;

    private List<Atuacao> atuacoes;


    @BeforeEach
    void setUp() {
        String region = "Test region";
        List<String> states = new ArrayList<>();
        states.add("OK");
        states.add("OFF");
        atuacao = new Atuacao(region, states);
        atuacoes = new ArrayList<>();
        atuacoes.add(atuacao);
    }

    @Test
    void should_bring_not_insert_atuacao() {
        when(atuacaoRepository.findByRegion(atuacao.getRegion())).thenReturn(atuacao);
        ResponseEntity<String> response = service.insert(atuacao);
        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.NO_CONTENT)));
        verify(atuacaoRepository).findByRegion(atuacao.getRegion());
    }

    @Test
    void should_bring_insert_atuacao() {
        when(atuacaoRepository.findByRegion(atuacao.getRegion())).thenReturn(null);
        ResponseEntity<String> response = service.insert(atuacao);
        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.CREATED)));
        verify(atuacaoRepository).findByRegion(atuacao.getRegion());
    }

    @Test
    void should_bring_find_region() {
        when(atuacaoRepository.findByRegion(atuacao.getRegion())).thenReturn(atuacao);
        Atuacao response = service.getByRegion(atuacao.getRegion());
        assertThat(response, is(equalTo(atuacao)));
        verify(atuacaoRepository).findByRegion(atuacao.getRegion());
    }
}
