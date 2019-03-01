package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	//@Qualifier("")
	private UserDetailsService userDetailsService;

	@Autowired
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
//				.csrf()
//				.disable()
				.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/swagger-ui.html/**", "/v2/api-docs/**")
				.permitAll()
				.anyRequest()
				.fullyAuthenticated()
//				.authenticated()
				.and()
				.httpBasic()
//				.and()
//				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.logout()
				.logoutSuccessUrl("/");

		super.configure(http);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		List<GrantedAuthority> authorities = Stream.of("AUTH_1", "AUTH_2", "AUTH_3")
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());*/

		auth.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	/*	auth.inMemoryAuthentication()
				auth.jdbcAuthentication().dataSource().withUser("dave")
				.password("secret").roles("USER");
				.withUser("user")
				.password(encoder.encode("123"))
				.authorities(authorities)
			//	.roles("USER")
				.and()
				.withUser("manager")
				.password("password")
				.credentialsExpired(true)
				.accountExpired(true)
				.accountLocked(true)
				.authorities("WRITE_PRIVILEGES", "READ_PRIVILEGES")
				.roles("MANAGER");*/
}
