package com.operating.api.controller;

import com.operating.api.model.Vendedor;
import com.operating.api.service.Vendedores.VendedoresService;
import com.operating.api.service.Vendedores.impl.VendedoresServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity<List<Vendedor>> allList() throws SQLException {
		List<Vendedor> produtos = vendedoresServiceImpl.getAll();
		return ResponseEntity.ok().body(produtos);
	}


	@GetMapping(value = "/{id}")
	public ResponseEntity<List<Vendedor>> findById(@PathVariable int id) throws SQLException {
		List<Vendedor> produtos = vendedoresServiceImpl.getById(id);
		return ResponseEntity.ok().body(produtos);
	}

	@PostMapping(path = "/")
	public ResponseEntity<String> save(@RequestBody Vendedor vendedor) {
		return vendedoresServiceImpl.insert(vendedor);
	}


}
