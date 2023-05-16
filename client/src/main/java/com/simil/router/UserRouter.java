package com.simil.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration(proxyBeanMethods = false)
public class UserRouter {

	@Bean
	public RouterFunction<ServerResponse> route(final HealthCheckHandler healthCheckHandler, final UserHandler userHandler) {


		// TODO add filter

		// TODO add tests
		// https://spring.io/guides/gs/reactive-rest-service/

		return RouterFunctions.route(GET("/healthcheck").and(accept(MediaType.APPLICATION_JSON)), healthCheckHandler)
				.andNest(GET("/router/api/users"),
						RouterFunctions.route(GET("/{userId}").and(accept(MediaType.APPLICATION_JSON)), userHandler::findUserById)
								.andRoute(GET("/page").and(accept(MediaType.APPLICATION_JSON)), userHandler::findUsersPageable)
								.andRoute(GET("/ids/{ids}").and(accept(MediaType.APPLICATION_JSON)), userHandler::findUsersByIds)
								.andNest(GET("{userId}"), RouterFunctions.route(GET("/contact").and(accept(MediaType.APPLICATION_JSON)),
												userHandler::findContactById)
										.andRoute(GET("/directory").and(accept(MediaType.APPLICATION_JSON)), userHandler::findDirectoryById)
										.andRoute(GET("/location").and(accept(MediaType.APPLICATION_JSON)), userHandler::findLocationById)));
	}


}
