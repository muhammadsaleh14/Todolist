package com.example.todolist.Controllers;

import com.example.todolist.Models.TextFieldModel;
import com.example.todolist.Models.TodoListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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

    public ObservableList<TextFieldModel> textFieldModels = FXCollections.observableArrayList();

    TodoListener todoList;

//    @FXML
//    protected void onHelloButtonClick() {
////        welcomeText.setText(textFieldModels.toString());
//        textFieldModels.forEach(model-> System.out.println(model.getText()));
//    }

    @FXML
    protected void saveTodo(){
        System.out.println(tfTodo.getText());
        if (tfTodo.getText() != ""){
        textFieldModels.add(new TextFieldModel(new SimpleStringProperty(tfTodo.getText())));
        tfTodo.setText("");
        }
    }
    @FXML
    protected void loadTodos(TextFieldModel tfModel){
        TextField textField = new TextField();
        textField.textProperty().bindBidirectional(tfModel.textProperty());
        todoList.vbtodoList.getChildren().add(textField);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Create text field models for each string in your list
        List<String> stringList = Arrays.asList("String 1", "String 2", "String 3"); // Replace with your actual list
        stringList.forEach(string -> textFieldModels.add(new TextFieldModel(new SimpleStringProperty(string))));
        textFieldModels.forEach(model -> loadTodos(model));
        todoList = new TodoListener(textFieldModels,vbtodoList);

    }


}