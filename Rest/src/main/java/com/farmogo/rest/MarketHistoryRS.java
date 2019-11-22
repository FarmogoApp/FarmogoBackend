package com.farmogo.rest;

import com.farmogo.model.AnimalType;
import com.farmogo.services.MarketHistoryService;
import com.farmogo.model.MarketHistory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RequestScoped
@Path("marketHistory")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MarketHistoryRS {

    @Inject
    MarketHistoryService marketHistoryService;

    @GET
    public List<MarketHistory> getAll() {
        return marketHistoryService.getAll();
    }

    @GET
    @Path("test")
    public String test(){


        MarketHistory MH1 = new MarketHistory();
        AnimalType animalType = new AnimalType();
        animalType.setIcon("bull");
        animalType.setDescription("xxxxxxxx");
        animalType.setAnimalType("1");


        for (int i = 1; i < 11; i++) {
            MH1.setAnimalType(animalType);
            MH1.setDate(new Date());
            MH1.setPrice(new BigDecimal(1200));
            MH1.setAnimalTyupe(15);

        }



        return "ok";
    }
}
