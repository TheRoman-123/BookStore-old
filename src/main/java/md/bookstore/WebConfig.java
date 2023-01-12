package md.bookstore;

import lombok.AllArgsConstructor;
import md.bookstore.filter.JwtFilter;
import md.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.security.SecureRandom;

@EnableWebMvc
@EnableWebSecurity
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final JwtFilter jwtFilter;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

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
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers(HttpMethod.POST,"/api/auth/login", "/api/auth/token", "/register", "/sales").permitAll()
                        .antMatchers(HttpMethod.GET).permitAll()
                        .antMatchers(HttpMethod.GET,"/sales").authenticated()
                        .anyRequest().authenticated()
                        .and()
                        .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                );

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
            int secure = 10;
            return new BCryptPasswordEncoder(secure, new SecureRandom());
        }
    }
}
