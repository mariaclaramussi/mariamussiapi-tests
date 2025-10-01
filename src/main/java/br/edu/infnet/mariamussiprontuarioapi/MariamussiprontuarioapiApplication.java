package br.edu.infnet.mariamussiprontuarioapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MariamussiprontuarioapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MariamussiprontuarioapiApplication.class, args);
	}

}
