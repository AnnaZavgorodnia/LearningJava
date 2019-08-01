package com.test.controller.filter;

import com.test.model.entity.Role;
import com.test.model.entity.User;
import com.test.request.UserRoleRequestWrapper;
import com.test.utils.AppUtils;
import com.test.utils.SecurityUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class SecurityFilter implements Filter {
    public SecurityFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath();

        User loginedUser = AppUtils.getLoginedUser(request.getSession());

        if (servletPath.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest wrapRequest = request;

        if (loginedUser != null) {

            String userName = loginedUser.getUsername();

            Role role = loginedUser.getRole();

            wrapRequest = new UserRoleRequestWrapper(userName, role.name() , request);
        }

        if (SecurityUtils.isSecurityPage(request)) {

            if (loginedUser == null) {

                String requestUri = request.getRequestURI();

                int redirectId = AppUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);

                response.sendRedirect(wrapRequest.getContextPath() + "/app/login?redirectId=" + redirectId);
                return;
            }


            boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);

            if (!hasPermission) {

                RequestDispatcher dispatcher
                        = request.getServletContext().getRequestDispatcher("/WEB-INF/views/accessDeniedView.jsp");

                dispatcher.forward(request, response);
                return;
            }
        }

        chain.doFilter(wrapRequest, response);
    }

    @Override
    public void destroy() {
    }

}
