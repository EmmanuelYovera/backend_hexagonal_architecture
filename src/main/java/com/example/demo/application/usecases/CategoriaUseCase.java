package com.example.demo.application.usecases;

import com.example.demo.domain.models.Categoria;
import com.example.demo.domain.ports.in.CategoriaPort;
import com.example.demo.domain.ports.out.CategoriaRepositoryPort;

import java.util.List;
import java.util.Optional;

public class CategoriaUseCase implements CategoriaPort {
    private final CategoriaRepositoryPort categoriaRepositoryPort;

    public CategoriaUseCase (CategoriaRepositoryPort categoriaRepositoryPort){
        this.categoriaRepositoryPort = categoriaRepositoryPort;
    }

    @Override
    public Categoria guardarCategoria(Categoria categoria){
        return categoriaRepositoryPort.save(categoria);
    }

    @Override
    public Optional<Categoria> obtenerCategoriaPorId(Long id){
        return  categoriaRepositoryPort.findById(id);
    }

    @Override
    public List<Categoria> listarCategorias(){
        return categoriaRepositoryPort.findAll();
    }

    @Override
    public  Categoria actualizarCategoria(Long id, Categoria categoria){
        Optional<Categoria> categoriaExistente = categoriaRepositoryPort.findById(id);
        if (categoriaExistente.isPresent()){
            return categoriaRepositoryPort.update(id, categoria).orElseThrow(()-> new RuntimeException("No se puede actualizar la categoría"));
        }
        throw new RuntimeException("Categoría no encontrada");
    }

    @Override
    public void eliminarCategoria(Long id){
        categoriaRepositoryPort.delete(id);
    }
}
