package com.farmogo.dao;

import com.farmogo.model.MarketHistory;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class MarketHistoryDAO {


    public List<MarketHistory> getAll() {
        List<MarketHistory> marketHistoryList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MarketHistory marketHistory = new MarketHistory();
        }
        return marketHistoryList;
    }
}
