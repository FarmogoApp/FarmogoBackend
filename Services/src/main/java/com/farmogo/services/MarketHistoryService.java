package com.farmogo.services;

import com.farmogo.dao.MarketHistoryDAO;
import com.farmono.model.MarketHistory;

import javax.inject.Inject;
import java.util.List;

public class MarketHistoryService {
    @Inject
    MarketHistoryDAO markethistoryDAO;

    public List<MarketHistory> getAll() {
        return markethistoryDAO.getAll();
    }
}
