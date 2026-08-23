package com.systemtray;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Query {

    private final RestTemplate restTemplate;

    Query(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Scheduled(fixedRate = 1000)
    public void queryStuff() {
        String url = "http://localhost:8080/api/info";
        String res = restTemplate.getForObject(url, String.class);
        System.out.println(res);
    }
}
