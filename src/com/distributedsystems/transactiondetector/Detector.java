package com.distributedsystems.transactiondetector;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class Detector {
	KafkaConsumer<String, String> consumer;
	KafkaProducer<String, String> producer;
	
	Detector(){
		setupConsumer();
		setupProducer();
	}
	
	void setupProducer() {
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
	
	void setupConsumer(){
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
	
	ConsumerRecords<String, String> consume() {
		return consumer.poll(100);
	}
	
	void subscribe(Collection<String> topics) {
		consumer.subscribe(topics);
	}
	
	public static void main(String[] args){
		System.out.println("Consumer");
		final String TRANSACTIONS_TOPIC = "queueing.transactions";
		final String LEGIT_TOPIC = "streaming.transactions.legit";
		final String FRAUD_TOPIC = "streaming.transactions.fraud";
		
		List<String> topics = Arrays.asList(TRANSACTIONS_TOPIC);
		
		Detector detector = new Detector();
		detector.subscribe(topics);
		
		while(true) {
			ConsumerRecords<String, String> records = detector.consume();
			//record = detector.consume();
			for (ConsumerRecord<String, String> record : records) { 
				System.out.println("something consumed");
				System.out.printf("offset = %d, key = %s, value = %s%n",
				record.offset(), record.key(), record.value());
			}//for
		}				
	}
}
