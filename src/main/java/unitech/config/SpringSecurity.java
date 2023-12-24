package unitech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
public class SpringSecurity {



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .requestMatchers("/login","/register").permitAll()
                .requestMatchers("/add/cc","/update/**","/find/all","/transfer").permitAll()
                .requestMatchers("/convert-currency**").permitAll()
                .anyRequest()
                .authenticated()
                .and().formLogin()
                .successForwardUrl("/find/all")
                .and()
                .logout()
                .and()
                .csrf()
                .disable();

        return http.build();
    }

}
