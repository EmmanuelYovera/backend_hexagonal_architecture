package com.example.demo.application.service;

import com.example.demo.domain.models.Categoria;
import com.example.demo.domain.ports.out.CategoriaRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    private final CategoriaRepositoryPort categoriaRepositoryPort;

    public CategoriaService(CategoriaRepositoryPort categoriaRepositoryPort){
        this.categoriaRepositoryPort = categoriaRepositoryPort;
    }

    public Categoria guardarCategoria(Categoria categoria){
        return  categoriaRepositoryPort.save(categoria);
    }

    public Optional<Categoria> buscarCategoriaPorId(Long id){
        return  categoriaRepositoryPort.findById(id);
    }

    public List<Categoria> listarCategorias(){
        return categoriaRepositoryPort.findAll();
    }

    public Optional<Categoria> actualizarCategoria(Long id, Categoria categoria){
        return categoriaRepositoryPort.update(id, categoria);
    }

    public void eliminarCategoria(Long id){
        categoriaRepositoryPort.delete(id);
    }
}
