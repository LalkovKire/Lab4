package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/balloons")
public class BalloonController {
    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
    }
    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model,HttpServletRequest request){
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        request.getSession().setAttribute("something","true");
        model.addAttribute("prebaraj",balloonService.listAll());
        return "listBalloons";
    }

    @PostMapping("/add")
    public String saveBalloon(@RequestParam String name,@RequestParam String description,@RequestParam Long manufacturer){
        this.balloonService.save(name,description,manufacturer);
        return "redirect:/balloons";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id) {
        balloonService.deleteByID(id);
        return "redirect:/balloons";
    }
    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addProductPage(Model model) {
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        return "add-balloon";
    }
    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if(this.balloonService.findById(id).isPresent()){
            Balloon product = this.balloonService.findById(id).get();
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("balloon", product);
            return "add-balloon";
        }
        return "redirect:/balloons?error=ProductNotFound";
    }
    @PostMapping
    public String continueTheOrder(HttpServletRequest request, HttpServletResponse response){
        String boja = request.getParameter("color");
        request.getSession().setAttribute("boja",boja);
        return "redirect:/selectBalloon";
    }
}
