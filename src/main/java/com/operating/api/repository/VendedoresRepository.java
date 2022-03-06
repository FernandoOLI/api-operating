package com.operating.api.repository;
import com.operating.api.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendedoresRepository extends JpaRepository<Vendedor, Long> {

    List<Vendedor> findAll();
    Vendedor findByNameAndPhoneAndAgeAndCityAndStateAndRegion(String Username, String phone,int age, String city,String state,String region);

    Vendedor findById(int user_id);

    void deleteById(int user_id);

}
