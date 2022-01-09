package br.com.zinid.awssqsproducer.awsconfig

data class SQSMessageProperties(
    val queueUrl: String,
    val delaySeconds: Int
)