package microservice.asynchronousMicroservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableKafka
@EnableJpaRepositories
@EntityScan(basePackages = {"microservice.asynchronousMicroservices.models.domain"})
@Profile("dev")
public class AsynchronousForumMicroservicesApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(AsynchronousForumMicroservicesApplication.class, args);
//        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .configure().build();
//        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        DocumentDomain documentDomain = new DocumentDomain();
//        documentDomain.setDocumentName("korka");
//        session.merge(documentDomain);
//        transaction.commit();
//        session.close();

    }

}
