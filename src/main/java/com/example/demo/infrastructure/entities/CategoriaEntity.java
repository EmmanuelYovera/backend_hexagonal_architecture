package com.example.demo.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name= "categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<ProductoEntity> productos;
}
