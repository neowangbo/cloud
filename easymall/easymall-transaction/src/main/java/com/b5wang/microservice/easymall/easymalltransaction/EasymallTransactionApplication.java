package com.b5wang.microservice.easymall.easymalltransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
* @SpringBootApplication implies
*   @Configuration
*   @EnableAutoConfiguration
*   @ComponentScan
*
* */
@SpringBootApplication
public class EasymallTransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasymallTransactionApplication.class, args);
    }

}
