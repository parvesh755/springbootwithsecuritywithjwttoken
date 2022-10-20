package com.sca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication(scanBasePackages = {
        "com.sca.entity",
        "com.sca.repository",
        "com.sca.controller"})
public class SpringbootwithsecuritywithjwttokenApplication {

	public static void main(String[] args) {
		System.out.println("1");
		SpringApplication.run(SpringbootwithsecuritywithjwttokenApplication.class, args);
	}

}
