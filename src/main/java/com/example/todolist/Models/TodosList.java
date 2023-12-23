package com.example.todolist.Models;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class TodosList {
    public ObservableList<Todo> todos = FXCollections.observableArrayList();
    private VBox vbtodoList = new VBox();

    public TodosList(ObservableList<Todo> todos, VBox vbtodoList) {
        this.todos = todos;
        this.vbtodoList = vbtodoList;
        initializeTodoListener();
    }

    // Add the listener code here
    private void initializeTodoListener() {
        todos.addListener((ListChangeListener<Todo>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    change.getAddedSubList().forEach(model -> {
                        TextArea textArea = new TextArea(model.getText());
                        textArea.setId(model.getStringId());
                        textArea.textProperty().bindBidirectional(model.textProperty());
                        textArea.setWrapText(true);
                        textArea.setPrefRowCount(1);
                        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
                            int currentRowCount = textArea.getParagraphs().size();
                            if (currentRowCount <= 4){
                            textArea.setPrefRowCount(currentRowCount);
                            }
                        });
                        textArea.focusedProperty().addListener((observable, oldValue, newValue) -> {
                            if (!newValue && model.getText().isEmpty()) {
                                // Delete the TextArea when focus is lost and it's empty
                                todos.removeIf(obj -> obj.getId() == model.getId());

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
                                    System.out.println(model.getText()+ model.getId());

                                TextArea boundTextField = (TextArea) node;
                                if (boundTextField.getId().equals(model.getStringId())) {
                                    vbtodoList.getChildren().remove(i);
                                    break; // Exit inner loop as TextField is found
                                }
                        }
                    });
                }
            }
        });

    }


}