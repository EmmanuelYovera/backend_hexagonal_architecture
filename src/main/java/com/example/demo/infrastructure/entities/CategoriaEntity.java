package com.example.demo.infrastructure.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name= "categorias")

public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<ProductosEntity> productos;

    public CategoriaEntity(){}

    public CategoriaEntity(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ProductosEntity> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductosEntity> productos) {
        this.productos = productos;
    }
}


