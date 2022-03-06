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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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
class VendedoresServiceTest {

    @InjectMocks
    VendedoresService service;

    @Mock
    private VendedoresRepository repository;

    @Mock
    private AtuacaoRepository atuacaoRepository;

    private Vendedor vendedor;

    private List<Vendedor> vendedores;

    private Atuacao atuacao;

    private List<Atuacao> atuacoes;

    private VendedorReponseUnit vendedorUnit;

    private List<VendedorReponseList> lista;

    @BeforeEach
    void setUp() {
        int id = 1;
        String name = "test Name";
        Date dataInclusao = new Date(1L);
        String phone = "999";
        Integer age = 10;
        String city = "Test City";
        String state = "OK";
        String region = "Test region";

        List<String> states = new ArrayList<>();
        states.add("OK");
        states.add("OFF");
        atuacao = new Atuacao(region, states);
        atuacoes = new ArrayList<>();
        atuacoes.add(atuacao);

        vendedor = new Vendedor(id, dataInclusao, name, phone, age, city, state, region);
        vendedor.setAtuacao(atuacao);
        vendedores = new ArrayList<>();
        vendedores.add(vendedor);


    }

    @Test
    void should_bring_all_vendedores() {
        when(repository.findAll()).thenReturn(vendedores);
        List<VendedorReponseList> response = service.getAll();
        assertThat(response.get(0).getName(), is(equalTo("test Name")));
        verify(repository).findAll();
    }

    @Test
    void should_bring_one_vendedor() {
        when(repository.findById(1)).thenReturn(vendedor);
        VendedorReponseUnit response = service.getById(1);
        assertThat(response.getName(), is(equalTo("test Name")));
        verify(repository).findById(1);
    }

    @Test
    void should_bring_null_vendedor() {
        when(repository.findById(2)).thenReturn(null);
        VendedorReponseUnit response = service.getById(2);
        assertNull("Doesn't exist to id 2", response);
        verify(repository).findById(2);
    }


    @Test
    void should_insert_nok_vendedor() {
        when(repository.findByNameAndPhoneAndAgeAndCityAndStateAndRegion(vendedor.getName(), vendedor.getPhone(),
                vendedor.getAge(), vendedor.getCity(), vendedor.getState(),
                vendedor.getRegion())).thenReturn(vendedor);

        ResponseEntity<String> response = service.insert(vendedor);
        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.NO_CONTENT)));
    }

}
