package com.example.todolist.Controllers;

import com.example.todolist.Models.TextFieldModel;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;

public class TextFieldListCell extends ListCell<TextFieldModel> {

    @Override
    protected void updateItem(TextFieldModel item, boolean empty)

    {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            TextField textField = new TextField(item.getText());
            textField.setStyle("-fx-text-fill: red;");
            textField.textProperty().bindBidirectional(item.textProperty());
            setGraphic(textField);
        }

    }
}