package com.example.todolist.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.UUID;

public class Todo {

    private UUID id;
    // Getter for the ID


    private StringProperty text = new SimpleStringProperty();
    private int priority;
    public Todo(StringProperty text) {
        this.text = text;
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getStringId(){
        return id.toString();
    }
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public StringProperty textProperty() {
        return text;
    }

    public String getText() {
        return text.get();
    }

    public void setText(String text) {
        this.text.set(text);
    }


}
