package com.sale.ticket.task.controller;

import com.sale.ticket.task.converters.SettlementConverter;
import com.sale.ticket.task.domen.model.SettlementOverview;
import com.sale.ticket.task.facade.SettlementFacade;
import com.sale.ticket.task.model.*;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class StartController {

    private final SettlementFacade settlementFacade;
    private final SettlementConverter settlementConverter;

    @RequestMapping ("/")
    public String showFirstPage(Model model) {
        List<Settlement> settlementList = settlementFacade.getSettlementList();
        if (settlementList != null) {
            model.addAttribute("settlementList", settlementList);
        } else {
            model.addAttribute("settlementList", null);
        }

        return "index.html";
    }
    @PostMapping ("/create-settlement")
    public String createSettlement(Model model, @RequestParam ("quantity") @NonNull Integer quantity, @RequestParam ("name") @NonNull String name) {
        Settlement settlement = settlementFacade.createSettlement(quantity, name);
        List<Settlement> settlementList = settlementFacade.getSettlementList();
        model.addAttribute("settlementList", settlementList);
        SettlementOverview settlementOverview = settlementConverter.convert(settlement);
        model.addAttribute("settlement", settlementOverview);

        return "index.html";
    }

    @PostMapping ("/delete-settlement")
    public String deleteSettlement(Model model, @RequestParam ("id") @NonNull String id) {
        settlementFacade.deleteSettlement(Integer.valueOf(id));
        List<Settlement> settlementList = settlementFacade.getSettlementList();
        if (settlementList != null) {
            model.addAttribute("settlementList", settlementList);
        } else {
            model.addAttribute("settlementList", null);
        }

        return "index.html";
    }

    @GetMapping ("/choose-settlement")
    public String chooseSettlement(Model model, @RequestParam ("id") @NonNull Integer id) {
        Settlement settlement = settlementFacade.getSettlerById(id);
        if (settlement != null) {
            SettlementOverview settlementOverview = settlementConverter.convert(settlement);
            model.addAttribute("settlement", settlementOverview);
        } else {
            model.addAttribute("settlement", null);
        }

        return "settlement.html";
    }

    @PostMapping ("/update-settlement")
    public String updateSettlement(Model model, @RequestParam ("id") @NonNull Integer id) {
        Settlement settlement = settlementFacade.updateSettlerById(id);
        if (settlement != null) {
            SettlementOverview settlementOverview = settlementConverter.convert(settlement);
            model.addAttribute("settlement", settlementOverview);
        } else {
            model.addAttribute("settlement", null);
        }

        return "settlement.html";
    }

}
