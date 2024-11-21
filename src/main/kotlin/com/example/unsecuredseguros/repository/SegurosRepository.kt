package com.example.unsecuredseguros.repository

import com.example.unsecuredseguros.entities.Seguro
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SegurosRepository: JpaRepository<Seguro, Long>