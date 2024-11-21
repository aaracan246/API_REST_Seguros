package com.example.unsecuredseguros.services

import com.example.unsecuredseguros.entities.Seguro
import com.example.unsecuredseguros.exceptions.APIExceptionHandler
import com.example.unsecuredseguros.exceptions.NotFoundException
import com.example.unsecuredseguros.exceptions.ValidationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import com.example.unsecuredseguros.repository.SegurosRepository
import org.apache.coyote.BadRequestException
import kotlin.reflect.jvm.internal.ReflectProperties.Val

@Service
class SeguroService {

    @Autowired
    private lateinit var segurosRepository: SegurosRepository


    fun getById(
        @PathVariable
        id: String?): Seguro? {

        val seguro = null // No se encuentra

        if (seguro == null){ throw NotFoundException("No se encontró el seguro.")}

        return null
      }

    fun validateNIF(id: Long): Boolean{

        val seguroNIF = segurosRepository.findById(id)

        val letras = ('A'..'Z')

        val seguro = seguroNIF.get().nif

        if(seguro.length != 9){
            throw ValidationException("El DNI debe tener 9 dígitos.")
        }

        for (letra in letras){
            if (seguro.contains(letra) && seguro.last() == letra){
                return true
            }
        }

        throw ValidationException("El DNI debe tener una letra y debe estar en la última posición.")
    }


    fun validateSeguro(id: Long): Boolean{

        val seguro = segurosRepository.findById(id)
        val seguroID = seguro.get().idSeguro

        when {
            !validateNIF(seguroID) -> throw ValidationException("Ha ocurrido un error validando el DNI del usuario.")

            seguro.get().nombre.isEmpty() -> throw ValidationException("El campo nombre no puede estar vacío.")

            seguro.get().ape1.isEmpty() -> throw ValidationException("El campo apellido no puede estar vacío.")

            seguro.get().edad <= 0 || seguro.get().edad < 18 -> throw ValidationException("Error validando la edad. No puede ser menor de edad.")

            seguro.get().sexo.isEmpty() -> throw ValidationException("El campo sexo no puede estar vacío.")

            seguro.get().numHijos < 0 -> throw ValidationException("El campo número de hijos no puede estar vacío.")

            !seguro.get().casado && seguro.get().numHijos != 0 -> throw ValidationException("Por orden del Reino de España, bajo real decreto 5/2047: toda persona no casada no tendrá acceso a sus capacidades fértiles. El incumplimiento de este decreto conllevará ejecución inmediata.")

            seguro.get().embarazada && seguro.get().sexo != "Mujer" -> throw ValidationException("Por orden del Reino de España, bajo real decreto 2/2043: debido a los progresos tecnológicos la sanidad pública del Reino de España permite tanto a hombres como a mujeres engendrar descendencia. Aún así, políticamente bajo el reinado de nuestro amo y señor, su Majestad Lordramus III, quedará prohibido el ejercicio de tal acto si el género se considera: Hombre. El incumplimiento de este decreto conllevará ejecución inmediata.")

            else -> return true
        }
    }
}
