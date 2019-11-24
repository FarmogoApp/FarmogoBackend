package com.farmogo.services;

import com.farmogo.model.MarketHistory;
import com.farmogo.dao.MarketHistoryDao;


import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class MarketHistoryService {

    @Inject
    MarketHistoryDao markethistoryDao;

    public List<MarketHistory> getAll() {
        return markethistoryDao.getAll();
    }


    public MarketHistory save(MarketHistory marketHistory) {
        return markethistoryDao.save(marketHistory);
    }
}
