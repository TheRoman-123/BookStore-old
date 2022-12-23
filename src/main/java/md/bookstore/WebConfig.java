package md.bookstore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.security.SecureRandom;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebMvc
@EnableWebSecurity
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // @CrossOrigin(origins="http://localhost:4200") для RestController
        registry.addMapping("/**") // к каким страничкам доступ
                .allowedOrigins("http://localhost:4200") // от какого сервера запрошен доступ
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"); // "*" Тип http-запроса
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user/**").hasAuthority("USER")
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin(withDefaults());
        // По умолчанию проверка на csrf токен работает
        /*      .httpBasic(withDefaults());*/
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        int secure = 16;
        return new BCryptPasswordEncoder(secure, new SecureRandom()); // SecureRandom - salt
    }
}
