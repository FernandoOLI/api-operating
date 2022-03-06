package com.operating.api.service.Vendedores.impl;

import com.operating.api.model.Atuacao;
import com.operating.api.model.Vendedor;
import com.operating.api.model.VendedorReponseList;
import com.operating.api.model.VendedorReponseUnit;
import com.operating.api.repository.AtuacaoRepository;
import com.operating.api.repository.VendedoresRepository;
import com.operating.api.service.Atuacao.impl.AtuacaoServiceImpl;
import com.operating.api.service.Vendedores.VendedoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendedoresServiceImpl implements VendedoresService {

    @Autowired
    private VendedoresRepository repository;
    @Autowired
    private AtuacaoRepository repositoryAtuacao;

    @Autowired
    private AtuacaoServiceImpl atuacaoServiceImpl;

    @CachePut("Vendedor")
    public VendedorReponseUnit getById(int id) {
        Vendedor vendedor = repository.findById(id);
        return new VendedorReponseUnit(vendedor.getName(), vendedor.getDataInclusao(), vendedor.getAtuacao().getStates());
    }

    @CachePut("Vendedor")
    public List<VendedorReponseList> getAll() {
        List<Vendedor> vendedores = repository.findAll();
        return generate(vendedores);
    }

    private List<VendedorReponseList> generate(List<Vendedor> vendedores) {

       return vendedores.stream()
                .map(vendedor -> new VendedorReponseList(vendedor.getName(),
                        vendedor.getPhone(), vendedor.getAge(), vendedor.getCity(), vendedor.getState(),
                        vendedor.getAtuacao().getStates()))
                .collect(Collectors.toList());

    }

    @CachePut("Vendedor")
    public ResponseEntity<String> insert(Vendedor vendedor) {
        if (validateCreate(vendedor)) {
            vendedor.setDataInclusao(new Date());
            Atuacao atuacao = validateRegionAndState(vendedor);
            if (atuacao != null) {
                vendedor.setAtuacao(atuacao);
                repository.save(vendedor);
                return new ResponseEntity<>("Dados inseridos com sucesso!", HttpStatus.OK);
            } else
                return new ResponseEntity<>("Valor do Estado e/ou Região inseridos não existentes em atuacao!",
                        HttpStatus.NO_CONTENT);
        } else
            return new ResponseEntity<>("Dados já existentes!", HttpStatus.NO_CONTENT);
    }

    private boolean validateCreate(Vendedor vendedor) {
        return repository.findById(vendedor.getId()) == null;
    }

    private Atuacao validateRegionAndState(Vendedor vendedor) {
        return  atuacaoServiceImpl.getByRegion(vendedor.getRegion());
    }

}
