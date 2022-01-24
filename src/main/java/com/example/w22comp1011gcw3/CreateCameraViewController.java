package com.example.w22comp1011gcw3;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateCameraViewController implements Initializable {

    @FXML
    private ComboBox<String> brandComboBox;

    @FXML
    private TextField modelTextField;

    @FXML
    private TextField resolutionTextField;

    @FXML
    private CheckBox slrCheckBox;

    @FXML
    private TextField priceTextField;

    @FXML
    private Label msgLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        msgLabel.setText("");
        brandComboBox.getItems().addAll(Camera.getManufacturers());
    }

    @FXML
    private void createCamera()
    {

        String make = brandComboBox.getSelectionModel().getSelectedItem();
        String model = modelTextField.getText();
        boolean slr = slrCheckBox.isSelected();
        int res = -1;
        double price = -1;
        try {
            res = Integer.parseInt(resolutionTextField.getText());
            price = Double.parseDouble(priceTextField.getText());
        }
        catch (Exception e)
        {
            msgLabel.setText("Resolution & price must be numbers only");
        }
        if (res != -1 && price != -1)
        {
            Camera newCamera = new Camera(make, model, res, slr, price);
            msgLabel.setText(newCamera.toString());
        }
    }
}
