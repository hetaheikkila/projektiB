package s24.ruokasovelluss.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import s24.ruokasovelluss.web.UserDetailServiceImpl;

@Configuration
public class WebSecurityConfig {
	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

@Bean
public SecurityFilterChain configure(HttpSecurity http) throws Exception {
	http
	.authorizeHttpRequests( authorize -> authorize
	.anyRequest().authenticated()
	)
	.formLogin( formlogin -> formlogin
	.loginPage("/login")
	.defaultSuccessUrl("/index", true)
	.permitAll()
	);
	return http.build();
   }
}