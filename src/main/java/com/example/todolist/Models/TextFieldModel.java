package com.example.todolist.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TextFieldModel {


    private StringProperty text = new SimpleStringProperty();
    private int priority;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public StringProperty textProperty() {
        return text;
    }
    public TextFieldModel(StringProperty text) {
        this.text = text;
    }

    public String getText() {
        return text.get();
    }

    public void setText(String text) {
        this.text.set(text);
    }


}
