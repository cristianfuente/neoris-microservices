package com.neoris.movements;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
@SpringBootApplication
public class MovementsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovementsApplication.class, args);
    }

}
