package com.example.demo.domain.ports.in;

import com.example.demo.domain.models.Productos;

import java.util.List;
import java.util.Optional;

public interface ProductosPort {
    Productos guardarProductos(Productos productos);
    Optional<Productos> obtenerProductoPorId(Long id);
    List<Productos> listarProductos();
    Productos actualizarProductos(Long id, Productos productos);
    void eliminarProductos(Long id);
}
