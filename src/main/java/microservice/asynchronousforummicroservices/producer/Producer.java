package microservice.asynchronousforummicroservices.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@RequiredArgsConstructor
public class Producer {

    @Value("${spring.kafka.topic}")
    public static String TOPIC;

    private static  KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate<String, String> kafkaTemplate) {
        Producer.kafkaTemplate = kafkaTemplate;
    }

    public static void sendFlightEvent(String event) {
        kafkaTemplate.send(TOPIC, event);
        log.info("Producer produced the message {}", event);
    }


}