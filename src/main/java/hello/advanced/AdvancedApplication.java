package hello.advanced;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import hello.advanced.app.proxy.config.AppV1Config;

@Import(AppV1Config.class)
@SpringBootApplication(scanBasePackages = "hello.advanced.app.proxy")
public class AdvancedApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedApplication.class, args);
	}

}
