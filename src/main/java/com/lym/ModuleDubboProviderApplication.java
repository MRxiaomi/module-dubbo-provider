package com.lym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class ModuleDubboProviderApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ModuleDubboProviderApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		application.sources(this.getClass());
		return super.configure(application);
	}
}
