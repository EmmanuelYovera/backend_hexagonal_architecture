package com.example.demo.infrastructure.repositories;

import com.example.demo.infrastructure.entities.ProductosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductosRepository extends JpaRepository <ProductosEntity, Long> {
}
