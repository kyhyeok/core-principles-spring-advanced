package hello.advanced.proxy.pureproxy.dacorator;

import org.junit.jupiter.api.Test;

import hello.advanced.proxy.pureproxy.dacorator.code.Component;
import hello.advanced.proxy.pureproxy.dacorator.code.DecoratorPatternClient;
import hello.advanced.proxy.pureproxy.dacorator.code.RealComponent;

public class DecoratorPatternTest {

	@Test
	void noDecorator() {
		Component realComponent = new RealComponent();
		DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
		client.execute();
	}
}
