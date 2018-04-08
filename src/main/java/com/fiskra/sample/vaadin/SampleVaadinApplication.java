package com.fiskra.sample.vaadin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan(basePackages = {"com.fiskra.sample.vaadin.ui"})
@EnableJpaRepositories(basePackages = {"com.fiskra.sample.vaadin.repo"})
@EntityScan(basePackages = {"com.fiskra.sample.vaadin.model"} )
@SpringBootApplication
public class SampleVaadinApplication {
	
	private static final Logger log = LoggerFactory.getLogger(SampleVaadinApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SampleVaadinApplication.class, args);
	}
}
