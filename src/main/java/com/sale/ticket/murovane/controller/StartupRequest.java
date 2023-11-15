//package com.sale.ticket.murovane.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//import javax.annotation.PostConstruct;
//
//@Component
//public class StartupRequest {
//    private final RestTemplate restTemplate;
//
//    @Autowired
//    public StartupRequest(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    @PostConstruct
//    public void onStartup() {
//        // Выполните запрос при старте приложения
//        ResponseEntity<String> response = restTemplate.exchange(
//                "http://localhost:8080/api/settlements",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<String>() {});
//
//        String settlements = response.getBody();
//        // Обработайте полученные поселения
//        System.out.println("Received settlements: " + settlements);
//    }
//}
