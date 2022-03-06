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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VendedoresServiceImpl implements VendedoresService {

    @Autowired
    private VendedoresRepository repository;
    @Autowired
    private AtuacaoRepository repositoryAtuacao;

    @Autowired
    private AtuacaoServiceImpl atuacaoServiceImpl;

    @CachePut("Vendedor")
    public VendedorReponseUnit getById(int id) throws SQLException {
        Vendedor vendedor = repository.findById(id);
        if (vendedor != null) {
            Atuacao atuacao = repositoryAtuacao.findByRegion(vendedor.getRegion());
            if (atuacao != null) {
                return  new VendedorReponseUnit(vendedor.getName(), vendedor.getDataInclusao(), atuacao.getStates());
            } else return null;
        } else return null;
    }

    @CachePut("Vendedor")
    public List<VendedorReponseList> getAll() {
        List<Vendedor> vendedores = repository.findAll();
        List<Atuacao> atuacoes = repositoryAtuacao.findAll();

        return generate(vendedores,atuacoes);
    }
    
    private List<VendedorReponseList> generate(List<Vendedor> vendedores, List<Atuacao> atuacoes) {
        List<VendedorReponseList> result = new ArrayList<VendedorReponseList>();

        vendedores.forEach(vendedor -> {
            Atuacao atuacao = atuacoes.stream().filter(p -> p.getRegion().equals(vendedor.getRegion())).findFirst().orElse(null);
            result.add(new VendedorReponseList(vendedor.getName(),
                    vendedor.getPhone(),vendedor.getAge(),vendedor.getCity(),vendedor.getState(),
                    atuacao.getStates()));
        });
        return result;
    }

    @CachePut("Vendedor")
    public ResponseEntity<String> insert(Vendedor vendedor) {
        if (validateCreate(vendedor)) {
            vendedor.setDataInclusao(new Date());
            if (validateRegionAndState(vendedor)) {
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

    private boolean validateRegionAndState(Vendedor vendedor) {
        Atuacao atuacao = atuacaoServiceImpl.getByRegion(vendedor.getRegion());
        if (atuacao == null)
            return false;
        else return atuacao.getStates().contains(vendedor.getState());
    }

}
