package com.raul.curso.springboot.app.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import com.raul.curso.springboot.app.springboot_crud.entities.Product;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product p);

    Optional<Product> update(Long id, Product p);

    Optional<Product> delete(Long id);

    boolean existsBySku(String sku);

}
