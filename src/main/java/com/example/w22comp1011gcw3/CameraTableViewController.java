package com.example.w22comp1011gcw3;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CameraTableViewController implements Initializable {

    @FXML
    private TableView<Camera> tableView;

    @FXML
    private TableColumn<Camera, Integer> cameraIDColumn;

    @FXML
    private TableColumn<Camera, String> makeColumn;

    @FXML
    private TableColumn<Camera, String> modelColumn;

    @FXML
    private TableColumn<Camera, Integer> resolutionColumn;

    @FXML
    private TableColumn<Camera, Double> priceColumn;

    @FXML
    private TableColumn<Camera, Boolean> slrColumn;

    @FXML
    private TableColumn<Camera, Integer> unitsSoldColumn;

    @FXML
    private Label highestRevenueLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //configure the columns to connect with the Camera class get methods
        {
            cameraIDColumn.setCellValueFactory(new PropertyValueFactory<>("cameraID"));
            makeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));
            modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
            resolutionColumn.setCellValueFactory(new PropertyValueFactory<>("resolution"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            slrColumn.setCellValueFactory(new PropertyValueFactory<>("slr"));
            unitsSoldColumn.setCellValueFactory(new PropertyValueFactory<>("unitsSold"));
        }

        //add the camera objects from the DB to the tableview object
        tableView.getItems().addAll(DBUtility.getCamerasFromDB());
        highestRevenueLabel.setText("Highest Revenue = " + getHighestRevenueString());
    }

    private String getHighestRevenueString()
    {
        if (tableView.getItems().size() == 0)
            return "No cameras in the table";
        else
        {
            Camera highRev = tableView.getItems().get(0);
            for (Camera camera : tableView.getItems())
            {
                double highestRevenue = highRev.getPrice() * highRev.getUnitsSold();
                double cameraRevenue = camera.getPrice() * highRev.getUnitsSold();
                if (cameraRevenue>highestRevenue)
                    highRev = camera;
            }
            double highestRevenue = highRev.getPrice() * highRev.getUnitsSold();
            return (String.format("$%.2f, %s",highestRevenue, highRev));
        }
    }
}

