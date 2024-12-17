package pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // definir rutas protegidas y quien puede acceder a ellas
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/manage/login").permitAll() // rutas con acceso no autenticado
                        .requestMatchers("/manage/start").hasAnyRole("ADMIN", "OPERATOR") // rutas para ADMIN y OPERATOR
                        .requestMatchers("/manage/add").hasAnyRole("ADMIN") // acceso para ADMIN unicamente
                )

                // Configuración para las rutas de las APIs REST
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/public/**").permitAll()  // Rutas públicas para APIs
                        .requestMatchers("/manage-guest/**").authenticated()  // Requiere autenticación para estas rutas
                )

                // Habilitar la autenticación básica para las APIs REST sin usar httpBasic()
                .httpBasic(Customizer.withDefaults())  // Usa la configuración predeterminada para la autenticación básica

                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() // El resto de las rutas requieren autenticación
                )

                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF para pruebas en Postman
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Habilitar CORS

                // Manejo de excepciones para APIs y rutas web
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((req, resp, authException) -> {
                            if (req.getRequestURI().startsWith("/api/")) {
                                // Devuelve error 401 en JSON para las APIs REST
                                resp.setContentType("application/json");
                                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                resp.getWriter().write("{\"error\": \"Unauthorized\", \"message\": \"" + authException.getMessage() + "\"}");
                            } else {
                                // Redirigir a la página de login para las rutas web
                                resp.sendRedirect("/manage/login");
                            }
                        })
                        .accessDeniedHandler((req, resp, accessDeniedException) -> {
                            if (req.getRequestURI().startsWith("/api/")) {
                                // Devuelve error 403 en JSON para las APIs REST
                                resp.setContentType("application/json");
                                resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                                resp.getWriter().write("{\"error\": \"Forbidden\", \"message\": \"" + accessDeniedException.getMessage() + "\"}");
                            } else {
                                // Redirigir a la página de acceso restringido para rutas web
                                resp.sendRedirect("/manage/restricted");
                            }
                        })
                )

                // Configuración del formulario de inicio de sesión
                .formLogin(form -> form
                        .loginPage("/manage/login")
                        .defaultSuccessUrl("/manage/start", false)
                        .permitAll()
                )

                // configurar logout
                .logout(logout -> logout
                        .logoutUrl("/manage/logout")
                        .logoutSuccessUrl("/manage/login?logout")
                        .permitAll()
                );

        return http.build();

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowCredentials(true);
        config.addExposedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
