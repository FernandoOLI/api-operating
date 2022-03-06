package com.operating.api.controller;

import com.operating.api.model.Vendedor;
import com.operating.api.model.VendedorReponseList;
import com.operating.api.model.VendedorReponseUnit;
import com.operating.api.service.Vendedores.VendedoresService;
import com.operating.api.service.Vendedores.impl.VendedoresServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@Transactional
@RequestMapping(path = "/vendedor")
public class VendedorController {

    @Autowired
    private VendedoresServiceImpl vendedoresServiceImpl;
    @Autowired
    private VendedoresService generalService;

    @GetMapping(value = "/")
    public ResponseEntity<List<VendedorReponseList>> allList() throws SQLException {
        List<VendedorReponseList> produtos = vendedoresServiceImpl.getAll();
        return ResponseEntity.ok().body(produtos);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<VendedorReponseUnit> findById(@PathVariable int id) throws SQLException {
        VendedorReponseUnit result = vendedoresServiceImpl.getById(id);
        if (result != null)
            return ResponseEntity.ok().body(vendedoresServiceImpl.getById(id));
        else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "/")
    public ResponseEntity<String> save(@RequestBody Vendedor vendedor) {
        return vendedoresServiceImpl.insert(vendedor);
    }


}
