package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "balloon-order-servlet", urlPatterns = "/BalloonOrder")
public class BalloonOrderServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;

    public BalloonOrderServlet (SpringTemplateEngine springTemplateEngine,OrderService orderService) {
        this.springTemplateEngine = springTemplateEngine;
        this.orderService = orderService;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        springTemplateEngine.process("deliveryInfo.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ime = (String) req.getParameter("clientName");
        String adresa = (String) req.getParameter("clientAddress");
        String boja = (String) req.getSession().getAttribute("boja");
        String golemina = (String) req.getSession().getAttribute("size");
        req.getSession().setAttribute("cn",ime);
        req.getSession().setAttribute("ca",adresa);
        String str = (String) req.getParameter("datum");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        Order order = orderService.placeOrder(boja,golemina,dateTime);
        req.getSession().setAttribute("order",order);
        req.getSession().setAttribute("checkOrder",true);
        orderService.insert(order);
        resp.sendRedirect("/ConfirmationInfo");
    }
}
