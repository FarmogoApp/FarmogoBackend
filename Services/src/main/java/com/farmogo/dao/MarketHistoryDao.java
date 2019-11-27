package com.farmogo.dao;

import com.farmogo.model.MarketHistory;

import java.util.List;

public interface MarketHistoryDao {
    List<MarketHistory> getAll();

    MarketHistory save(MarketHistory marketHistory);
}
