package microservice.asynchronousMicroservices.REST.service.impl;

import microservice.asynchronousMicroservices.models.domain.Document;
import org.springframework.stereotype.Service;

@Service
public interface documentService {
    Document save(Document document);
}
