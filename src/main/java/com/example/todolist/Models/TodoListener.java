package com.example.todolist.Models;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class TodoListener {
    public ObservableList<TextFieldModel> textFieldModels = FXCollections.observableArrayList();
    public VBox vbtodoList = new VBox();

    public TodoListener(ObservableList<TextFieldModel> textFieldModels, VBox vbtodoList) {
        this.textFieldModels = textFieldModels;
        this.vbtodoList = vbtodoList;
        initializeTodoListener();
    }

    // Add the listener code here
    private void initializeTodoListener() {
        textFieldModels.addListener((ListChangeListener<TextFieldModel>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    change.getAddedSubList().forEach(model -> {
                        TextField textField = new TextField(model.getText());
                        textField.textProperty().bindBidirectional(model.textProperty());
                        // Apply text wrapping, styling, binding (as discussed earlier)
                        vbtodoList.getChildren().add(textField);
                    });
                } else if (change.wasRemoved()) {
                    // Remove corresponding TextFields from the VBox
                    change.getRemoved().forEach(model -> {
                        for (int i = 0; i < vbtodoList.getChildren().size(); i++) {
                            Node node = vbtodoList.getChildren().get(i);
                            if (node instanceof TextField && ((TextField) node).textProperty().isBound()) {
                                TextField boundTextField = (TextField) node;
                                if (boundTextField.textProperty().get().equals(model.getText())) {
                                    vbtodoList.getChildren().remove(i);
                                    break; // Exit inner loop as TextField is found
                                }
                            }
                        }
                    });
                }
            }
        });

    }
}