package com.non.k4r.inteceptor;

import com.alibaba.fastjson.JSON;
import com.non.k4r.context.RequestContext;
import com.non.k4r.domain.AccessTokenBody;
import com.non.k4r.framework.commons.Result;
import com.non.k4r.manager.AccessTokenManager;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import static com.non.k4r.framework.constant.ErrorCodes.*;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    private final AntPathMatcher matcher = new AntPathMatcher();

    private static final String NO_AUTH_URI_PATTERN = "/**/no-auth/**";

    @Resource
    private AccessTokenManager accessTokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从request中获取到uri
        String uri = request.getRequestURI();

        // 根据uri判断是否不需要鉴权的请求
        boolean noAuthorizationRequire = matcher.match(NO_AUTH_URI_PATTERN, uri);
        if (noAuthorizationRequire) {
            // 放行
            return true;
        }

        // 从request header中取出access token
        String accessToken = request.getHeader("Access-Token");

        // access token为空
        if (!StringUtils.hasText(accessToken)) {
            Result result = Result.fail(ACCESS_TOKEN_EMPTY.getErrorCode(), ACCESS_TOKEN_EMPTY.getMessage());
            writeError2Response(HttpStatus.UNAUTHORIZED, response, result);
            return false;
        }

        AccessTokenBody accessTokenBody = accessTokenManager.getTokenBody(accessToken);
        if (accessTokenBody == null) {
            Result result = Result.fail(ACCESS_TOKEN_INVALID.getErrorCode(), ACCESS_TOKEN_INVALID.getMessage());
            writeError2Response(HttpStatus.UNAUTHORIZED, response, result);
            return false;
        }

        RequestContext.setAccessTokenBody(accessTokenBody);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    private static void writeError2Response(HttpStatus status, HttpServletResponse response, Result result) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
