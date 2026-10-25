package hello.advanced.aop.pointcut;

import static org.assertj.core.api.Assertions.*;

import java.lang.reflect.Method;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import hello.advanced.aop.member.MemberServiceImpl;

public class WithinTest {

	AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
	Method helloMethod;

	@BeforeEach
	public void init() throws NoSuchMethodException {
		helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
	}

	@Test
	void withinExact() {
		pointcut.setExpression("within(hello.advanced.aop.member.MemberServiceImpl)");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	void withinStar() {
		pointcut.setExpression("within(hello.advanced.aop.member.*Service*)");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	void withinSubPackage() {
		pointcut.setExpression("within(hello.advanced.aop..*)");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("타켓의 타입에만 직접 적용, 인터페이스 선정하면 안된다.")
	void withinSuperTypeFalse() {
		pointcut.setExpression("within(hello.advanced.aop.member.MemberService)");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
	}

	@Test
	@DisplayName("execution은 타입 기반, 인터페이스 설정 가능")
	void executionSuperTypeType() {
		pointcut.setExpression("execution(* hello.advanced.aop.member.MemberService.*(..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

}
