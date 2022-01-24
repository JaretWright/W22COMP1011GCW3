package com.example.w22comp1011gcw3;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class SpinnerChangeListener implements ChangeListener {
    @Override
    public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
        System.out.printf("old value: %s    new Value:  %s%n", oldValue,newValue);
    }
}
