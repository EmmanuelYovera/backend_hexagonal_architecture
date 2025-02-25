package com.example.demo.domain.ports.in;

import com.example.demo.domain.models.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaPort {
    Categoria guardarCategoria(Categoria categoria);
    Optional<Categoria> obtenerCategoriaPorId(Long id);
    List<Categoria> listarCategorias();
    Categoria actualizarCategoria(Long id, Categoria categoria);
    void eliminarCategoria(Long id);
}
