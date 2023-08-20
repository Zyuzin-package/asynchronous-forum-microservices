package microservice.asynchronousforummicroservices.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {

    @KafkaListener(topics = "korka", groupId = "krokGroup")
    public void flightEventConsumer(String message) {
        log.info("Consumer consume Kafka message -> {}", message);
    }

}