package com.example.democonsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class MyController {
    @Autowired
    @Qualifier("clientCredentialsRestTemplate")
    private OAuth2RestTemplate clientCredentialsRestTemplate;

    @GetMapping("/number")
    public int number(){
        log.info("Token: " + clientCredentialsRestTemplate.getAccessToken().getValue());
        return clientCredentialsRestTemplate.getForObject("https://demo-producer.app.derrickwong.hk/contacts",
                List.class).size();
    }

    @PostMapping("/new")
    public Contact create(@RequestBody Contact contact){
        return clientCredentialsRestTemplate.exchange("https://demo-producer.app.derrickwong.hk/contact",
                HttpMethod.POST, new HttpEntity<>(contact), Contact.class ).getBody();
    }

}
