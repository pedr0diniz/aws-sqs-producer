package br.com.zinid.awssqsproducer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AwsSqsProducerApplication

fun main(args: Array<String>) {
	runApplication<AwsSqsProducerApplication>(*args)
}
