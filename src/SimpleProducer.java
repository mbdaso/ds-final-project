import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;


public class SimpleProducer {
	Properties props;
	// The producer is a Kafka client that publishes records to the Kafka
	// cluster.
	KafkaProducer<String, String> producer;
	SimpleProducer() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		// Serializer for conversion the key type to bytes
		props.put("key.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");
		// Serializer for conversion the value type to bytes
		props.put("value.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");
		producer = new KafkaProducer<>(props);
	}
	void produceAndPrint() {
		for (int i = 1; i < 100; i++)
			// Fire-and-forget send(topic, key, value)
			// Send adds records to unsent records buffer and return
			producer.send(new ProducerRecord<String, String>("hola", Integer
					.toString(i), Integer.toString(i)));
	}
	void stop() {
		producer.close();
	}
	public static void main(String[] args) {
		SimpleProducer myProducer = new SimpleProducer();
		myProducer.produceAndPrint();
		myProducer.stop();
		System.out.println("End");
	}
}