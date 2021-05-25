package com.projeto.vacinaja.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.projeto.vacinaja.service.UsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

		@Autowired
	private UsuarioService userService;
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().
		authorizeRequests().
        antMatchers("/api/cidadao/**", "/api/registroFuncionario/{id}").hasAnyAuthority("CIDADAO", "ADMIN").
        antMatchers("/api/cadastro").hasAnyAuthority("VISITANTE", "ADMIN").
        antMatchers("/api/funcionario/*", "/api/funcionarios").hasAnyAuthority("FUNCIONARIO", "ADMIN").
        antMatchers("/api/vacinas", "/api/lotes", "/api/vacina/**").hasAnyAuthority("FUNCIONARIO", "ADMIN").
        antMatchers("/api/**").hasAuthority("ADMIN").
		anyRequest().authenticated().and().formLogin().defaultSuccessUrl("https://vacinaja-grupo-04.herokuapp.com/swagger-ui.html").permitAll().
		failureUrl("/login?error=true").permitAll()
		.and()
		.logout().logoutSuccessUrl("/login").permitAll();
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                                   "/configuration/ui",
                                   "/swagger-resources/**",
                                   "/configuration/security",
                                   "/swagger-ui.html",
                                   "/webjars/**");
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(authenticationProvider());
	}
}
