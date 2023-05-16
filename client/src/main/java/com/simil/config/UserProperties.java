package com.simil.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "clients")
public record UserProperties(
		String baseUrl,
		String getContactUserPath,
		String getDirectoryUserPath,
		String getLocationUserPath,

		Integer retryMaxAttempts,
		Integer retryBackoff
) {
}
