package fcmb.com.good;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication
@RequiredArgsConstructor

@PropertySource(value = "file: /home/olakunle/Folders/spring/Collaborations/config_files/application.properties", ignoreResourceNotFound = true  )
public class GoodApplication {

	public static void main(String[] args) {

        SpringApplication.run(GoodApplication.class, args);
	}

}
