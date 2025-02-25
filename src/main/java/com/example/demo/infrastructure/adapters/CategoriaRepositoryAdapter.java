package com.example.demo.infrastructure.adapters;

import com.example.demo.domain.models.Categoria;
import com.example.demo.domain.ports.out.CategoriaRepositoryPort;
import com.example.demo.infrastructure.entities.CategoriaEntity;
import com.example.demo.infrastructure.repositories.CategoriaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CategoriaRepositoryAdapter implements CategoriaRepositoryPort {

    private final CategoriaRepository categoriaRepository;

    public CategoriaRepositoryAdapter(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria save(Categoria categoria) {
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setNombre(categoria.getNombre());

        CategoriaEntity saveEntity = categoriaRepository.save(categoriaEntity);
        return new Categoria(saveEntity.getId(), saveEntity.getNombre());
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id)
                .map(categoriaEntity -> new Categoria(categoriaEntity.getId(), categoriaEntity.getNombre()));
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll()
                .stream().map(categoriaEntity -> new Categoria(categoriaEntity.getId(), categoriaEntity.getNombre()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Categoria> update(Long id, Categoria categoria) {
        return categoriaRepository.findById(id).map(categoriaEntity -> {
            categoriaEntity.setNombre(categoria.getNombre());
            CategoriaEntity updateEntity = categoriaRepository.save(categoriaEntity);
            return new Categoria(updateEntity.getId(), updateEntity.getNombre());
        });
    }

    @Override
    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }
}
