package com.example.demo.application.usecases;

import com.example.demo.domain.models.Categoria;
import com.example.demo.domain.models.Productos;
import com.example.demo.domain.ports.in.ProductosPort;
import com.example.demo.domain.ports.out.CategoriaRepositoryPort;
import com.example.demo.domain.ports.out.ProductosRepositoryPort;
import org.hibernate.grammars.hql.HqlParser;

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
    public Productos guardarProductos(Productos productos){
        Optional<Categoria> categoriaExistente = categoriaRepositoryPort.findById(productos.getCategoria().getId());
        if (categoriaExistente.isPresent()){
            return productosRepositoryPort.save(productos);
        }else {
            throw new RuntimeException("La categoría no existe. No se puede registrar el producto");
        }
    }

    @Override
    public Optional<Productos> obtenerProductoPorId(Long id){
        return productosRepositoryPort.findById(id);
    }

    @Override
    public List<Productos> listarProductos(){
        return  productosRepositoryPort.findAll();
    }

    @Override
    public Productos actualizarProductos(Long id, Productos productos){
        Optional<Productos> productoExistente = productosRepositoryPort.findById(id);
        if (productoExistente.isPresent()){
            return productosRepositoryPort.update(id, productos)
                    .orElseThrow(()->new RuntimeException("No se puede actualizar el producto"));
        }
        throw new RuntimeException("Producto no encontrado");
    }

    @Override
    public void eliminarProductos(Long id){
        productosRepositoryPort.delete(id);
    }
}
