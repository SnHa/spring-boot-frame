package com.atsun.coreapi.utils;

import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Description: Created by LD on 2018/1/25</p>
 *
 * @author LD
 */
@Slf4j
public class ServletUtils {

    public static final String HEADER_JWT_AUTHORIZATION = "JWT-AUTHORIZATION";

    public static boolean isAjaxRequest() {
        return isAjaxRequest(getRequest());
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

    public static String readBody() throws Exception {
        return readBody(StandardCharsets.UTF_8);
    }

    public static String readBody(Charset charset) throws Exception {
        return readBody(getRequest(), charset);
    }

    public static String readBody(HttpServletRequest request) throws Exception {
        return readBody(request, StandardCharsets.UTF_8);
    }

    public static String readBody(HttpServletRequest request, Charset charset) throws Exception {
        try {
            return IoUtil.read(request.getInputStream(), charset);
        } catch (Exception e) {
            log.error(String.format("Read request input stream error: %s", e.getMessage()), e);
            throw e;
        }
    }

    public static void writeJson(String json) throws Exception {
        write(json, "application/json", StandardCharsets.UTF_8);
    }

    public static void writeJson(HttpServletResponse response, String json) throws Exception {
        write(response, json, "application/json", StandardCharsets.UTF_8);
    }

    public static void write(String buffer, String contentType, Charset charset) throws Exception {
        write(getResponse(), buffer, contentType, charset);
    }

    public static void write(HttpServletResponse response, String buffer, String contentType, Charset charset) throws Exception {

        charset = null == charset ? StandardCharsets.UTF_8 : charset;

        response.setContentType(String.format("%s; charset=%s", StringUtils.isBlank(contentType) ? "text/plain" : contentType, charset.toString()));
        response.setCharacterEncoding(charset.toString());

        try (ServletOutputStream os = response.getOutputStream()) {
            os.write(buffer.getBytes(charset));
            os.flush();
        } catch (Exception e) {
            log.error(String.format("Writer print char error: %s", e.getMessage()), e);
            throw e;
        }
    }

    public static String getJwtToken() {
        return getJwtToken(getRequest());
    }

    public static String getJwtToken(HttpServletRequest request) {
        return request.getHeader(HEADER_JWT_AUTHORIZATION);
    }

    public static HttpServletRequest getRequest() {

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (null == attr) {
            throw new IllegalStateException("No thread-bound request found: " +
                    "Are you referring to request attributes outside of an actual web request, " +
                    "or processing a request outside of the originally receiving thread? " +
                    "If you are actually operating within a web request and still receive this message, " +
                    "your code is probably running outside of DispatcherServlet: " +
                    "In this case, use RequestContextListener or RequestContextFilter to expose the current request.");
        }

        return attr.getRequest();
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static HttpServletResponse getResponse() {

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (null == attr) {
            throw new IllegalStateException("No thread-bound request found: " +
                    "Are you referring to request attributes outside of an actual web request, " +
                    "or processing a request outside of the originally receiving thread? " +
                    "If you are actually operating within a web request and still receive this message, " +
                    "your code is probably running outside of DispatcherServlet: " +
                    "In this case, use RequestContextListener or RequestContextFilter to expose the current request.");
        }

        return attr.getResponse();
    }

    public static Object getSessionObj(String key) {
        return getSession().getAttribute(key);
    }

    public static void putSessionObj(String key, Object obj) {
        getSession().setAttribute(key, obj);
    }

    public static void removeSessionObj(String key) {
        getSession().removeAttribute(key);
    }

    public static String redirect(String url) {
        return String.format("redirect:%s", url);
    }

    public static String forward(String url) {
        return String.format("forward:%s", url);
    }

}
