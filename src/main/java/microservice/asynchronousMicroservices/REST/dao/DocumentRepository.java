package microservice.asynchronousMicroservices.REST.dao;

import microservice.asynchronousMicroservices.models.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document,Long> {
}
