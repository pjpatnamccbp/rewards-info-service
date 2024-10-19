package com.sreenutech.rewards;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;
//import org.springframework.context.annotation.Bean;
//import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@CrossOrigin
public class RewardsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardsServiceApplication.class, args);
	}
	
	/*
	 * @Bean public JdbcTemplate jdbcTemplate() { return new JdbcTemplate(); }
	 */
	
	@Bean 
	public RestTemplate restTemplate() {
		
		return new RestTemplate();
	}

}
