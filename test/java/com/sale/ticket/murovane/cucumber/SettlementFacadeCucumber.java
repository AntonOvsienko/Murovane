package com.sale.ticket.murovane.cucumber;

import com.sale.ticket.murovane.cucumber.dto.IndividualDataGenerator;
import com.sale.ticket.murovane.facade.impl.SettlementFacadeImpl;
import com.sale.ticket.murovane.model.Settlement;
import com.sale.ticket.murovane.service.*;
import com.sale.ticket.murovane.service.impl.BurnServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class SettlementFacadeCucumber {

    private Settlement settlement = new Settlement();
    private IndividualDataGenerator individualDataGenerator= new IndividualDataGenerator();
    private ManNameService manNameService;
    private ManService manService;
    private WomanService womanService;
    private WomanNameService womanNameService;
    private SurnameService surnameService;
    private SettlementService settlementService;
    private DeathService deathService;
    private BurnServiceImpl burnService;
    private SettlementFacadeImpl settlementFacade;

    @Given("initialize before create settlement")
    public void initializeBeforeCreateSettlement() {
        deathService = mock(DeathService.class);
        settlementService = mock(SettlementService.class);
        surnameService = mock(SurnameService.class);
        womanNameService = mock(WomanNameService.class);
        womanService = mock(WomanService.class);
        manService = mock(ManService.class);
        manNameService = mock(ManNameService.class);

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

    @When("creating new settlement with {string} and number of settlers is {int}")
    public void creatingNewSettlementWithAndNumberOfSettlersIs(String name, Integer count) {
        settlement = settlementFacade.createSettlement(count, name);
    }

    @Then("return new settlement name is {string} number of settlers is {int}")
    public void returnNewSettlementNameIsNumberOfSettlersIs(String name, Integer count) {
        assertEquals((settlement.getWomen().size() + settlement.getMen().size()), count);
        assertEquals(settlement.getName(), name);
        for (int i = 0; i < settlement.getMen().size(); i++) {
            assertNotNull(settlement.getMen().get(i).getName());
            assertNotNull(settlement.getMen().get(i).getSurname());
        }
        for (int i = 0; i < settlement.getWomen().size(); i++) {
            assertNotNull(settlement.getWomen().get(i).getName());
            assertNotNull(settlement.getWomen().get(i).getSurname());
        }
    }
}
