package com.health2gether.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author flaoliveira
 * @version : $<br/>
 * : $
 * @since 27/10/2019 11:09
 */
@RestController
@RequestMapping("/gateway")
public class TesteController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/teste")
    public ResponseEntity<String> teste() {
        List<ServiceInstance> instances = discoveryClient.getInstances("HEALTH2GETHER-USER");
        System.out.println(instances.get(0).getUri().toString());
        return new ResponseEntity<>("Teste", HttpStatus.OK);
    }

    @RequestMapping("/users")
    @ResponseBody
    public String getUsers() {
        return "{\"users\":[{\"name\":\"Lucas\", \"country\":\"Brazil\"}," +
                "{\"name\":\"Jackie\",\"country\":\"China\"}]}";
    }

    @RequestMapping("/users-auth")
    @ResponseBody
    public String getUsersAuth() {
        return "{\"users\":[{\"name\":\"Lucas User Auth\", \"country\":\"Brazil\"}," +
                "{\"name\":\"Jackie\",\"country\":\"China\"}]}";
    }

    @PostMapping("/teste-post")
    @ResponseBody
    public String teste2() {
        return "ok";
    }

}
