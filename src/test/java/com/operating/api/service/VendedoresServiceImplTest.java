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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VendedoresServiceImplTest {

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
        vendedor = new Vendedor(id, dataInclusao, name, phone, age, city, state, region);
        vendedores = new ArrayList<>();
        vendedores.add(vendedor);

        List<String> states = new ArrayList<>();
        states.add("OK");
        states.add("OFF");

        atuacao = new Atuacao(region,states);
        atuacoes = new ArrayList<>();
        atuacoes.add(atuacao);

    }

    @Test
    void should_bring_all() {
        when(repository.findAll()).thenReturn(vendedores);
        when(atuacaoRepository.findAll()).thenReturn(atuacoes);
        List<VendedorReponseList> response = service.getAll();
        assertThat(response.get(0).getName(), is(equalTo("test Name")));
        assertThat(response.size(), is(equalTo(1)));
        verify(repository).findAll();
    }

//    @Test
//    void should_bring_one() {
//        when(repository.findByUsername("testeName")).thenReturn(lista);
//        List<GeneralResponseDTO> response = service.getByTabela("testeName");
//        assertThat(response.get(0).getUsername(), is(equalTo("testeName")));
//        assertThat(response.size(), is(equalTo(1)));
//        verify(repository).findByUsername("testeName");
//    }

}
