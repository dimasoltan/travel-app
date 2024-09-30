package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan
@EnableJpaRepositories
@EnableDiscoveryClient
public class CabFareMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CabFareMicroServiceApplication.class, args);
    }

}
