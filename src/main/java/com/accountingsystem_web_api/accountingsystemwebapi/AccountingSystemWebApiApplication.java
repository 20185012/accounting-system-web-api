package com.accountingsystem_web_api.accountingsystemwebapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * SUPRANTU KAD SIEK TIEK TRUKSTA TIKRINIMU BET AS ZINOKIT LABAI STENGIAUS
 */


@Configuration
@ComponentScan(basePackages = {"com.accountingsystem_web_api.accountingsystemwebapi.Service", "com.accountingsystem_web_api.accountingsystemwebapi.Controller"})
@SpringBootApplication
public class AccountingSystemWebApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountingSystemWebApiApplication.class, args);
    }

}
