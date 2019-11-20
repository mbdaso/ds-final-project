# ds-final-project


1. Run the kafka cluster

```
docker-compose up
```

2. Run the generator, which will produce a stream of fake transactions with the topic "queueing.transactions"

```
java -jar Generator.jar
```

3. Run the detector, which will parse the transactions and generate "streaming.transactions.legit" and "streaming.transactions.fraud" topics.

```
java -jar Detector.jar
```

4. Subscribe to the topics using the kafka scripts in kafka_2.12-2.3.0/

```
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic "queueing.transactions.legit"
```

```
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic "queueing.transactions.fraud"
```
