package com.teams810.reservation.api.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ServiceDiscoveryClient {
    @Autowired
    private DiscoveryClient discoveryClient;

    // TODO: IMPLEMENTS SERVICE DISCOVERY
//    public Student getStudent(String studentId) {
//        RestTemplate restTemplate = new RestTemplate();
//        List<ServiceInstance> instances = discoveryClient.getInstances("studentservice");
//        String serviceUri = String.format("%s/v1/student/%s", instances.get(0).getUri().toString(), studentId);
//        ResponseEntity<Student> restExchange = restTemplate.exchange(serviceUri, HttpMethod.GET, null, Student.class, studentId);
//
//        return restExchange.getBody();
//    }
}
