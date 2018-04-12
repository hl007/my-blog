package main.java.blog.config;

import main.java.blog.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserRepository userRepository;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(new BlogUserService(userRepository));
    }

  @Override
  protected void configure(HttpSecurity http) throws Exception{
      http.formLogin().
              loginPage("/login").loginProcessingUrl("/signIn").defaultSuccessUrl("/loginSucess").and()
              .rememberMe().tokenValiditySeconds(2419200).key("blogKey")
              .and()
              .logout().logoutSuccessUrl("/home")
              .and()
              .authorizeRequests()
              .antMatchers("/edit").hasRole("USER")
              .antMatchers(HttpMethod.GET, "/edit").authenticated()
              .anyRequest().permitAll();
  }
}
