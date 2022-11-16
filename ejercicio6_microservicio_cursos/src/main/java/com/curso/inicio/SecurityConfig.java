package com.curso.inicio;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public InMemoryUserDetailsManager usersDetailsMemory() throws Exception {
		List<UserDetails> users = List.of(
			User.withUsername("user1")
				.password("{noop}user1")
				.roles("Invitado")
				.build(),
			User.withUsername("user2")
				.password("{noop}user2")
				.roles("Operador")
				.build(),
			User.withUsername("user3")
			 	.password("{noop}user3")
			 	.roles("Admin")
			 	.build(),
			User.withUsername("user4")
			 	.password("{noop}user4")
			 	.roles("Operador", "Admin")
			 	.build()
			);
		
		return new InMemoryUserDetailsManager(users);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST,"/altaCurso").hasRole("Admin")
			.antMatchers(HttpMethod.DELETE,"/eliminarCurso/**").hasAnyRole("Admin","Operador")
			.antMatchers(HttpMethod.PUT,"/actualizarDuracion/**").hasAnyRole("Admin","Operador")
			.antMatchers("/cursos").authenticated()
			.antMatchers("/curso/**").authenticated()
			.antMatchers("/cursosPorPrecio/**").authenticated()
			.and()
			.httpBasic();
		
		return http.build();
	}
	
	
}
