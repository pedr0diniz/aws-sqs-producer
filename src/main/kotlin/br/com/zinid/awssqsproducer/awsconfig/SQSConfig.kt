package br.com.zinid.awssqsproducer.awsconfig

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.AmazonSQSClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SQSConfig {

    @Value("\${aws.sqs.access.key}")
    val accessKey: String? = null

    @Value("\${aws.sqs.secret.key}")
    val secretKey: String? = null

    @Value("\${aws.sqs.queue.url}")
    val queueUrl: String? = null

    @Value("\${aws.sqs.delay.seconds}")
    val delaySeconds: Int? = null

    @Bean("credentials")
    fun credentials(): BasicAWSCredentials {
        return BasicAWSCredentials(accessKey, secretKey)
    }

    @Bean("sqsProperties")
    fun sqsProperties(): SQSMessageProperties {
        return SQSMessageProperties(
            queueUrl = queueUrl!!,
            delaySeconds = delaySeconds!!
        )
    }

    @Bean("amazonSqs")
    fun amazonSqs(): AmazonSQS {
        return AmazonSQSClientBuilder.standard()
            .withCredentials(AWSStaticCredentialsProvider(credentials()))
            .withRegion(Regions.SA_EAST_1)
            .build()
    }

}