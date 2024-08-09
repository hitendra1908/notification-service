package com.api.freelancer;

import com.api.freelancer.entity.NotificationEntity;
import com.api.freelancer.model.Notification;
import com.api.freelancer.service.NotificationService;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop"})
public class KafkaConsumerIntegrationTest {

	@Container
	@ServiceConnection
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
			"postgres:15-alpine"
	);

	@Container
	public static KafkaContainer kafkaContainer = new KafkaContainer(
			DockerImageName.parse("confluentinc/cp-kafka:latest")
	);



	@Autowired
	private NotificationService notificationService;

	@DynamicPropertySource
	static void registerKafkaProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.kafka.bootstrap-servers", () -> kafkaContainer.getBootstrapServers());
	}

	@Bean
	public ProducerFactory<String, Notification> producerFactory() {
    Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaContainer.getBootstrapServers());
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(configProps);
	}

	private final KafkaTemplate<String, Notification> kafkaTemplate = new KafkaTemplate<>(producerFactory());

	@BeforeEach
	void setup(){
		kafkaContainer.start();
	}


	@Test
    void testKafkaListener() throws Exception {
		Notification notification = Notification.builder()
						.receiver("testUser")
						.documentName("testDoc")
						.timestamp(LocalDateTime.now())
						.build();
		NotificationEntity notificationEntity = NotificationEntity.builder()
				.id(1L)
				.receiver("testUser")
				.documentName("testDoc")
				.timestamp(LocalDateTime.now())
				.build();

		var resultList = List.of(notificationEntity);

		kafkaTemplate.send("freelancer_notification", notification);
		Thread.sleep(2000);

		var receivedNotification = notificationService.getNotificationByUser("testUser");
        assertFalse(receivedNotification.isEmpty());
		assertEquals(resultList.size(), receivedNotification.size());
		assertEquals(resultList.get(0).getId(), receivedNotification.get(0).getId());
		assertEquals(resultList.get(0).getReceiver(), receivedNotification.get(0).getReceiver());
	}
}
