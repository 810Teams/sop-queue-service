package com.bank.springhibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication

@EnableDiscoveryClient
public class SpringBootAndHibernateApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAndHibernateApplication.class, args);
	}

}
