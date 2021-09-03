package com.cross.solutions.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * @author huynhlehoaibac
 * @since 0.0.1-SNAPSHOT
 */
@EnableEurekaClient
@SpringBootApplication
public class GatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

  @Bean
  public RouteLocator routeLocator(
      RouteLocatorBuilder builder, TokenRelayGatewayFilterFactory filterFactory) {
    return builder
        .routes()
        .route(
            "cart",
            r -> r.path("/cart/**").filters(f -> f.filter(filterFactory.apply())).uri("lb://cart"))
        .build();
  }
}
