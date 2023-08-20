package microservice.asynchronousforummicroservices;

import microservice.asynchronousforummicroservices.consumer.Cons;
import microservice.asynchronousforummicroservices.producer.Prod;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;
import static org.testcontainers.shaded.org.hamcrest.core.StringContains.containsString;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(
        partitions = 1,
        brokerProperties = { "listeners=PLAINTEXT://localhost:29092",
                "port=29092" })
public class KafkaIntegrationTest {
    @Autowired
    private Cons consumer;

    @Autowired
    private Prod producer;

    @Value("${spring.kafka.topic}")
    private String topic;


    @Test
    public void givenEmbeddedKafkaBroker_whenSendingWithSimpleProducer_thenMessageReceived()
            throws Exception {
        String data = "Sending with our own simple KafkaProducer";

        producer.sendFlightEvent(topic, data);

        boolean messageConsumed = consumer.getLatch().await(50, TimeUnit.SECONDS);
        Assertions.assertTrue(messageConsumed);
        assertThat(consumer.getPayload(), containsString(data));
    }
}
