package com.non.k4r.framework.jsonbody;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;

public class RequestUtil {

    private static final Logger LOG = LoggerFactory.getLogger(RequestUtil.class);

    private static final String jsonCacheKey = "__$JSONObjectOrArray";

    public static Object readJsonObjectOrArray(HttpServletRequest request) {
        Object jsonObjectOrArray = request.getAttribute(jsonCacheKey);
        if (jsonObjectOrArray == null) {
            String body = readBodyString(request);
            jsonObjectOrArray = JSON.parse(body);
            request.setAttribute(jsonCacheKey, jsonObjectOrArray);
        }
        return jsonObjectOrArray;
    }


    public static String readBodyString(HttpServletRequest request) {
        try {
            String ce = request.getCharacterEncoding();
            InputStreamReader reader = new InputStreamReader(request.getInputStream(), ce != null ? ce : "UTF-8");
            StringBuilder sb = new StringBuilder();
            char[] buf = new char[1024];
            for (int num; (num = reader.read(buf, 0, buf.length)) != -1; ) {
                sb.append(buf, 0, num);
            }
            return sb.toString();
        } catch (IOException e) {
            LOG.error(e.toString(), e);
        }
        return null;
    }

}
