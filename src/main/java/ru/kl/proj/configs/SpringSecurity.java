package ru.kl.proj.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
@ComponentScan({"ru.kl.proj.configs"})
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select organization, password, enabled"
                        + " from public.organizations where organization=?")
                .authoritiesByUsernameQuery("select organization, authority "
                        + "from public.organizations where organization=?")
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        CharacterEncodingFilter filter = new CharacterEncodingFilter();
//        filter.setEncoding("UTF-8");
//        filter.setForceEncoding(true);
//
//        http.authorizeRequests()
//                .antMatchers("/accountMainPage/**").access("hasRole('ROLE_organization')")
//                .antMatchers("/confidential/**").access("hasRole('ROLE_SUPERADMIN')")
//                .and().formLogin().defaultSuccessUrl("/accountMainPage", false);
//        http.addFilterBefore(filter, CsrfFilter.class);

        http.authorizeRequests()
                .antMatchers("/login")
                .permitAll()
                .antMatchers("/accountMainPage/**")
                .hasAnyRole("ADMIN", "ORGANIZATION")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .csrf()
                .disable();
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/accountMainPage")
//                .failureUrl("/login?error=true")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/login?logout=true")
//                .invalidateHttpSession(true)
//                .permitAll()
//                .and()


    }
}