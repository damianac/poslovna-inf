package rs.ftn.xws.booking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import rs.ftn.xws.booking.security.JwtAuthenticationEntryPoint;
import rs.ftn.xws.booking.security.JwtAuthenticationFilter;
import rs.ftn.xws.booking.security.SecurityEvaluationContextExtension;
import rs.ftn.xws.booking.service.DomainUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
	securedEnabled = true,
	jsr250Enabled = true,
	prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private DomainUserDetailsService domainUserDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
    
    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension(){
        return new SecurityEvaluationContextExtension();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
            .userDetailsService(domainUserDetailsService)
            .passwordEncoder(passwordEncoder());
    }
    
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
	        .cors()
	            .and()
            .headers().frameOptions()
            	.disable()
            	.and()
	        .csrf()
	            .disable()
	        .exceptionHandling()
	            .authenticationEntryPoint(unauthorizedHandler)
	            .and()
	        .sessionManagement()
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and()
	        .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
	        .authorizeRequests()
	            .antMatchers("/",
			                 "/favicon.ico",
			                 "/**/*.png",
			                 "/**/*.gif",
			                 "/**/*.svg",
			                 "/**/*.jpg",
			                 "/**/*.html",
			                 "/**/*.css",
			                 "/**/*.js")
	                .permitAll()
	            .antMatchers("/api/account/**")
	                .permitAll()
	            .antMatchers("/services/**")
	            	.permitAll();
//	            .anyRequest()
//	                .authenticated();
    }
	
}
