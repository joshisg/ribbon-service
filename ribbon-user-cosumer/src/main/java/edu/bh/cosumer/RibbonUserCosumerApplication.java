package edu.bh.cosumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RibbonClient(name = "user-service-ribbon")
public class RibbonUserCosumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonUserCosumerApplication.class, args);
	}

}
