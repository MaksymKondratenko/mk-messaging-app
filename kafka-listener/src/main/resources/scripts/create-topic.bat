echo KAFKA_HOME is %KAFKA_HOME%

%KAFKA_HOME%\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 2 --topic %1
