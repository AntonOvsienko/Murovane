package com.sale.ticket.murovane.cucumber;

import com.sale.ticket.murovane.converters.SettlementConverter;
import com.sale.ticket.murovane.converters.impl.SettlementConverterImpl;
import com.sale.ticket.murovane.cucumber.dto.IndividualDataGenerator;
import com.sale.ticket.murovane.cucumber.dto.SettlementDataGenerator;
import com.sale.ticket.murovane.facade.impl.SettlementFacadeImpl;
import com.sale.ticket.murovane.model.Man;
import com.sale.ticket.murovane.model.Settlement;
import com.sale.ticket.murovane.model.Woman;
import com.sale.ticket.murovane.repository.ManRepository;
import com.sale.ticket.murovane.repository.WomanRepository;
import com.sale.ticket.murovane.service.*;
import com.sale.ticket.murovane.service.impl.BurnServiceImpl;
import com.sale.ticket.murovane.service.impl.ManServiceImpl;
import com.sale.ticket.murovane.service.impl.WomanServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class BurnServiceCucumber {

    private Settlement settlement = new Settlement();
    private IndividualDataGenerator individualDataGenerator = new IndividualDataGenerator();
    private SettlementDataGenerator settlementDataGenerator = new SettlementDataGenerator();
    private SettlementConverter settlementConverter = new SettlementConverterImpl();
    private ManNameService manNameService;
    private ManService manService;
    private ManRepository manRepository;
    private WomanRepository womanRepository;
    private WomanService womanService;
    private WomanNameService womanNameService;
    private SurnameService surnameService;
    private SettlementService settlementService;
    private BurnServiceImpl burnService;
    private SettlementFacadeImpl settlementFacade;
    private DeathService deathService;
    private int finalCount = 0;

    @Given("create settlement with {int} people")
    public void createSettlementWithCountPeople(Integer count) {
        settlement = settlementDataGenerator.createSettlementForMarried(count);
        manRepository = mock(ManRepository.class);
        womanRepository = mock(WomanRepository.class);
        manService = new ManServiceImpl(manRepository, settlementConverter);
        womanService = new WomanServiceImpl(womanRepository, settlementConverter);
        burnService = new BurnServiceImpl(manService, manNameService, womanService, womanNameService, surnameService, settlementService);
    }

    @When("update settlement married by {int}")
    public void updateSettlementByCycles(Integer cycles) {
        for (int i = 0; i < cycles; i++) {
            burnService.married(settlement, 0);
        }
    }

    @Then("return updated settlement {int} with married people")
    public void returnUpdatedSettlementWithMarriedPeople(Integer count) {
        assertEquals(count, settlement.getMen().size() + settlement.getWomen().size());
        Long husband = settlement.getMen().stream()
                .filter(man -> Objects.nonNull(man.getWife()))
                .count();
        Long wife = settlement.getWomen().stream()
                .filter(woman -> Objects.nonNull(woman.getHusband()))
                .count();
        assertEquals(husband, wife);

        for (Man man : settlement.getMen()) {
            int husbands = Math.toIntExact(settlement.getWomen().stream()
                    .filter(woman -> Objects.nonNull(woman.getHusband()))
                    .filter(woman -> woman.getHusband().equals(man))
                    .count());
            assertTrue(husbands < 2);
        }
        for (Woman woman : settlement.getWomen()) {
            int wifes = Math.toIntExact(settlement.getMen().stream()
                    .filter(man -> Objects.nonNull(man.getWife()))
                    .filter(man -> man.getWife().equals(woman))
                    .count());
            assertTrue(wifes < 2);
        }
    }

    @Given("create settlement with {int} and {int} count")
    public void createSettlementWithWifeAndNowifeCount(Integer wife, Integer nowife) {
        settlement = settlementDataGenerator.createSettlementForPregnant(wife, nowife);
        manRepository = mock(ManRepository.class);
        womanRepository = mock(WomanRepository.class);
        manService = new ManServiceImpl(manRepository, settlementConverter);
        womanService = new WomanServiceImpl(womanRepository, settlementConverter);
        burnService = new BurnServiceImpl(manService, manNameService, womanService, womanNameService, surnameService, settlementService);
    }

    @When("update settlement pregnants {int} count")
    public void updateSettlementPregnantsCyclesCount(Integer cycles) {
        for (int i = 0; i < cycles; i++) {
            burnService.pregnant(settlement, 0);
        }
    }

    @Then("return updated settlement where {int} didn't exist pregnant")
    public void returnUpdatedSettlementWhereNowifeDidnTExistPregnant(Integer nowife) {
        assertTrue(settlement.getWomen().stream()
                .filter(woman -> Objects.isNull(woman.getHusband()))
                .allMatch(woman -> woman.getPregnant().equals(false)));
        assertTrue(settlement.getWomen().stream()
                .filter(woman -> woman.getCountBaby() == 0)
                .filter(woman -> woman.getPregnant().equals(Boolean.FALSE))
                .count() >= nowife);
    }

    @Given("create settlement with {int} pregnant and {int} non-pregnant count")
    public void createSettlementWithPregnantAndNonPregnantCount(int arg0, int arg1) {
        settlement = settlementDataGenerator.createSettlementForChildBirth(arg0,arg1);
        manRepository = mock(ManRepository.class);
        womanRepository = mock(WomanRepository.class);
        deathService = mock(DeathService.class);
        settlementService = mock(SettlementService.class);
        surnameService = mock(SurnameService.class);
        womanNameService = mock(WomanNameService.class);
        manNameService = mock(ManNameService.class);
        manService = new ManServiceImpl(manRepository, settlementConverter);
        womanService = new WomanServiceImpl(womanRepository, settlementConverter);

        Mockito.when(manNameService.getListManName())
                .thenReturn(individualDataGenerator.createListManName());
        Mockito.when(womanNameService.getListWomanName())
                .thenReturn(individualDataGenerator.createListWomanName());
        Mockito.when(surnameService.getListSurname())
                .thenReturn(individualDataGenerator.createListSurname());
        Mockito.when(settlementService.createSettler(settlement)).thenReturn(settlement);
        burnService = new BurnServiceImpl(manService, manNameService, womanService, womanNameService, surnameService, settlementService);
        settlementFacade = new SettlementFacadeImpl(settlementService, burnService, deathService);
    }

    @When("update settlement childBirth {int} count")
    public void updateSettlementChildBirthCount(int cycles) {
        for (int i = 0; i < cycles; i++) {
            finalCount = burnService.childbirth(settlement, finalCount);
        }
    }

    @Then("return updated settlement with {int} child")
    public void returnUpdatedSettlementWithChild(int child) {
        assertEquals(finalCount,child);
    }
}
