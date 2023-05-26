package sleuth.testing;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.Assert;

@SpringBootApplication
public class MainApp implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(MainApp.class, args);
  }

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Override
  public void run(String... args) throws Exception {
    SendResult<String, String> sendResult = kafkaTemplate.send("sleuth.test", "test-value").get();
    Assert.notNull(
        sendResult.getProducerRecord()
            .headers()
            .lastHeader("b3")
    );
  }

}
