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
    public Categoria createCategoria(Categoria categoria){
        return categoriaRepositoryPort.save(categoria);
    }

    @Override
    public Optional<Categoria> getCategoriaId(Long id){
        return  categoriaRepositoryPort.findById(id);
    }

    @Override
    public List<Categoria> getAllCategorias(){
        return categoriaRepositoryPort.findAll();
    }

    @Override
    public Categoria updateCategoria(Long id, Categoria categoria){
        return categoriaRepositoryPort.update(id, categoria)
                .orElseThrow(() -> new RuntimeException("No se puede actualizar la categoría porque no existe"));
    }

    @Override
    public void deleteCategoria(Long id){
        categoriaRepositoryPort.delete(id);
    }
}
