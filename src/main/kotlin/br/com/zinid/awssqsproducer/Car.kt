package br.com.zinid.awssqsproducer

import br.com.zinid.awssqsproducer.controller.CarDTO

data class Car(
    val id: String,
    val model: String,
    val color: String
) {

    fun toDTO(): CarDTO {
        return CarDTO(
            model = model,
            color = color
        )
    }

}