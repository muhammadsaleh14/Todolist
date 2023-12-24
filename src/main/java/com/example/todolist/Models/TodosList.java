package com.example.todolist.Models;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TodosList {
    public ObservableList<Todo> todos = FXCollections.observableArrayList();
    private VBox vbtodoList = new VBox();
    public int nextPriorityNumber = 1;

    public TodosList(ObservableList<Todo> todos, VBox vbtodoList) {
        this.todos = todos;
        this.vbtodoList = vbtodoList;
        vbtodoList.setSpacing(0);
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
                        textArea.setPadding(Insets.EMPTY);


                        HBox hBox = new HBox();
                        hBox.setAlignment(Pos.TOP_RIGHT);
                        hBox.setPrefHeight(0);
                        hBox.setPrefWidth(200);
                        hBox.setStyle("-fx-border-radius: 10; -fx-background-radius: 10;");
                        Button xButton = new Button("X");
                        xButton.setMnemonicParsing(false);
                        xButton.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");
                        xButton.setFont(Font.font(12));
                        xButton.setStyle("-fx-background-color: white;");
                        xButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
                            if (newValue) {
                                xButton.setStyle("-fx-background-color: red;");
                            } else {
                                // Reset to original background color
                                xButton.setStyle("-fx-background-color: white;");
                            }
                        });
                        xButton.setOnAction(event -> {
                            todos.removeIf(obj -> obj.getId() == model.getId());
                            // Code to be executed when the button is clicked
                        });

                        hBox.getChildren().add(xButton);
                        hBox.setPadding(Insets.EMPTY);
                        hBox.setSpacing(0);

                        VBox vbtodoContainer = new VBox();
                        vbtodoContainer.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(9), BorderWidths.DEFAULT)));
                        vbtodoContainer.setPadding(new Insets(10));
//                        vbtodoContainer.setMargin();
                        vbtodoContainer.getChildren().addAll(xButton,textArea);

                        vbtodoList.setVgrow(hBox, Priority.NEVER); // Prevent vertical growth
//                        AnchorPane.setRightAnchor(xButton, 0.0); // Adjust right margin as needed

                        vbtodoList.getChildren().add(vbtodoContainer);

// Adjust font size as needed
                        // Apply text wrapping, styling, binding (as discussed earlier)
//                        vbtodoList.getChildren().add(textArea);
                    });
                } else if (change.wasRemoved()) {
                    // Remove corresponding TextFields from the VBox
                    change.getRemoved().forEach(model -> {
                        for (int i = 0; i < vbtodoList.getChildren().size(); i++) {
                            Node node = vbtodoList.getChildren().get(i);
                            VBox vbtodoContainer = (VBox) node;

                            Node node0 = vbtodoContainer.getChildren().get(1);
                            TextArea textArea = (TextArea) node0;
                            if (textArea.getId().equals(model.getStringId())) {
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