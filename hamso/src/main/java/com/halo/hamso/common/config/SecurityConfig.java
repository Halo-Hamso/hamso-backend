package com.halo.hamso.common.config;


import com.halo.hamso.filter.JwtAuthenticationFilter;
import com.halo.hamso.utils.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtProvider jwtProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // ID, Password 문자열을 Base64로 인코딩하여 전달하는 구조
                .httpBasic().disable()
                // 쿠키 기반이 아닌 JWT 기반이므로 사용 X
                .csrf().disable()
                //cors 설정 --> 나중에 오류로 고생할 수있음
                .cors().configurationSource(corsConfigurationSource())
                .and()
                // Spring Security 세션 정책 : 세션을 생성 및 사용하지 않음
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                // 조건별로 요청 허용/제한 설정
                .authorizeRequests()

                //API 명세서 관련된 모든 요소들도 모두 승인
                .antMatchers("/", "/swagger-ui/**", "/v3/**","/swagger-ui.html").permitAll()

                // 로그인 회원가입은 누구나 가능하다.
                .antMatchers("/auth/login","/auth/signup").permitAll()

                // 문자 인증 번호 발생은 누구나 가능
                .antMatchers("/sms/send").permitAll()

                // 가계부 조회 ( 개발 단계에서는 누구나 가능하도록 설정 )
                .antMatchers("/account-book/**").permitAll()

                // 이미지 업로드
                .antMatchers("/bill/image").permitAll()

                // 이미지 조회
                .antMatchers("/bills").permitAll()

                // /admin으로 시작하는 요청은 ADMIN 권한이 있는 유저에게만 허용
                .antMatchers("/admin/**").hasRole("ADMIN")
                // /user 로 시작하는 요청은 USER 권한이 있는 유저에게만 허용
                .antMatchers("/user/**").hasRole("USER")

                .anyRequest().authenticated(); // 위에서 설정한 API를 제외하고는 모두 JWT 필터를 거친다는 소리


        /** 아래 내용은 인증과 권한을 확인하는 단계이다. 일반 회원가입으로 로그인을 진행하면 로그인시 제공한 JWT 토큰을 확인하여 아래 과정이 일어남
         * */

        http
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)

                // JWT 인증 필터 적용

                // 에러 핸들링
                .exceptionHandling()
                .accessDeniedHandler(new AccessDeniedHandler() { // 권한이 없는경우 처리 즉 인증은 됬지만 role의 권한이 없는 것
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        // 권한 문제가 발생했을 때 이 부분을 호출한다.
                        response.setStatus(403);
                        response.setCharacterEncoding("utf-8");
                        response.setContentType("text/html; charset=UTF-8");
                        response.getWriter().write("권한이 없는 사용자입니다.");
                    }
                })
                .authenticationEntryPoint(new AuthenticationEntryPoint() { // 인증이 안된 사용자를 예외처리 하는 것
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                        // 인증문제가 발생했을 때 이 부분을 호출한다.
                        response.setStatus(401);
                        response.setCharacterEncoding("utf-8");
                        response.setContentType("text/html; charset=UTF-8");
                        response.getWriter().write("인증되지 않은 사용자입니다.");
                    }
                });


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();

        // 모든 도메인으로부터의 요청을 허용함
        configuration.addAllowedOriginPattern("*");
        // 모든 헤더를 허용
        configuration.setAllowedMethods(Arrays.asList("*")); // 모든 HTTP 메서드 허용

        // 모든 종류의 HTTP 메서드 허용  (GET, POST, 등등 )
        configuration.setAllowedHeaders(Arrays.asList("*"));

        //요청에 사용자 인증 정보를 포함할 수 있음 예를 들어 jwt나 세션기반 인증
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration); // 모든 경로에 대해 CORS 구성 적용
        return source;
    }
}
