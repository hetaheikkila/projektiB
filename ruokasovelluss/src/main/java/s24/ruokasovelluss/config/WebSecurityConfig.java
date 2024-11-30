package s24.ruokasovelluss.config;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;

import s24.ruokasovelluss.web.UserDetailServiceImpl;

import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig  {
	@Autowired
	private UserDetailServiceImpl userDetailsService;

   @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("http://localhost:5173"); // URL sallittu tekemään pyyntöjä tälle palvelimelle
        corsConfig.addAllowedMethod("*"); //Antaa luvan kaikille HTTP  pyynnöille (GET, POST, etc.)
        corsConfig.addAllowedHeader("*"); // HTTP-otsikot ovar sallittuja pyynnöstä
        corsConfig.setAllowCredentials(true); // Evästeiden ja HTTP-autentikoinnin, lähettämisen pyynnöissä

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig); // CORS policy kaikille endpointeille
        return new CorsFilter(source);
    }



	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {

		http
		.csrf(csrf -> csrf.ignoringRequestMatchers(antMatcher("/api/users"))
		)
		.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(antMatcher("/css/**")).permitAll()
				.requestMatchers(antMatcher("/api/users/**")).permitAll()
				.requestMatchers(HttpMethod.POST, "/api/users").permitAll()
				.requestMatchers(antMatcher("/api/**")).permitAll()
				.requestMatchers(antMatcher("/reseptit")).permitAll()
				.requestMatchers(antMatcher("/resepti/{id}")).permitAll()
				.requestMatchers(antMatcher("/ainesosat")).permitAll()
				.requestMatchers(antMatcher("/ainesosa/{id}")).permitAll()
                .requestMatchers(antMatcher("/kategoriat")).permitAll()
				.requestMatchers(antMatcher("/kategoria/{id}")).permitAll()
			.anyRequest().authenticated()
		)
		
		.headers(headers -> headers
				.frameOptions(frameoptions -> 
				frameoptions.disable() 		
				)
		)
		.formLogin(formlogin -> formlogin
				.loginPage("/login")
				.defaultSuccessUrl("/index", true)
				.permitAll()
		)
		.logout(logout -> logout
				.permitAll()
		);
				
		return http.build();
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}}