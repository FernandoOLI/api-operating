package com.operating.api.controller;

import com.operating.api.model.Vendedor;
import com.operating.api.model.VendedorReponseList;
import com.operating.api.model.VendedorReponseUnit;
import com.operating.api.service.VendedoresService;
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
    private VendedoresService vendedoresService;

    @GetMapping(value = "/")
    public ResponseEntity<List<VendedorReponseList>> allList(){
        List<VendedorReponseList> produtos = vendedoresService.getAll();
        return ResponseEntity.ok().body(produtos);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<VendedorReponseUnit> findById(@PathVariable int id){
        VendedorReponseUnit result = vendedoresService.getById(id);
        if (result != null)
            return ResponseEntity.ok().body(vendedoresService.getById(id));
        else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "/")
    public ResponseEntity<String> save(@RequestBody Vendedor vendedor) {
        return vendedoresService.insert(vendedor);
    }


}
