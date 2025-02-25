package com.example.demo.domain.ports.out;

import com.example.demo.domain.models.Categoria;
import com.example.demo.domain.models.Productos;

import java.util.List;
import java.util.Optional;

public interface ProductosRepositoryPort {
    Productos save(Productos productos);
    Optional<Productos> findById(Long id);
    List<Productos> findAll();
    Optional<Productos> update(Long id, Productos productos);
    void delete(Long id);
}
