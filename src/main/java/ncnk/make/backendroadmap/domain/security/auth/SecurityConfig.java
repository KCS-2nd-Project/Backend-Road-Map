package ncnk.make.backendroadmap.domain.security.auth;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import ncnk.make.backendroadmap.domain.entity.Role;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/login","/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile");
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .securityMatcher(new NegatedRequestMatcher(EndpointRequest.toAnyEndpoint()))
                .csrf(csrf -> csrf.disable())
                .headers(header -> header
                        .frameOptions(frameOptions -> frameOptions.disable()))

                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/").permitAll()
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .dispatcherTypeMatchers(DispatcherType.INCLUDE).permitAll()
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .requestMatchers("/api/v1/**").hasRole(Role.USER.name())
                        .requestMatchers("/actuator/**").permitAll()
//                                .requestMatchers("/   admins/**", "/api/v1/admins/**").hasRole(Role.ADMIN.name())
//                        .anyRequest().authenticated())
                        .anyRequest().permitAll())
//                .formLogin((formLogin) ->
//                        formLogin
//                                .loginPage("/login/login")
//                                .usernameParameter("username")
//                                .passwordParameter("password")
//                                .loginProcessingUrl("/login/login-proc")
//                                .defaultSuccessUrl("/", true)
//                )
                .logout((logoutConfig) ->
                        logoutConfig.logoutSuccessUrl("/")
                                .invalidateHttpSession(true))
                .oauth2Login(oauth2Login -> oauth2Login
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                                .userService(customOAuth2UserService)));
        return http.build();
    }
    
    @Bean
    public SecurityFilterChain actuatorFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests.anyRequest().permitAll());
        return http.build();
    }
}