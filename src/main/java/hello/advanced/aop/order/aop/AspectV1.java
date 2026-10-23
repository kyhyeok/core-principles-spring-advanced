package hello.advanced.aop.order.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class AspectV1 {

	@Around("execution (* hello.advanced.aop.order..*(..))")
	public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("[log] {}", joinPoint.getSignature()); //join point 스니이터
		return joinPoint.proceed();
	}
}
