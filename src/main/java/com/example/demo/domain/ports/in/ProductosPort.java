package com.example.demo.domain.ports.in;

import com.example.demo.domain.models.Productos;

import java.util.List;
import java.util.Optional;

public interface ProductosPort {
    Productos createProductos(Productos productos);
    Optional<Productos> getProductoId(Long id);
    List<Productos> getAllProductos();
    Productos updateProductos(Long id, Productos productos);
    void deleteProductos(Long id);
}
