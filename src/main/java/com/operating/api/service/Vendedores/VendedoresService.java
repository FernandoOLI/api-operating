package com.operating.api.service.Vendedores;

import com.operating.api.model.Vendedor;
import org.springframework.http.ResponseEntity;

/**
 * Service interface responsible for handling operations provided for Examples.
 */
public interface VendedoresService {

    public ResponseEntity<String> insert(Vendedor vendedor);

}
