package microservice.asynchronousMicroservices.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Profile("${config.kafka.environment}")
@Component
public class Cons {

    private CountDownLatch latch = new CountDownLatch(1);
    private String payload;


    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.group-id}")
    public void flightEventConsumer(String message) {
        log.info("Consumer consume Kafka message -> {}", message);
        payload = message;
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}