package hello.advanced.proxy.pureproxy.dacorator;

import org.junit.jupiter.api.Test;

import hello.advanced.proxy.pureproxy.dacorator.code.Component;
import hello.advanced.proxy.pureproxy.dacorator.code.DecoratorPatternClient;
import hello.advanced.proxy.pureproxy.dacorator.code.MessageDecorator;
import hello.advanced.proxy.pureproxy.dacorator.code.RealComponent;
import hello.advanced.proxy.pureproxy.dacorator.code.TimeDecorator;

public class DecoratorPatternTest {

	@Test
	void noDecorator() {
		Component realComponent = new RealComponent();
		DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
		client.execute();
	}

	@Test
	void decorator1() {
		Component realComponent = new RealComponent();
		Component messageDecorator = new MessageDecorator(realComponent);
		DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);
		client.execute();
	}

	@Test
	void decorator2() {
		Component realComponent = new RealComponent();
		Component messageDecorator = new MessageDecorator(realComponent);
		Component timeDecorator = new TimeDecorator(messageDecorator);
		DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);
		client.execute();
	}
}
