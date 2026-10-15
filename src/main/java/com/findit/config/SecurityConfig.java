package com.findit.config;
import com.findit.repository.UserRepository; import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.*; import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain; import org.springframework.security.config.annotation.web.builders.HttpSecurity;
@Configuration public class SecurityConfig {
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean UserDetailsService userDetailsService(UserRepository repo){return email->repo.findByEmailIgnoreCase(email).map(u->User.withUsername(u.getEmail()).password(u.getPassword()).roles(u.getRole().name()).build()).orElseThrow(()->new UsernameNotFoundException("User not found"));}
 @Bean SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
  http.authorizeHttpRequests(a->a.requestMatchers("/","/register","/login","/gallery","/item/**","/css/**","/js/**","/uploads/**","/api/**","/error").permitAll().requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated())
  .formLogin(f->f.loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/dashboard",true).failureUrl("/login?error").permitAll())
  .logout(l->l.logoutSuccessUrl("/?logout").permitAll()); return http.build();
 }
}