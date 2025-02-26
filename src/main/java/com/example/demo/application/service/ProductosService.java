package com.example.demo.application.service;

import com.example.demo.domain.models.Productos;
import com.example.demo.domain.ports.out.ProductosRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductosService {
    private final ProductosRepositoryPort productosRepositoryPort;

    public ProductosService(ProductosRepositoryPort productosRepositoryPort) {
        this.productosRepositoryPort = productosRepositoryPort;
    }

    public Productos guardarProducto(Productos producto) {
        return productosRepositoryPort.save(producto);
    }

    public Optional<Productos> buscarProductoPorId(Long id) {
        return productosRepositoryPort.findById(id);
    }

    public List<Productos> listarProductos() {
        return productosRepositoryPort.findAll();
    }

    public Optional<Productos> actualizarProducto(Long id, Productos producto) {
        return productosRepositoryPort.update(id, producto);
    }

    public void eliminarProducto(Long id) {
        productosRepositoryPort.delete(id);
    }
}
