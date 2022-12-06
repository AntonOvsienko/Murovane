package com.sale.ticket.task.controller;

import com.sale.ticket.task.model.*;
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
public class LogicController {

    @RequestMapping ("/")
    public String showFirstPage() {
        return "index.html";
    }

    @GetMapping ("/route-list")
    public String showAllRoute(Model model) {
        City city1 = new City("Dnepr");
        City city2 = new City("Kharkov");
        Date date = new Date(2022, 9, 31);
        Route route = new Route(1, city1, city2, date, 600, 30);
        List<Route> routeList = new ArrayList<>();
        routeList.add(route);
        model.addAttribute("routeList", routeList);
        model.addAttribute("billetPresent", false);

        return "route-list.html";
    }

    @PostMapping ("/buy-ticket")
    public String addNewTicket(@RequestParam ("id") Integer id, @RequestParam ("firstname") String firstname, @RequestParam ("surname") String surname, @RequestParam ("patronomic") String patronomic, Model model) {
        System.out.println(id);
        Payment payment = new Payment();
        Billet billet = new Billet();
        billet.setId(id);
        payment.setBillet(billet);
        payment.setFirstName(firstname);
        payment.setSurname(surname);
        payment.setPatronomic(patronomic);
        payment.setCount(1);
        payment.setStatus(new PaymentStatus("NEW"));

        model.addAttribute("billetIndex", payment.getBillet().getId());
        model.addAttribute("billetPresent", true);

        return "route-list.html";
    }
}
