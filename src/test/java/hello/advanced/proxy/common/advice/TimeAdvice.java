package hello.advanced.proxy.common.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jspecify.annotations.Nullable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeAdvice implements MethodInterceptor {
	@Override
	public @Nullable Object invoke(MethodInvocation invocation) throws Throwable {
		log.info("TimeAdvice 실행");
		long startTime = System.currentTimeMillis();

		// Object result = method.invoke(target, args);
		Object result = invocation.proceed();

		long endTIme = System.currentTimeMillis();
		long resultTime = endTIme - startTime;
		log.info("TimeAdvice 종료 resultTime={}", resultTime);

		return result;
	}
}
