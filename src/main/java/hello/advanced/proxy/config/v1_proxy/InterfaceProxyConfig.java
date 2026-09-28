package hello.advanced.proxy.config.v1_proxy;

import org.springframework.boot.webmvc.autoconfigure.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import hello.advanced.proxy.app.v1.OrderControllerV1;
import hello.advanced.proxy.app.v1.OrderControllerV1Impl;
import hello.advanced.proxy.app.v1.OrderRepositoryV1;
import hello.advanced.proxy.app.v1.OrderRepositoryV1Impl;
import hello.advanced.proxy.app.v1.OrderServiceV1;
import hello.advanced.proxy.app.v1.OrderServiceV1Impl;
import hello.advanced.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.advanced.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.advanced.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.advanced.trace.logtrace.LogTrace;

@Configuration
public class InterfaceProxyConfig {

	@Bean
	public OrderControllerV1 orderController(LogTrace logTrace) {
		OrderControllerV1Impl orderControllerImpl = new OrderControllerV1Impl(orderService(logTrace));
		return new OrderControllerInterfaceProxy(orderControllerImpl, logTrace);
	}

	@Bean
	public OrderServiceV1 orderService(LogTrace logTrace) {
		OrderServiceV1Impl serviceImpl = new OrderServiceV1Impl(orderRepository(logTrace));
		return new OrderServiceInterfaceProxy(serviceImpl, logTrace);
	}

	@Bean
	public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
		OrderRepositoryV1Impl repositoryImpl = new OrderRepositoryV1Impl();
		return new OrderRepositoryInterfaceProxy(repositoryImpl, logTrace);
	}

	@Bean
	public WebMvcRegistrations webMvcRegistrations() {
		return new WebMvcRegistrations() {
			@Override
			public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
				return new RequestMappingHandlerMapping() {
					@Override
					protected boolean isHandler(Class<?> beanType) {
						return super.isHandler(beanType) || OrderControllerV1.class.isAssignableFrom(beanType);
					}
				};
			}
		};
	}
}
