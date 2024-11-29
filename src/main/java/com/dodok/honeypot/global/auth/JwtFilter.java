package com.dodok.honeypot.global.auth;

import com.dodok.honeypot.global.error.exception.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.dodok.honeypot.domain.auth.error.AuthErrorCode.TOKEN_NOT_FOUND;
import static com.dodok.honeypot.domain.auth.type.MemberRole.MEMBER;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    private static final List<RequestMatcher> whiteUrlMatchers = Arrays.asList(
            //TODO : 와일드카드 표현 삭제하기

            //auth
            new AntPathRequestMatcher("/api/auth/kakao-login"),
            new AntPathRequestMatcher("/api/auth/register"),
            new AntPathRequestMatcher("/api/auth/reissue"),
            new AntPathRequestMatcher("/api/auth/send-mail"),
            new AntPathRequestMatcher("/api/auth/check-mail"),

            //member
            new AntPathRequestMatcher("/api/member/profile-image"),

            //badge

            //group

            //stamp
            new AntPathRequestMatcher("/api/stamp"),

            //send-praise
            new AntPathRequestMatcher("/api/send-praise/group"),
            new AntPathRequestMatcher("/api/send-praise/check"),

            //receive-praise
            // TODO : POST, DEL은 매치하도록 수정
            new AntPathRequestMatcher("/api/receive-praise"),


            //기타
            new AntPathRequestMatcher("/api/health")
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        for (RequestMatcher urlMatchers : whiteUrlMatchers) {
            if (urlMatchers.matches(request)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        String authorizationHeader = getAuthorizationHeaderFromRequest(request);
        String accessToken = splitAccessTokenFromHeader(authorizationHeader);
        jwtUtil.validateJwtToken(accessToken, "accessToken");
        Long memberId = jwtUtil.getMemberIdFromAccessToken(accessToken);
        setAuthentication(request, memberId);
        filterChain.doFilter(request, response);
    }

    private String getAuthorizationHeaderFromRequest(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    private String splitAccessTokenFromHeader(String accessToken) {

        if (StringUtils.hasText(accessToken) && accessToken.startsWith("Bearer")) {
            return accessToken.split(" ")[1]; //토큰 꺼내기
        }
        throw new EntityNotFoundException(TOKEN_NOT_FOUND);
    }

    private void setAuthentication(HttpServletRequest request, Long memberId) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(memberId, null, List.of(new SimpleGrantedAuthority(MEMBER.getRole())));
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("[+] Token in SecurityContextHolder");
    }
}
