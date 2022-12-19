package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/user-orders")
public class UserOrdersController {

    public final OrderService orderService;

    public UserOrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getUserOrders(HttpServletRequest request,Model model) {
        request.getSession().setAttribute("lista",orderService.listAll());
        return "userOrders";
    }
    @PostMapping
    public String filteringOrders(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime datumOd,
                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime datumDo,
                                  Model model, HttpServletRequest request){
        List<Order> naracki = this.orderService.filterOrders(datumOd,datumDo);
        model.addAttribute("naracki",naracki);
        request.getSession().setAttribute("naracki",naracki);
        if (!naracki.isEmpty() && naracki != null) {
            request.getSession().setAttribute("checkOrder",false);
        }
        return "redirect:/user-orders";
    }
    @GetMapping("/sorting")
    public String sortingOrders(HttpServletRequest request,Model model) {
        List<Order> list = (List<Order>) request.getSession().getAttribute("lista");
        this.orderService.sortingOrders(list);
        request.getSession().setAttribute("lista",list);
        return "redirect:/user-orders";
    }
}
