package com.sale.ticket.murovane.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sale.ticket.murovane.converters.impl.SettlementConverterImpl;
import com.sale.ticket.murovane.domen.model.SettlementOverview;
import com.sale.ticket.murovane.facade.SettlementFacade;
import com.sale.ticket.murovane.model.Settlement;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/settlements")
@AllArgsConstructor
public class StartController {

    private final SettlementFacade settlementFacade;
    private final SettlementConverterImpl settlementConverter;
    private final ObjectMapper objectMapper;

    @ResponseBody
    @GetMapping
    public ResponseEntity<String> getAllSettlements() throws JsonProcessingException {
        List<Settlement> settlementList = settlementFacade.getSettlementList();
        String json="";
        if (Objects.nonNull(settlementList) && !settlementList.isEmpty()){
        List<SettlementOverview> settlementOverviews = settlementList.stream()
                .map(settlementConverter::convert)
                .collect(Collectors.toList());
        json = objectMapper.writeValueAsString(settlementOverviews);
        }
        return ResponseEntity.ok(json);
    }

    @PostMapping
    public ResponseEntity<SettlementOverview> createSettlement(
            @RequestParam("quantity") @NonNull Integer quantity,
            @RequestParam("name") @NonNull String name) {
        Settlement settlement = settlementFacade.createSettlement(quantity, name);
        SettlementOverview settlementOverview = settlementConverter.convert(settlement);
        System.out.println(settlementOverview);
        return ResponseEntity.ok(settlementOverview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSettlement(@PathVariable("id") @NonNull Integer id) {
        settlementFacade.deleteSettlement(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SettlementOverview> getSettlement(@PathVariable("id") @NonNull Integer id) {
        Settlement settlement = settlementFacade.getSettlerById(id);
        if (settlement != null) {
            SettlementOverview settlementOverview = settlementConverter.convert(settlement);
            return ResponseEntity.ok(settlementOverview);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SettlementOverview> updateSettlement(@PathVariable("id") @NonNull Integer id) {
        List<String> messages = new ArrayList<>();
        Settlement settlement = settlementFacade.updateSettlerById(id, messages);
        if (settlement != null) {
            SettlementOverview settlementOverview = settlementConverter.convert(settlement);
            return ResponseEntity.ok(settlementOverview);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
