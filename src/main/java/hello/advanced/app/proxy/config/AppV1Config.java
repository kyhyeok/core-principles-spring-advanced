package hello.advanced.app.proxy.config;

import org.springframework.boot.webmvc.autoconfigure.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import hello.advanced.app.proxy.v1.OrderControllerV1;
import hello.advanced.app.proxy.v1.OrderControllerV1Impl;
import hello.advanced.app.proxy.v1.OrderRepositoryV1;
import hello.advanced.app.proxy.v1.OrderRepositoryV1Impl;
import hello.advanced.app.proxy.v1.OrderServiceV1;
import hello.advanced.app.proxy.v1.OrderServiceV1Impl;
import hello.advanced.app.proxy.v2.OrderControllerV2;

@Configuration
public class AppV1Config {

	@Bean
	public OrderControllerV1 orderControllerV1() {
		return new OrderControllerV1Impl(orderServiceV1());
	}

	@Bean
	public OrderServiceV1 orderServiceV1() {
		return new OrderServiceV1Impl(orderRepositoryV1());
	}

	@Bean
	public OrderRepositoryV1 orderRepositoryV1() {
		return new OrderRepositoryV1Impl();
	}

	@Bean
	@Primary
	public WebMvcRegistrations webMvcRegistrationsV1() {
		return new WebMvcRegistrations() {
			@Override
			public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
				return new RequestMappingHandlerMapping() {
					@Override
					protected boolean isHandler(Class<?> beanType) {
						return super.isHandler(beanType) || OrderControllerV1.class.isAssignableFrom(beanType)
							|| OrderControllerV2.class.isAssignableFrom(beanType);
					}
				};
			}
		};
	}
}
