package com.example.shopping.handler;


import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * 로그인 성공 처리 핸들러
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws javax.servlet.ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //로그인 성공 후 요청 전 페이지로 이동
        String prevPage = (String) request.getSession().getAttribute("prevPage");
        if(prevPage.contains("error") || prevPage.contains("login")){
            prevPage = "/main";
        }
        redirectStrategy.sendRedirect(request,response,prevPage);
    }
}
