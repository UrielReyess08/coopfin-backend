package com.coopfin.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rol", schema = "coopfin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    @Column(name = "nombre", nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(name = "descripcion", length = 150)
    private String descripcion;
}
