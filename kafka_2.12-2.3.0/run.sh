#gnome-terminal -- bin/zookeeper-server-start.sh /home/martin/repos/sistemas-distribuidos/kafka_2.12-2.3.0/config/zookeper.properties
gnome-terminal -- bin/kafka-server-start.sh config/server-1.properties
gnome-terminal -- bin/kafka-server-start.sh config/server-2.properties
#sleep 5
#gnome-terminal -- bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning
#gnome-terminal -- bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 3 --partitions 1 --topic my-replicated-topic
#gnome-terminal -- bin/kafka-topics.sh --describe --bootstrap-server localhost:9092 --topic my-replicated-topic
