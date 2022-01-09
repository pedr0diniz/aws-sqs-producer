package br.com.zinid.awssqsproducer.controller

import br.com.zinid.awssqsproducer.awsconfig.SQSMessageProperties
import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.model.SendMessageRequest
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cars")
class CarController(
    val sqsProperties: SQSMessageProperties,
    val sqs: AmazonSQS
) {

    val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping
    fun createCar(@RequestBody carRequest: CarDTO): ResponseEntity<Any> {

        val message = carRequest.toDomain().toString()

        val sendMessageRequest = SendMessageRequest()
            .withQueueUrl(sqsProperties.queueUrl)
            .withMessageBody(message)
            .withDelaySeconds(sqsProperties.delaySeconds)

        sqs.sendMessage(sendMessageRequest).also { logger.info("Message sent: $message") }

        return ResponseEntity.status(HttpStatus.CREATED).body(message)
    }

}