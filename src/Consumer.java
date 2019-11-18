import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;



public class Consumer {

	KafkaConsumer<String, String> consumer;
	//The class KafkaConsumer creates a consumer for a group of consumer threads.
	Consumer(){
		Properties props = new Properties();
		//The property BOOTSTRAP_SERVERS_CONFIG is the same as the producer properties.
		props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "Group1");
		props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
		StringDeserializer.class.getName());
		props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
		StringDeserializer.class.getName());

		consumer = new KafkaConsumer<>(props);
	}
	public void subscribe(List<String> topics){
		consumer.subscribe(topics);
	}
	
	public void consumeForEver() {
		while (true) {
			//System.out.println("Start polling");
			ConsumerRecords<String, String> records = consumer.poll(100);
//			System.out.println("Exit polling");
			for (ConsumerRecord<String, String> record : records) { 
				System.out.println("something consumed");
				System.out.printf("offset = %d, key = %s, value = %s%n",
				record.offset(), record.key(), record.value());
			}//for
		} //while
	}
	
	public static void main(String[] args) {
		List<String> topics = Arrays.asList("userId", "hola");
		Consumer myconsumer = new Consumer();
		
		myconsumer.subscribe(topics);
		myconsumer.consumeForEver();
	}	
}
