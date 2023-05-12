package com.emreakin.controller;

import com.emreakin.model.CompanyModel;
import com.emreakin.model.request.UserDto;
import com.emreakin.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/produce")
public class ProduceController {

    private final ProducerService producerService;

    @PostMapping("/user")
    public ResponseEntity<String> produceUserToKafka(@RequestBody UserDto user) {
        schema.avro.User userAvro = schema.avro.User.newBuilder()
                .setName(user.getName())
                .setSurname(user.getSurname())
                .setAge(user.getAge())
                .setActive(user.isActive())
                .build();
        producerService.produceUser(userAvro);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/company")
    public ResponseEntity<String> produceCompanyToKafka(@RequestBody CompanyModel company) {
        producerService.produceCompany(company);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/message")
    public ResponseEntity<String> produceMessageIdToKafka(@RequestBody String messageId) {
        producerService.produceMessageId(messageId);
        return ResponseEntity.ok("OK");
    }
}
