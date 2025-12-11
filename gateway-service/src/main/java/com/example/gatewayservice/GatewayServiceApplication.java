package com.example.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	// Optional: Static routing if discovery locator is not enabled
	// @Bean
	// public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
	// 	return builder.routes()
	// 		.route("client_route", r -> r.path("/clients/**")
	// 			.uri("lb://client-service"))
	// 		.route("voiture_route", r -> r.path("/voitures/**")
	// 			.uri("lb://voiture-service"))
	// 		.build();
	// }
}
