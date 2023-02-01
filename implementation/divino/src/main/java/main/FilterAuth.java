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

        if (user == null) {
            resp.sendRedirect(redirectURL);
            return;
        } else {
            HttpServletRequest req = (HttpServletRequest) request;
            if ((user.getRole().equals(AccountEntity.Role.MANAGERUSER) && !req.getRequestURI().contains("/admin/admin.jsp"))
                    || (user.getRole().equals(AccountEntity.Role.CUSTOMERUSER) && !req.getRequestURI().contains("/account/account.jsp"))
            ) {
                resp.sendRedirect(redirectURL);
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
