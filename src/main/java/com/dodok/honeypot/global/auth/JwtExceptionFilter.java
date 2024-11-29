package com.dodok.honeypot.global.auth;

import com.dodok.honeypot.global.error.code.ErrorCode;
import com.dodok.honeypot.global.error.entity.ErrorResponse;
import com.dodok.honeypot.global.error.exception.EntityNotFoundException;
import com.dodok.honeypot.global.error.exception.ForbiddenException;
import com.dodok.honeypot.global.error.exception.UnauthorizedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.dodok.honeypot.global.error.code.GlobalErrorCode.INTERNAL_SERVER_ERROR;

@RequiredArgsConstructor
@Slf4j
public class JwtExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("CONNECT URL : {}", request.getRequestURI());

        try {
            filterChain.doFilter(request, response);
        } catch (ForbiddenException | UnauthorizedException | EntityNotFoundException e) {
            handleJwtException(response, e);
        }
    }


    private void handleJwtException(HttpServletResponse response, Exception exception) throws IOException {
        log.error(exception.getMessage());

        //Jackson이 java.time.LocalDateTime을 직렬화/역직렬화 하는데 필요한 모듈 등록
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        ErrorCode errorCode;

        if (exception instanceof ForbiddenException forbiddenException) {
            errorCode = forbiddenException.getErrorCode();
        } else if (exception instanceof EntityNotFoundException entityNotFoundException) {
            errorCode = entityNotFoundException.getErrorCode();
        } else if(exception instanceof UnauthorizedException unauthorizedException){
            errorCode = unauthorizedException.getErrorCode();
        } else errorCode = INTERNAL_SERVER_ERROR;

        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(ErrorResponse.of(errorCode)));
    }
}
