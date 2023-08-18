package com.sale.ticket.murovane.cucumber;

import com.sale.ticket.murovane.converters.SettlementConverter;
import com.sale.ticket.murovane.converters.impl.SettlementConverterImpl;
import com.sale.ticket.murovane.cucumber.dto.IndividualDataGenerator;
import com.sale.ticket.murovane.cucumber.dto.SettlementDataGenerator;
import com.sale.ticket.murovane.facade.impl.SettlementFacadeImpl;
import com.sale.ticket.murovane.model.Settlement;
import com.sale.ticket.murovane.repository.ManRepository;
import com.sale.ticket.murovane.repository.WomanRepository;
import com.sale.ticket.murovane.service.*;
import com.sale.ticket.murovane.service.impl.BurnServiceImpl;
import com.sale.ticket.murovane.service.impl.DeathServiceImpl;
import com.sale.ticket.murovane.service.impl.ManServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeathServiceCucumber {

    private Settlement settlement = new Settlement();
    private SettlementDataGenerator settlementDataGenerator = new SettlementDataGenerator();
    private SettlementConverter settlementConverter = new SettlementConverterImpl();
    private DeathService deathService;

    private int finalCount = 0;
    private List<Integer> list = new ArrayList<>();

    @Given("create settlement with {int} count with {string}")
    public void createSettlementWithPeopleCountWith(Integer count, String string) {
        list = convertStringToInt(string);

        deathService = new DeathServiceImpl(settlementConverter);
        settlement = settlementDataGenerator.createSettlementForDeath(count, list);
    }

    @When("update settlement death {int} count")
    public void updateSettlementDeathCyclesCount(Integer cycles) {
        LocalDate data = settlement.getSettlementTime().plusYears(cycles);
        settlement.setSettlementTime(data);
        finalCount = deathService.dead(settlement, finalCount);
    }

    @Then("return updated settlement with {int} death people")
    public void returnUpdatedSettlementWithDeathDeathPeople(Integer count) {
        assertEquals(finalCount, count);
    }

    private List<Integer> convertStringToInt(String string) {
        List<Integer> list1 = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            list1.add(Integer.valueOf(matcher.group()));
        }
        return list1;
    }
}
