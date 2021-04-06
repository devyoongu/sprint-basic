package hello.basic; //해당 패키지 하위에서만 컴포넌트 스캔이된다.

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasicApplication {

	public static void main( String[] args) {
		SpringApplication.run(BasicApplication.class, args);
	}

}
