package com.example.w22comp1011gcw3;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class CameraChartViewController implements Initializable {

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis categoryAxis;

    @FXML
    private NumberAxis numberAxis;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        barChart.getData().addAll(DBUtility.getUnitsSold());

        //create fake data to show how to add another data series
        XYChart.Series<String, Integer> unitsSold = new XYChart.Series<>();
        unitsSold.getData().addAll(new XYChart.Data<>("Canon-Rebel T7",150));
        unitsSold.getData().addAll(new XYChart.Data<>("fake 2",200));
        unitsSold.getData().addAll(new XYChart.Data<>("fake 3",290));

        barChart.getData().addAll(unitsSold);
    }
}
