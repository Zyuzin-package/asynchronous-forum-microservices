package microservice.asynchronousforummicroservices;

import microservice.asynchronousforummicroservices.consumer.Cons;
import microservice.asynchronousforummicroservices.producer.Prod;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.common.Node;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.shaded.org.apache.commons.lang3.BooleanUtils;

import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertEquals;
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
    private String  bootstrap;

    AdminClient adminClient;

    @Before
    public void configureAdminClient(){

    }

    @Test
    public void verifyConnection() throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrap);
        props.put("request.timeout.ms", 3000);
        props.put("connections.max.idle.ms", 5000);

        this.adminClient = AdminClient.create(props);

        Collection<Node> nodes = this.adminClient.describeCluster()
                .nodes()
                .get();
        boolean alive = nodes != null && nodes.size() > 0;;
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