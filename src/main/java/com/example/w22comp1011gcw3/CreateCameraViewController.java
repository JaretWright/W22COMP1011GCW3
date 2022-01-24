package com.example.w22comp1011gcw3;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateCameraViewController implements Initializable {

    @FXML
    private ComboBox<String> brandComboBox;

    @FXML
    private TextField modelTextField;

    @FXML
    private Spinner<Integer> resolutionSpinner;

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

        //configure the spinner to only accept realistic camera resolutions
        //We will use a spinner value factory
        //the constructor is taking the minimum, maximum, default and step/increment
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(5,100,20,5);
        resolutionSpinner.setValueFactory(spinnerValueFactory);
        resolutionSpinner.setEditable(true);
        TextField spinnerTextField = resolutionSpinner.getEditor();


        //creating a custom ChangeListener class wasn't very efficient - extra files and
        //didn't allow us to access JavaFX objects that were private in the controller
//        SpinnerChangeListener scl = new SpinnerChangeListener();
//        spinnerTextField.textProperty().addListener(scl);

        //We can create an anonymous inner class
//        spinnerTextField.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
//                try{
//                    Integer.parseInt(newValue);
//                    msgLabel.setText("");
//                }catch (Exception e)
//                {
//                    msgLabel.setText("Only whole numbers allowed for the resolution");
//                    spinnerTextField.setText(oldValue);
//                }
//            }
//        });

        //Let's use a lamdba expression
        spinnerTextField.textProperty().addListener((obs, oldValue, newValue) -> {
            try{
                Integer.parseInt(newValue);
            }
            catch (Exception e)
            {
                spinnerTextField.setText(oldValue);
            }
        });

        //update the price text field such that it will only accept a double
        priceTextField.textProperty().addListener((obs, oldValue, newValue)->{
            try{Double.parseDouble(newValue);}
            catch (Exception e){priceTextField.setText(oldValue);}
        });
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
            res = resolutionSpinner.getValue();
            price = Double.parseDouble(priceTextField.getText());
        }
        catch (Exception e)
        {
            msgLabel.setText("Resolution & price must be numbers only");
        }
        if (res != -1 && price != -1)
        {
            try {
                Camera newCamera = new Camera(make, model, res, slr, price);
                msgLabel.setText(newCamera.toString());
            }catch (IllegalArgumentException e)
            {
                msgLabel.setText(e.getMessage());
            }

        }
    }
}
