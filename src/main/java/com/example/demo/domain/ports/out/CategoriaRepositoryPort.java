package com.example.demo.domain.ports.out;

import com.example.demo.domain.models.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepositoryPort {
    Categoria save(Categoria categoria);
    Optional<Categoria> findById(Long id);
    List<Categoria> findAll();
    Optional<Categoria> update(Long id, Categoria categoria);
    void delete(Long id);
}
