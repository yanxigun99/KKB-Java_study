package com.abc.filter;

import javax.servlet.*;
import java.io.IOException;

public class SomeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("信息被过滤");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
