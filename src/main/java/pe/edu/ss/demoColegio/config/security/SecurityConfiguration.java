package pe.edu.ss.demoColegio.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioDetailsService usuarioDetailsService;
	
	@Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/index.html").permitAll()
				.antMatchers("/menuestudiante").hasRole("ALUMNO")
				.antMatchers("/alumno").hasAnyRole("ADMIN","TRABAJADOR")
				.antMatchers("/alumno/nuevo").hasAnyRole("ADMIN","TRABAJADOR")
				.antMatchers("/alumno/info/**").hasAnyRole("ADMIN","TRABAJADOR","ALUMNO")
				.antMatchers("/alumno/**/nuevamatricula").hasAnyRole("ADMIN","TRABAJADOR")
				.antMatchers("/alumno/**/nuevoapoderado").hasAnyRole("ADMIN","TRABAJADOR")
				.antMatchers("/alumno/**/nuevousuario").hasAnyRole("ADMIN","TRABAJADOR")
				.antMatchers("/alumno/edit/**").hasAnyRole("ADMIN","TRABAJADOR")
				.antMatchers("/alumno/del/**").hasRole("ADMIN")
				.antMatchers("/grado").hasAnyRole("ADMIN","TRABAJADOR")
				.antMatchers("/grado/info/**").hasAnyRole("ADMIN","TRABAJADOR")
				.antMatchers("/grado/nuevo").hasRole("ADMIN")
				.antMatchers("/grado/**/nuevaseccion").hasRole("ADMIN")
				.antMatchers("/grado/infoseccion/**").hasAnyRole("ADMIN","TRABAJADOR")
				.antMatchers("/grado/edit/**").hasRole("ADMIN")
				.antMatchers("/grado/del/**").hasRole("ADMIN")
				.antMatchers("/trabajador").hasAnyRole("ADMIN","TRABAJADOR")
				.antMatchers("/trabajador/**/nuevousuario").hasRole("ADMIN")
				.antMatchers("/trabajador/nuevo").hasRole("ADMIN")
				.antMatchers("/trabajador/edit/**").hasRole("ADMIN")
				.antMatchers("/trabajador/info/**").hasAnyRole("ADMIN","TRABAJADOR")
				.antMatchers("/trabajador/del/**").hasRole("ADMIN")
				.antMatchers("/apoderado").hasAnyRole("ADMIN","TRABAJADOR")
				.antMatchers("/apoderado/info/**").hasAnyRole("ADMIN","TRABAJADOR")
				.antMatchers("/apoderado/edit/**").hasAnyRole("ADMIN","TRABAJADOR")
				.antMatchers("/apoderado/del/**").hasRole("ADMIN")
				.antMatchers("/cargo").hasAnyRole("ADMIN","TRABAJADOR")
				.antMatchers("/cargo/info/**").hasAnyRole("ADMIN","TRABAJADOR")
				.antMatchers("/cargo/nuevo").hasRole("ADMIN")
				.antMatchers("/cargo/edit/**").hasRole("ADMIN")
				.antMatchers("/cargo/del/**").hasRole("ADMIN")
			.and()
			.formLogin()
				.loginProcessingUrl("/signin")
				.loginPage("/login").permitAll()
				.usernameParameter("inputUsername")
                .passwordParameter("inputPassword")
			.and()
	        .logout()
	        	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	        	.logoutSuccessUrl("/")
	        .and()
            .rememberMe()
            	.tokenValiditySeconds(2592000)
            	.key("Cl4v3.")
            	.rememberMeParameter("checkRememberMe")
            	.userDetailsService(usuarioDetailsService)
            .and()
                .exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandler);
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder( ) {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.usuarioDetailsService);

        return daoAuthenticationProvider;
    }
	
}
