package mk.finki.ukim.mk.lab.web.filter;

import mk.finki.ukim.mk.lab.model.User;
import org.springframework.context.annotation.Profile;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
@Profile("servlet")
public class BalloonColorFIlter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response1 = (HttpServletResponse) response;
        HttpServletRequest request1 = (HttpServletRequest) request;

        String size = (String) request1.getSession().getAttribute("size");
        String cname = (String) request1.getSession().getAttribute("cn");
        String caddr = (String) request1.getSession().getAttribute("ca");
        User user = (User) request1.getSession().getAttribute("user");
        String path = request1.getServletPath();
        String check = (String) request1.getSession().getAttribute("boja");
            if (!"/login".equals(path) && user == null && !path.contains("register") && !path.equals("/favicon.ico")) {
                response1.sendRedirect("/login");
            } else if (!"/balloons".equals(path) && check == null && user != null) {
                if (path.contains("/delete") || path.contains("/add") || path.contains("/edit")) chain.doFilter(request, response);
                else response1.sendRedirect("/balloons");
            } else if (!"/selectBalloon".equals(path) && size == null && check != null){
                response1.sendRedirect("/selectBalloon");
            } else if (size != null && !"/BalloonOrder".equals(path) && (cname.equals("") || caddr.equals(""))) {
                response1.sendRedirect("/BalloonOrder");
            } else {
                 chain.doFilter(request,response);
            }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}