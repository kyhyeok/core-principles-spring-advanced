package hello.advanced.aop.pointcut;

import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import hello.advanced.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExecutionTest {

	AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
	Method helloMEthod;

	@BeforeEach
	public void init() throws NoSuchMethodException {
		helloMEthod = MemberServiceImpl.class.getMethod("hello", String.class);
	}

	@Test
	void printMethod() {
		//public java.lang.String hello.advanced.aop.member.MemberServiceImpl.hello(java.lang.String)
		log.info("helloMethod={}", helloMEthod);
	}
}
