package com.simil.client;

import java.net.URI;
import java.time.Duration;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

import com.simil.config.UserProperties;
import com.simil.dto.ContactUserDto;

@Slf4j
@Component
public class ContactUserClient {
	private final String getUserPath;
	private final String baseUrl;
	private final WebClient webClient;
	private final RetryBackoffSpec retry;

	public ContactUserClient(UserProperties userProperties) {
		this.getUserPath = userProperties.getContactUserPath();
		this.baseUrl = userProperties.baseUrl();
		this.webClient = WebClient.create();

		this.retry = Retry.backoff(userProperties.retryMaxAttempts(), Duration.ofSeconds(userProperties.retryBackoff()))
				.onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> retrySignal.failure());
	}

	public Mono<ContactUserDto> getUser(final Integer userId) {
		log.info("Trying to retrieve user from Contact Service for userId={}", userId);
		URI uri = UriComponentsBuilder.fromUriString(this.baseUrl)
				.path(this.getUserPath)
				.buildAndExpand(Map.of("id", userId))
				.toUri();
		return webClient.get()
				.uri(uri)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(ContactUserDto.class)
//				.onStatus(HttpStatus::isError, clientResponse -> clientResponse.bodyToMono(String.class)
//						.switchIfEmpty(Mono.error(new InternalServerErrorException("Failed to retrieve user from Directory for " +
//								"userId=" + userId + ", and customerId=" + customerId + ". Status Code: " + clientResponse.statusCode())))
//						.flatMap(errorResponse -> Mono.error(new InternalServerErrorException("Failed to retrieve user from Directory for " +
//								"userId=" + userId + ", and customerId=" + customerId + ". Status Code: " + clientResponse.statusCode()
//								+ ", Response: " + errorResponse))))
//				.mapNotNull(this::getFirst)
				.retryWhen(retry)
				.doOnSuccess(response -> log.info("Successfully retrieved user from Contact Service for userId={}", userId))
				.doOnError(e -> log.error("Failed to retrieve user from Contact Service for userId={}", userId, e));
	}

	public Flux<ContactUserDto> getUsers(final Pageable pageable) {
		log.info("Trying to retrieve a page of users from Contact Service for userId={}", pageable.getPageNumber());
		URI uri = UriComponentsBuilder.fromUriString(this.baseUrl)
				.path(this.getUserPath)
				.buildAndExpand(Map.of("id", pageable))
				.toUri();
		return webClient.get()
				.uri(uri)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(ContactUserDto.class);
//				.onStatus(HttpStatus::isError, clientResponse -> clientResponse.bodyToMono(String.class)
//						.switchIfEmpty(Mono.error(new InternalServerErrorException("Failed to retrieve user from Directory for " +
//								"userId=" + userId + ", and customerId=" + customerId + ". Status Code: " + clientResponse.statusCode())))
//						.flatMap(errorResponse -> Mono.error(new InternalServerErrorException("Failed to retrieve user from Directory for " +
//								"userId=" + userId + ", and customerId=" + customerId + ". Status Code: " + clientResponse.statusCode()
//								+ ", Response: " + errorResponse))))
//				.mapNotNull(this::getFirst)
//				.retryWhen(retry)
//				.doOnSuccess(response -> log.info("Successfully retrieved a page of users from Contact Service for userId={}", userId))
//				.doOnError(e -> log.error("Failed to retrieve a page of users from Contact Service for userId={}", userId, e));
	}


}
