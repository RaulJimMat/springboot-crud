package com.raul.curso.springboot.app.springboot_crud.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

//import com.raul.curso.springboot.app.springboot_crud.ProductValidation;
import com.raul.curso.springboot.app.springboot_crud.entities.Product;
import com.raul.curso.springboot.app.springboot_crud.services.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RequestMapping("/api/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    //@Autowired
    //private ProductValidation productValidation;

    @GetMapping
    // @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<Product> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    // @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Product> optionalProduct = service.findById(id);
        if(optionalProduct.isPresent()){
            return ResponseEntity.ok(optionalProduct.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result) {
        //productValidation.validate(product,result);
        if(result.hasErrors()){
            return validation(result);
        }
        Product productDb = service.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDb);
    }

    @PutMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result, @PathVariable Long id) {
        //productValidation.validate(product,result);
        if(result.hasErrors()){
            return validation(result);
        }
        Optional<Product> optionalProductDb = service.update(id, product);
        if(optionalProductDb.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optionalProductDb.get());
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Product> optionalProduct = service.delete(id);
        if(optionalProduct.isPresent()){
            return ResponseEntity.ok(optionalProduct.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err ->{
            errors.put (err.getField(),"El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
