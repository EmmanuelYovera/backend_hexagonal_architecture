package com.example.demo.infrastructure.repositories;

import com.example.demo.domain.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
