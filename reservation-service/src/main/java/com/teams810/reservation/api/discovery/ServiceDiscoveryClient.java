package com.teams810.reservation.api.discovery;

import com.teams810.reservation.api.entities.service.Shop;
import com.teams810.reservation.api.entities.service.ShopItems;
import com.teams810.reservation.api.entities.service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ServiceDiscoveryClient {
    @Autowired
    private DiscoveryClient discoveryClient;

    public User verifyUser(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        RestTemplate rest = new RestTemplate();
        ResponseEntity<User> response = rest.exchange("https://auth-service-258809.appspot.com/verify", HttpMethod.GET, entity, User.class);

        return response.getBody();
    }

    public Shop getShop(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        RestTemplate rest = new RestTemplate();
        ResponseEntity<Shop> response = rest.exchange("https://shop-service-258809.appspot.com/shops/me", HttpMethod.GET, entity, Shop.class);

        return response.getBody();
    }

    public ShopItems getShopItems(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        RestTemplate rest = new RestTemplate();
        ResponseEntity<ShopItems> response = rest.exchange("https://product-service-258809.appspot.com/products/me", HttpMethod.GET, entity, ShopItems.class);

        return response.getBody();
    }

//    public Student getStudent(String studentId) {
//        RestTemplate restTemplate = new RestTemplate();
//        List<ServiceInstance> instances = discoveryClient.getInstances("studentservice");
//        String serviceUri = String.format("%s/v1/student/%s", instances.get(0).getUri().toString(), studentId);
//        ResponseEntity<Student> restExchange = restTemplate.exchange(serviceUri, HttpMethod.GET, null, Student.class, studentId);
//
//        return restExchange.getBody();
//    }
}