package com.halo.hamso.utils.jwt;


import com.halo.hamso.repository.Authority.Authority;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtProvider {

    @Value("${jwt.token.secret}")
    private String secret;
    private  Key key;

    public final CustomUserDetailService customUserDetailService;

    @Autowired
    public JwtProvider(CustomUserDetailService customUserDetailService)
    {
        this.customUserDetailService = customUserDetailService;
    }

    // 주입이 완료된후 key값을 초기화
    @PostConstruct
    protected void init()
    {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    // 토큰 생성
    public String createToken(String account, List<Authority> roles) {
        Claims claims = Jwts.claims().setSubject(account);
        claims.put("roles", roles);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() +  86400)) //만료기간
                .signWith(key, SignatureAlgorithm.HS256)   // HS512 알고리즘을 사용하여 secretKey를 이용해 서명
                .compact(); // jwt 토큰 생성
    }

    //Jwt 토큰을 복호화 하여 토큰 정보에 담은 Subject 가져오기 ( 암호화를 푸는 것)
    public String getAccount(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            // Bearer 검증
            if (!token.substring(0, "BEARER ".length()).equalsIgnoreCase("BEARER ")) {
                return false;
            } else {
                token = token.split(" ")[1].trim();
            }
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            // 만료되었을 시 false
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }


    // Authorization Header를 통해 인증을 한다. 헤더에서 Authorization 부분만 파싱해서 사용하기 위해 만들었음
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    // 권한정보 획득
    // Spring Security 인증과정에서 권한확인을 위한 기능
    public Authentication getAuthentication(String token)  {
        UserDetails userDetails=customUserDetailService.loadUserByUsername(this.getAccount(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
