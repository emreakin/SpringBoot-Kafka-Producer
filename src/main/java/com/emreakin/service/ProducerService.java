package com.emreakin.service;

import com.emreakin.model.CompanyModel;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProducerService {

    @Value("${kafka.topics.user.name}")
    private String userTopicName;

    @Value("${kafka.topics.company.name}")
    private String companyTopicName;

    @Value("${kafka.topics.message.name}")
    private String messageIdTopicName;

    private final KafkaTemplate<String, schema.avro.User> userKafkaTemplate;
    private final KafkaTemplate<String, CompanyModel> companyKafkaTemplate;
    private final KafkaTemplate<String, String> messageIdKafkaTemplate;

    public void produceUser(schema.avro.User user) {
        ProducerRecord<String, schema.avro.User> producerRecord = new ProducerRecord<>(userTopicName, user);
        userKafkaTemplate.send(producerRecord);
    }

    public void produceCompany(CompanyModel company) {
        ProducerRecord<String, CompanyModel> producerRecord = new ProducerRecord<>(companyTopicName, company);
        companyKafkaTemplate.send(producerRecord);
    }

    public void produceMessageId(String messageId) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(messageIdTopicName, messageId);
        messageIdKafkaTemplate.send(producerRecord);
    }
}
