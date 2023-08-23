package microservice.asynchronousMicroservices.models.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import microservice.asynchronousMicroservices.models.api.Model;

@Entity
@Table(name = "documents"
        ,schema = "public"
)
public class Document implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "document_name")
    private String documentName;
    @Column(name = "path_to_document")
    private String pathToDocument;
    @Column(name = "processing_status")
    private boolean processingStatus;
    @Column(name = "added_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime addedTime;

    public Document() {
    }

    public Document(Long id, String documentName, String pathToDocument, boolean processingStatus, LocalDateTime addedTime) {
        this.id = id;
        this.documentName = documentName;
        this.pathToDocument = pathToDocument;
        this.processingStatus = processingStatus;
        this.addedTime = addedTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getPathToDocument() {
        return pathToDocument;
    }

    public void setPathToDocument(String pathToDocument) {
        this.pathToDocument = pathToDocument;
    }

    public boolean isProcessingStatus() {
        return processingStatus;
    }

    public void setProcessingStatus(boolean processingStatus) {
        this.processingStatus = processingStatus;
    }

    public LocalDateTime getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(LocalDateTime addedTime) {
        this.addedTime = addedTime;
    }
}
