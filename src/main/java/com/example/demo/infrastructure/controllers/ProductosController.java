package com.example.demo.infrastructure.controllers;

import com.example.demo.application.service.ProductosService;
import com.example.demo.domain.models.Productos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductosController {
    private final ProductosService productosService;

    public ProductosController(ProductosService productosService) {
        this.productosService = productosService;
    }

    @PostMapping
    public ResponseEntity<Productos> guardarProducto(@RequestBody Productos producto) {
        return ResponseEntity.ok(productosService.guardarProducto(producto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Productos> buscarProducto(@PathVariable Long id) {
        return productosService.buscarProductoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Productos>> listarProductos() {
        return ResponseEntity.ok(productosService.listarProductos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Productos> actualizarProducto(@PathVariable Long id, @RequestBody Productos producto) {
        return productosService.actualizarProducto(id, producto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productosService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
