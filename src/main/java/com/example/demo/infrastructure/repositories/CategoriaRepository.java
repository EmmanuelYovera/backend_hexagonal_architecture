package com.example.demo.infrastructure.repositories;

import com.example.demo.infrastructure.entities.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
}
