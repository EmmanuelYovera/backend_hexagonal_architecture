package com.example.demo.infrastructure.adapters;

import com.example.demo.domain.models.Categoria;
import com.example.demo.domain.models.Productos;
import com.example.demo.domain.ports.out.ProductosRepositoryPort;
import com.example.demo.infrastructure.entities.CategoriaEntity;
import com.example.demo.infrastructure.entities.ProductosEntity;
import com.example.demo.infrastructure.repositories.CategoriaRepository;
import com.example.demo.infrastructure.repositories.ProductosRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductosRepositoryAdapter implements ProductosRepositoryPort {
    private final ProductosRepository productosRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductosRepositoryAdapter(ProductosRepository productosRepository, CategoriaRepository categoriaRepository){
        this.productosRepository = productosRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override

    public Productos save(Productos productos) {
        if (productos.getPrecio() < 0) {
            throw new RuntimeException("El precio del producto no puede ser negativo.");
        }

        CategoriaEntity categoriaEntity = categoriaRepository.findById(productos.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + productos.getCategoria().getId()));

        ProductosEntity productosEntity = new ProductosEntity();
        productosEntity.setNombre(productos.getNombre());
        productosEntity.setPrecio(productos.getPrecio());
        productosEntity.setCategoria(categoriaEntity);

        ProductosEntity saveEntity = productosRepository.save(productosEntity);
        return new Productos(
                saveEntity.getId(),
                saveEntity.getNombre(),
                saveEntity.getPrecio(),
                new Categoria(saveEntity.getCategoria().getId(), saveEntity.getCategoria().getNombre())
        );
    }


    @Override
    public Optional<Productos> findById(Long id) {
        return  productosRepository.findById(id)
                .map(productosEntity -> new Productos(
                        productosEntity.getId(),
                        productosEntity.getNombre(),
                        productosEntity.getPrecio(),
                        new Categoria(productosEntity.getCategoria().getId(), productosEntity.getCategoria().getNombre())
                ));
    }

    @Override
    public List<Productos> findAll() {
        return productosRepository.findAll().stream()
                .map(productosEntity -> new Productos(
                        productosEntity.getId(),
                        productosEntity.getNombre(),
                        productosEntity.getPrecio(),
                        new Categoria(productosEntity.getCategoria().getId(), productosEntity.getCategoria().getNombre())
                )).collect(Collectors.toList());
    }

    @Override
    public Optional<Productos> update(Long id, Productos productos) {
        return productosRepository.findById(id).map(productosEntity -> {
            productosEntity.setNombre(productos.getNombre());
            productosEntity.setPrecio(productos.getPrecio());
            productosEntity.setCategoria(new CategoriaEntity(productosEntity.getCategoria().getId(), productosEntity.getCategoria().getNombre()));

            ProductosEntity updateEntity = productosRepository.save(productosEntity);
            return new Productos(
                    updateEntity.getId(),
                    updateEntity.getNombre(),
                    updateEntity.getPrecio(),
                    new Categoria(updateEntity.getCategoria().getId(), updateEntity.getCategoria().getNombre())
            );
        });
    }

    @Override
    public void delete(Long id) {
        productosRepository.deleteById(id);
    }
}
