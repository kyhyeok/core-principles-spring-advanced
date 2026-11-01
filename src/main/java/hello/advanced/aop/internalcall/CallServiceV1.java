package hello.advanced.aop.internalcall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CallServiceV1 {

	private CallServiceV1 callServiceV1;

	// 자기 자신(프록시)을 주입받아 내부 호출도 AOP가 적용되게 함
	// 생성자 주입은 생성 시점에 자기 자신이 없어서 순환 참조가 되므로 setter 주입 사용
	@Autowired
	public void setCallServiceV1(CallServiceV1 callServiceV1) {
		log.info("callServiceV1 setter={}", callServiceV1.getClass());
		this.callServiceV1 = callServiceV1;
	}

	public void external() {
		log.info("call external");
		callServiceV1.internal(); //내부 메서드 호출(this.internal())
	}

	public void internal() {
		log.info("call internal");
	}
}
