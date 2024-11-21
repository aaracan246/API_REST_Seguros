package com.example.unsecuredseguros.entities

import jakarta.persistence.*
import java.sql.Date


@Entity
@Table(name = "seguros")
data class Seguro(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seguro")
    val idSeguro: Long,

    @Column
    val nif: String,

    @Column
    val nombre: String,

    @Column
    val ape1: String,

    @Column
    val ape2: String?,

    @Column
    val edad: Int,

    @Column(name = "num_hijos")
    val numHijos: Int,

    @Column(name = "fecha_creacion")
    val fechaCreacion: Date,

    @Column
    val sexo: String,

    @Column
    val casado: Boolean,

    @Column
    val embarazada: Boolean
)
