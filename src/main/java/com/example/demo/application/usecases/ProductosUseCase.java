package com.example.demo.application.usecases;

import com.example.demo.domain.models.Categoria;
import com.example.demo.domain.models.Productos;
import com.example.demo.domain.ports.in.ProductosPort;
import com.example.demo.domain.ports.out.CategoriaRepositoryPort;
import com.example.demo.domain.ports.out.ProductosRepositoryPort;

import java.util.List;
import java.util.Optional;

public class ProductosUseCase implements ProductosPort {
    private final ProductosRepositoryPort productosRepositoryPort;
    private final CategoriaRepositoryPort categoriaRepositoryPort;

    public ProductosUseCase (ProductosRepositoryPort productosRepositoryPort, CategoriaRepositoryPort categoriaRepositoryPort){
        this.productosRepositoryPort = productosRepositoryPort;
        this.categoriaRepositoryPort = categoriaRepositoryPort;
    }

    @Override
    public Productos createProductos (Productos productos) {
        if(productos.getCategoria() == null || productos.getCategoria().getId() == null){
            throw new RuntimeException("El producto debe tener una categoría válida");
        }
        Optional<Categoria> categoriaExistente = categoriaRepositoryPort.findById(productos.getCategoria().getId());
        return categoriaExistente
                .map(categoria -> productosRepositoryPort.save(productos))
                .orElseThrow(()-> new RuntimeException("La categoría no existe. No se puede registrar el producto"));
    }

    @Override
    public Optional<Productos> getProductoId(Long id){
        return productosRepositoryPort.findById(id);
    }

    @Override
    public List<Productos> getAllProductos(){
        return  productosRepositoryPort.findAll();
    }

    @Override
    public Productos updateProductos(Long id, Productos productos){
        return productosRepositoryPort.findById(id)
                .flatMap(p -> productosRepositoryPort.update(id, productos))
                .orElseThrow(() -> new RuntimeException("No se puede actualizar el producto porque no existe"));
    }


    @Override
    public void deleteProductos(Long id){
        productosRepositoryPort.delete(id);
    }
}
