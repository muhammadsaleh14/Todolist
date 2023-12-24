package com.example.todolist.Controllers;

import com.example.todolist.Models.Todo;
import com.example.todolist.Models.TodosList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Label welcomeText;

    @FXML
    private TextField tfTodo;

    @FXML
    private VBox vbtodoList;

    public ObservableList<Todo> tmp = FXCollections.observableArrayList();
    TodosList todoList;

//    @FXML
//    protected void onHelloButtonClick() {
////        welcomeText.setText(textFieldModels.toString());
//        textFieldModels.forEach(model-> System.out.println(model.getText()));
//    }

    @FXML
    protected void saveTodo(){
        todoList.todos.forEach(model -> System.out.println(model.getText()));
        System.out.println();
        if (tfTodo.getText() != ""){
        todoList.todos.add(new Todo(new SimpleStringProperty(tfTodo.getText())));
        tfTodo.setText("");
        }
        todoList.todos.forEach(model-> System.out.println(model.getText()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Create text field models for each string in your list
        List<String> stringList = Arrays.asList("String 1", "String 2", "String 3"); // Replace with your actual list

        todoList = new TodosList(tmp , vbtodoList);

        stringList.forEach(string -> todoList.todos.add(new Todo(new SimpleStringProperty(string))));
    }


}