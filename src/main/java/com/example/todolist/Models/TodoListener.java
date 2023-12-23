package com.example.todolist.Models;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import static javafx.scene.control.PopupControl.USE_PREF_SIZE;

public class TodoListener {
    public ObservableList<TextFieldModel> textFieldModels = FXCollections.observableArrayList();
    private VBox vbtodoList = new VBox();

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
                        TextArea textArea = new TextArea(model.getText());
                        textArea.textProperty().bindBidirectional(model.textProperty());
                        textArea.setWrapText(true);
                        textArea.setPrefRowCount(1);
                        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
                            int currentRowCount = textArea.getParagraphs().size();
                            if (currentRowCount <= 4){
                            textArea.setPrefRowCount(currentRowCount);
                            }
                        });
                        // Apply text wrapping, styling, binding (as discussed earlier)
                        vbtodoList.getChildren().add(textArea);
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