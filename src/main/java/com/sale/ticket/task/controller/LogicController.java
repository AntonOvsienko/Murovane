package com.sale.ticket.task.controller;

import com.sale.ticket.task.model.City;
import com.sale.ticket.task.model.Route;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class LogicController {

    @RequestMapping("/")
    public String showFirstPage() {
        return "index.jsp";
    }

    @GetMapping("/route-list")
    public String showAllRoute(Model model) {
        City city1 = new City("Dnepr");
        System.out.println(1);
        City city2 = new City("Kharkov");
        System.out.println(2);
        Date date = new Date(2022, 9, 31);
        System.out.println(3);
        Route route = new Route(city1, city2, date, 600, 30);
        System.out.println(4);
        List<Route> routeList = new ArrayList<>();
        System.out.println(5);
        routeList.add(route);
        System.out.println(6);
        model.addAttribute("routeList", routeList);
        System.out.println(7);

        return "route-list.jsp";
    }
}
