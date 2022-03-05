package com.operating.api.service.Vendedores.impl;

import com.operating.api.model.Vendedor;
import com.operating.api.repository.VendedoresRepository;
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

    public List<Vendedor> getById(int id) {
        return repository.findById(id).stream().collect(Collectors.toList());
    }

    @CachePut("Vendedor")
    public List<Vendedor> getAll() {
        return repository.findAll().stream().collect(Collectors.toList());
    }

    @CachePut("Vendedor")
    public ResponseEntity<String> insert(Vendedor vendedor) {
        if (validateCreate(vendedor)) {
            vendedor.setDataInclusao(new Date());
            repository.save(vendedor);
            return new ResponseEntity<>("Dados inseridos com sucesso!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Dados já existentes!", HttpStatus.NO_CONTENT);
    }

    private boolean validateCreate(Vendedor vendedor) {
        return repository.findById(vendedor.getId()).isEmpty();
    }


}
