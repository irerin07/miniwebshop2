package com.tistory.irerin07.miniwebshop.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.lang.invoke.MethodType;

// spring security 설정 파일은 보통
// WebSecurityConfigurerAdapter 를 상속받아서 만든다.
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 아예 인가처리를 하지 않는 (무시하는 URL설정) - 이미지 or css, javascript
    @Override
    public void configure(WebSecurity web) throws Exception {

//    PathRequest.toStaticResources().atCommonLocations()
//    CSS(new String[]{"/css/**"}),
//    JAVA_SCRIPT(new String[]{"/js/**"}),
//    IMAGES(new String[]{"/images/**"}),
//    WEB_JARS(new String[]{"/webjars/**"}),
//    FAVICON(new String[]{"/**/favicon.ico"});
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .requestMatchers(new AntPathRequestMatcher("/**.html"))
                .requestMatchers(new AntPathRequestMatcher("/static/**"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .logout() // logout설정
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/main")
                .permitAll().and()
                .authorizeRequests() // 인가에 대한 설정
                .antMatchers(HttpMethod.GET,"/api/comments").permitAll()
                .antMatchers(HttpMethod.POST,"/api/comments").permitAll()
                .antMatchers("/users/delete").permitAll()
                .antMatchers("/users/join").permitAll()
                .antMatchers("/users/welcome").permitAll()
                .antMatchers("/users/login").permitAll()
                .antMatchers("/users/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/posts/images/**").permitAll()
                .antMatchers("/posts/write").hasAnyRole("USER", "ADMIN")
                .antMatchers("/posts/{id}").permitAll()
                .antMatchers("/posts/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/main").permitAll()
                .antMatchers("/chatrooms").hasAnyRole("USER", "ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin() // 사용자가 정의하는 로그인 화면을 만들겠다.
                .loginProcessingUrl("/users/login") // 로그인 화면
                .loginPage("/users/login") // 사용자가 입력한 id, password가 전달되는 url경로(필터가처리)
                .usernameParameter("email")
                .passwordParameter("passwd")
                .defaultSuccessUrl("/users/welcome")
                .failureUrl("/users/login?fail=true").and().csrf().ignoringAntMatchers("/**");
    }
}