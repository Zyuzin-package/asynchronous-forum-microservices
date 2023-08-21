package microservice.asynchronousMicroservices.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Profile("${config.kafka.environment}")
@Component
//@RequiredArgsConstructor
public class Prod {

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