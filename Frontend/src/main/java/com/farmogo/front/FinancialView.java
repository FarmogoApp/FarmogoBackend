package com.farmogo.front;


import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class FinancialView {

    public LineChartModel getEvolutionData(){
        LineChartModel model = new LineChartModel();

        ChartSeries price = new ChartSeries();
        price.setLabel("Price");
        price.set("2004", 400);
        price.set("2005", 410);
        price.set("2006", 450);
        price.set("2007", 467);
        price.set("2008", 455);
        price.set("2009", 440);
        price.set("2010", 470);
        price.set("2011", 480);
        price.set("2012", 495);
        price.set("2013", 485);
        price.set("2014", 505);
        price.set("2015", 514);
        price.set("2016", 560);
        price.set("2017", 590);
        price.set("2018", 540);
        price.set("2019", 530);

        Axis axisX = model.getAxis(AxisType.X);
        axisX.setMin("2003");
        axisX.setMax("2020");

        model.addSeries(price);
        return model;
    }
}
