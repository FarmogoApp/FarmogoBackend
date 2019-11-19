package com.farmogo.services;

import com.farmogo.model.MarketHistory;
import com.farmogo.dao.MarketHistoryDao;


import javax.inject.Inject;
import java.util.List;

public class MarketHistoryService {

    @Inject
    MarketHistoryDao markethistoryDao;

    public List<MarketHistory> getAll() {

        return markethistoryDao.getAll();
    }


}
