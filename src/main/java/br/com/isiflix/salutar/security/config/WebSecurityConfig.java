package br.com.isiflix.salutar.security.config;

import br.com.isiflix.salutar.security.MyFilter;
import br.com.isiflix.salutar.security.TokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final TokenUtil tokenUtil;

    public WebSecurityConfig(TokenUtil tokenUtil){
        this.tokenUtil = tokenUtil;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .anyRequest().authenticated());
        http.addFilterBefore(new MyFilter(tokenUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
