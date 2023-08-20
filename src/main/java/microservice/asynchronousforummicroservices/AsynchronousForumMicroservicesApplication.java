package microservice.asynchronousforummicroservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableKafka
public class AsynchronousForumMicroservicesApplication {


    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(AsynchronousForumMicroservicesApplication.class, args);
    }

}
