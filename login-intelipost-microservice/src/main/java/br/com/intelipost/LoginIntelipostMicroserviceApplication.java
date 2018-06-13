package br.com.intelipost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//TODO HABILITAR EUREKA CLIENT
//@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackages = "br.com.intelipost")
public class LoginIntelipostMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginIntelipostMicroserviceApplication.class, args);
	}
}
