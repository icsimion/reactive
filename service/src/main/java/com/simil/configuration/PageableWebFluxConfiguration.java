package com.simil.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

@Configuration
/**
 * https://stackoverflow.com/questions/52355490/no-primary-or-default-constructor-found-for-interface-org-springframework-data-d
 */
public class PageableWebFluxConfiguration implements WebFluxConfigurer {

	@Override
	public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
		configurer.addCustomResolver(new ReactivePageableHandlerMethodArgumentResolver());
	}

}
