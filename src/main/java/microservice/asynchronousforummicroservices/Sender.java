package microservice.asynchronousforummicroservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Sender {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Scheduled(fixedRate = 1500)
    public void sendMessage() {
        System.out.println("\n SEND MESSAGE");
        kafkaTemplate.send("test", String.valueOf(new Random().nextInt()));
    }

}
