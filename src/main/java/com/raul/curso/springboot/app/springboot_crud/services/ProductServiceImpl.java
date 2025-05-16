package com.raul.curso.springboot.app.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raul.curso.springboot.app.springboot_crud.entities.Product;
import com.raul.curso.springboot.app.springboot_crud.respositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product p) {
        return repository.save(p);
    }

    @Transactional
    public Optional<Product> update(Long id, Product p) {
        Optional<Product> optionalProductDb = repository.findById(id);
        if(optionalProductDb.isPresent()) {
            Product productDb = optionalProductDb.orElseThrow();
            productDb.setSku(p.getSku());
            productDb.setName(p.getName());
            productDb.setPrice(p.getPrice());
            productDb.setDescription(p.getDescription());
            return Optional.of(repository.save(productDb));
        }
        return optionalProductDb;
    }

    @Transactional
    @Override
    public Optional<Product> delete(Long id) {
        Optional<Product> optionalProductDb = repository.findById(id);
        optionalProductDb.ifPresent(productDb -> {
            repository.delete(productDb);
        });
        return optionalProductDb;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsBySku(String sku) {
       return repository.existsBySku(sku);
    }

}
