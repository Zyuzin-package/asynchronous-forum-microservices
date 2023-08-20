package microservice.asynchronousforummicroservices.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@RequiredArgsConstructor
public class Prod {

//    @Value("${spring.kafka.topic}")
//    private String topic;

    private static  KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public Prod(KafkaTemplate<String, String> kafkaTemplate) {
        Prod.kafkaTemplate = kafkaTemplate;
    }

    public void sendFlightEvent(String topic, String event) {
        kafkaTemplate.send(topic, event);
        log.info("Producer produced the message {}", event);
    }


}