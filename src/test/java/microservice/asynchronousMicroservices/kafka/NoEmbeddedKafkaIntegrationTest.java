package microservice.asynchronousMicroservices.kafka;

import microservice.asynchronousMicroservices.kafka.consumer.Cons;
import microservice.asynchronousMicroservices.kafka.producer.Prod;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.common.Node;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;
import static org.testcontainers.shaded.org.hamcrest.core.StringContains.containsString;

@SpringBootTest
@DirtiesContext
public class NoEmbeddedKafkaIntegrationTest {
    @Autowired
    private Cons consumer;

    @Autowired
    private Prod producer;

    @Value("${spring.kafka.topic}")
    private String topic;
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap;

    AdminClient adminClient;

    @Before
    public void configureAdminClient() {

    }

    @Test
    public void verifyConnection() throws ExecutionException, InterruptedException {
            Properties props = new Properties();
            props.put("bootstrap.servers", bootstrap);

            this.adminClient = AdminClient.create(props);

            Collection<Node> nodes = this.adminClient.describeCluster()
                    .nodes()
                    .get();
            boolean alive = nodes != null && nodes.size() > 0;

            Assertions.assertTrue(alive);
    }


    @Test
    public void givenNoKafkaBroker_whenSendingWithSimpleProducer_thenMessageReceived()
            throws Exception {
        String data = "Sending with our own simple KafkaProducer";

        producer.sendFlightEvent(topic, data);

        boolean messageConsumed = consumer.getLatch().await(50, TimeUnit.SECONDS);
        Assertions.assertTrue(messageConsumed);
        assertThat(consumer.getPayload(), containsString(data));
    }
}