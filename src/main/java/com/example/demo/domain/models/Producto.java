package com.example.demo.domain.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Producto {
    private Long id;
    private String nombre;
    private Double precio;
    private Categoria categoria;
}
