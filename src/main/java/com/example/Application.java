package com.example;

import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@MapperScan({"com.example.mapper", "com.example.zsk.mapper", "com.example.bpm.mapper"})
public class Application {
    public static void main(String[] args) {
        //创建配置对象
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        KafkaProducer<String,String> producer = new KafkaProducer<>(configMap);
//        ProducerRecord<String,String> record = new ProducerRecord<>("test","key","value");
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String,String> record = new ProducerRecord<>("test","key"+i,"value"+i);
            producer.send(record);
        }


        producer.close();
    }
}
