package com.pda.pontoDeAcesso.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {
	public DevConfig() {
		System.out.println("Perfil de dev");
	}
}
