package br.com.zinid.awssqsproducer.controller

import br.com.zinid.awssqsproducer.Car
import java.util.*

data class CarDTO(
    val model: String,
    val color: String
) {

    fun toDomain(): Car {
        return Car(
            id = UUID.randomUUID().toString(),
            model = model,
            color = color
        )
    }

}