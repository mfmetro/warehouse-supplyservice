package de.metro.code.warehouse.supplyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan
@EnableScheduling
public class SupplyserviceApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SupplyserviceApplication.class, args);
    }
}
