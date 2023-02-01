package main;

import account.AccountEntity;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "FilterAuth")
public class FilterAuth implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        AccountEntity user = (AccountEntity) ((HttpServletRequest) request).getSession().getAttribute("user");
        HttpServletResponse resp = (HttpServletResponse) response;
        String redirectURL = resp.encodeRedirectURL(((HttpServletRequest) request).getContextPath() + "/login.jsp");


        chain.doFilter(request, response);
    }
}
