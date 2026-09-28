package hello.advanced.app.proxy.config;

import org.springframework.boot.webmvc.autoconfigure.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import hello.advanced.app.proxy.v1.OrderControllerV1;
import hello.advanced.app.proxy.v2.OrderControllerV2;
import hello.advanced.app.proxy.v2.OrderRepositoryV2;
import hello.advanced.app.proxy.v2.OrderServiceV2;

@Configuration
public class AppV2Config {

	@Bean
	public OrderControllerV2 orderControllerV2() {
		return new OrderControllerV2(orderServiceV2());
	}

	@Bean
	public OrderServiceV2 orderServiceV2() {
		return new OrderServiceV2(orderRepositoryV2());
	}

	@Bean
	public OrderRepositoryV2 orderRepositoryV2() {
		return new OrderRepositoryV2();
	}

	@Bean
	public WebMvcRegistrations webMvcRegistrationsV2() {
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
