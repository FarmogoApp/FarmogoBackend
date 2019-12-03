package com.farmogo.dao;
import com.farmogo.model.FarmBook;

import java.util.List;

public interface FarmBookDao {
    List<FarmBook> getAll();
    FarmBook save(FarmBook farmBook);
    void delete(FarmBook farmBook);
    FarmBook get(String id);
}
