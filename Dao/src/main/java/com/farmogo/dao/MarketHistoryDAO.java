package com.farmogo.dao;

import com.farmono.model.MarketHistory;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class MarketHistoryDAO {


    public List<MarketHistory> getAll() {
        List<MarketHistory> MarketHistoryList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

        }
        return MarketHistoryList;
    }
}
