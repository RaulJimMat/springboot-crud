package com.raul.curso.springboot.app.springboot_crud.respositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.raul.curso.springboot.app.springboot_crud.entities.Role;

public interface RoleRepository extends CrudRepository<Role,Long>{

    Optional<Role> findByName(String name);

}
