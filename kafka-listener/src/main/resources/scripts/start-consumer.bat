echo KAFKA_HOME is %KAFKA_HOME%

%KAFKA_HOME%\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic %1 --from-beginning
