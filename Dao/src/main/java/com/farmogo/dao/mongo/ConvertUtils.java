package com.farmogo.dao.mongo;

import java.time.LocalDate;
import java.util.Date;

public class ConvertUtils {

    public static Date convert(LocalDate date){
        return java.sql.Date.valueOf(date);
    }
    public static LocalDate convert(Date date){
        return new java.sql.Date(date.getTime()).toLocalDate();
    }
}
