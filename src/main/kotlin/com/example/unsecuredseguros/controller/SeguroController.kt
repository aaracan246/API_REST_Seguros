package com.example.unsecuredseguros.controller

import com.example.unsecuredseguros.entities.Seguro
import com.example.unsecuredseguros.exceptions.ValidationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.example.unsecuredseguros.services.SeguroService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@RestController
@RequestMapping("/seguros")
class SeguroController {

    @Autowired
    private lateinit var seguroService: SeguroService

    @GetMapping("/{id_seguro}")
    fun getSeguroById(
        @PathVariable
        id: String): ResponseEntity<Seguro>? {

        if(id.isBlank()){
            // Lanzamos la excepción:
            throw ValidationException("El id no puede estar vacío.")
        }
        val s: Seguro? = seguroService.getById(id)

        return ResponseEntity(s!!, HttpStatus.OK)
    }

    @GetMapping("/seguros")
    fun getAllSeguros(){

    }

    @GetMapping("/seguros")
    fun postSeguro(){}

    @GetMapping("/seguros/{id_seguro}")
    fun putSeguro(){}

    @GetMapping("/seguros/{id_seguro}")
    fun deleteSeguro(){}
}






//Operaciones CRUD
//GET /seguros:
//Devuelve una lista de todos los seguros registrados.
//
//GET /seguros/{id}:
//Devuelve un seguro por su identificador idSeguro. Si no existe, retorna un error 404.
//
//POST /seguros:
//Crea un nuevo seguro.
//
//Valida los campos siguiendo las restricciones mencionadas.
//PUT /seguros/{id}:
//Actualiza un seguro existente identificado por idSeguro.
//
//Si no existe, retorna un error 404.
//Aplica las validaciones antes de guardar los cambios.
//DELETE /seguros/{id}:
//Elimina un seguro identificado por idSeguro. Si no existe, retorna un error 404.