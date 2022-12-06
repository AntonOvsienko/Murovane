package com.sale.ticket.task.controller;

import com.sale.ticket.task.model.*;
import com.sale.ticket.task.service.BilletService;
import com.sale.ticket.task.service.RouteService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class LogicController {

    private final BilletService billetService;
    private final RouteService routeService;

    @RequestMapping ("/")
    public String showFirstPage() {
        return "index.html";
    }

    @GetMapping ("/route-list")
    public String showAllRoute(Model model) {
        List<Route> routeList = routeService.getAllRoute();
        model.addAttribute("routeList", routeList);
        model.addAttribute("billetPresent", false);

        return "route-list.html";
    }

    @PostMapping ("/buy-ticket")
    public String addNewTicket(@RequestParam ("id")Integer id, @RequestParam ("firstname") String firstname, @RequestParam ("surname") String surname, @RequestParam ("patronomic") String patronomic, Model model) {
        if (id != 0) {
            System.out.println(id);
            Billet billet = new Billet();
            billet.setFirstName(firstname);
            billet.setSurname(surname);
            billet.setPatronomic(patronomic);
            Integer billetId = billetService.createNewTicket(billet, id);
            model.addAttribute("billetIndex", billetId);
            model.addAttribute("billetPresent", true);
        }
        List<Route> routeList = routeService.getAllRoute();
        model.addAttribute("routeList", routeList);

        return "route-list.html";
    }
}
