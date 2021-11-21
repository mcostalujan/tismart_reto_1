package max.costa.reto1;
import static max.costa.reto1.utility.Utility.TIME_ZONE;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Reto1Application {

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone(TIME_ZONE));
	}

	public static void main(String[] args) {
		SpringApplication.run(Reto1Application.class, args);
	}

}
