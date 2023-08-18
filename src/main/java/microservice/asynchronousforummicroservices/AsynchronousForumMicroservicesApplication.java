package microservice.asynchronousforummicroservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableScheduling
@EnableKafka
public class AsynchronousForumMicroservicesApplication {


    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(AsynchronousForumMicroservicesApplication.class, args);
    }

}
