package com.raul.curso.springboot.app.springboot_crud.respositories;

import org.springframework.data.repository.CrudRepository;

import com.raul.curso.springboot.app.springboot_crud.entities.Product;

public interface ProductRepository extends CrudRepository<Product,Long>{

    boolean existsBySku(String sku);
}
