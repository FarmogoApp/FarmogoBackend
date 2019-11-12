package com.farmogo.rest;

import com.farmogo.services.MarketHistoryService;
import com.farmono.model.MarketHistory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RequestScoped
@Path("marketHistory")
public class MarketHistoryRS {
    @Inject
    MarketHistoryService marketHistoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MarketHistory> getAll() {
        return marketHistoryService.getAll();
    }
}
