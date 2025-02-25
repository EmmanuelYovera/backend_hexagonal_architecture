package com.example.demo.domain.ports.in;

import com.example.demo.domain.models.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaPort {
    Categoria createCategoria(Categoria categoria);
    Optional<Categoria> getCategoriaId(Long id);
    List<Categoria> getAllCategorias();
    Categoria updateCategoria(Long id, Categoria categoria);
    void deleteCategoria(Long id);
}
