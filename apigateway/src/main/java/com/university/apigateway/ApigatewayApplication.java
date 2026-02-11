package com.university.apigateway;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
public class ApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}

    @Bean
    public RouteLocator universityRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p
                        .path("/wbut/management/**")
                        .filters( f -> f.rewritePath("/wbut/management/(?<segment>.*)","/${segment}")

                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())

                                .circuitBreaker(config -> config.setName("administratorCircuitBreaker")
                                        .setFallbackUri("forward:/contactSupport")
                                )
//                                        .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
//                                                .setKeyResolver(userKeyResolver()))
//                                        .retry(retryConfig -> retryConfig.setRetries(4)
//                                                .setMethods(HttpMethod.GET)
//                                                .setBackoff(Duration.ofMillis(100),Duration.ofMillis(1000),2,true))

                                )

                        .uri("lb://ADMINISTRATION"))
                .route(p -> p
                        .path("/wbut/evaluation/**")
                        .filters( f -> f.rewritePath("/wbut/evaluation/(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())


                        )

                        .uri("lb://EVALUATION"))
                .build();


    }

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(5)).build()).build());
    }

//    @Bean
//    public RedisRateLimiter redisRateLimiter() {
//        return new RedisRateLimiter(1, 1, 1);
//    }

//    @Bean
//    KeyResolver userKeyResolver() {
//        return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("user"))
//                .defaultIfEmpty("anonymous");
//    }

}
