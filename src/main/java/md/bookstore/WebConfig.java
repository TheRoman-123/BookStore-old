package md.bookstore;

import lombok.AllArgsConstructor;
import md.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.security.SecureRandom;

@Configuration
@EnableWebMvc
@EnableWebSecurity
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // @CrossOrigin(origins="http://localhost:4200") для RestController
        registry.addMapping("/**") // к каким страничкам доступ
                .allowedOrigins("http://localhost:4200") // от какого сервера запрошен доступ
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"); // "*" Тип http-запроса
    }

    // По умолчанию проверка на csrf токен работает
//                        TODO: Сделай нормальную авторизацию для юзера и админа
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/user/**").hasAuthority("USER")     -- При Spring Boot 3
//                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .antMatchers("/", "/books/**").permitAll()
                        .antMatchers("/sales/confirm/**", "/sales/decline/**")
                                .hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .rememberMe()
                .and()
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }

    static class PWEncoder {
        @Bean
        public PasswordEncoder passwordEncoder() {
            int secure = 16;
            return new BCryptPasswordEncoder(secure, new SecureRandom());
        }
    }
}
