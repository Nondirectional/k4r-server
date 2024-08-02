package com.non.k4r.framework.error;

import com.non.k4r.framework.commons.FastJsonView;
import com.non.k4r.framework.commons.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


public class GlobalErrorResolver implements HandlerExceptionResolver {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalErrorResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        Result error;
        if (ex instanceof MissingServletRequestParameterException) {
            error = Result.fail(1, ((MissingServletRequestParameterException) ex).getParameterName() + " 不能为空.");
        } else {
            LOG.error(ex.toString(), ex);
            error = Result.fail(1, ex.getMessage());
        }

        return new ModelAndView(new FastJsonView())
                .addAllObjects(error);
    }
}
