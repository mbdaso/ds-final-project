package com.distributedsystems.transactiondetector;
import com.distributedsystems.transactiondetector.TransactionsJ;
import java.util.HashMap;
import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

//import blabla;

/*"""Produce fake transactions into a Kafka topic."""

import os
from time import sleep
import json

from kafka import KafkaProducer
from transactions import create_random_transaction

TRANSACTIONS_TOPIC = os.environ.get('TRANSACTIONS_TOPIC')
KAFKA_BROKER_URL = os.environ.get('KAFKA_BROKER_URL')
TRANSACTIONS_PER_SECOND = float(os.environ.get('TRANSACTIONS_PER_SECOND'))
SLEEP_TIME = 1 / TRANSACTIONS_PER_SECOND


if __name__ == '__main__':
    producer = KafkaProducer(
        bootstrap_servers=KAFKA_BROKER_URL,
        # Encode all values as JSON
        value_serializer=lambda value: json.dumps(value).encode(),
    )
    while True:
        transaction: dict = create_random_transaction()
        producer.send(TRANSACTIONS_TOPIC, value=transaction)
        #print(transaction)  # DEBUG
        sleep(SLEEP_TIME)*/

public class TransactionProducer {
	Properties props;
	// The producer is a Kafka client that publishes records to the Kafka
	// cluster.
	KafkaProducer<String, String> producer;
	TransactionProducer() {
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
			producer.send(new ProducerRecord<String, String>("topic", Integer
					.toString(i), Integer.toString(i)));
	}
	
	void send(ProducerRecord<String, String> record) {
		producer.send(record);		
		System.out.println(record.toString() + " sent!");
	}
	
	void stop() {
		producer.close();
	}
	
//	public static void main(String[] args) {
//		final String TRANSACTIONS_TOPIC = "queueing.transactions";
//		final float TRANSACTIONS_PER_SECOND = 1000;
//		final float SLEEP_TIME = (1/TRANSACTIONS_PER_SECOND*1000);
//		TransactionProducer myProducer = new TransactionProducer();
//		
//		while(true) {
//			TransactionsJ transactionsJ = new TransactionsJ();
//			HashMap<String, String> transaction = transactionsJ.transactions();
//			//Encode dictionary as string
//			myProducer.send(
//					new ProducerRecord<String, String>(TRANSACTIONS_TOPIC, 
//							null, transaction.toString()));
//			System.out.println("Transaction" + transaction.toString());
//			try {
//				Thread.sleep(1000);
//			}
//			catch(Exception e) {
//				
//			}
//		}
//		//myProducer.stop();
//		//System.out.println("End");
//	}
}
