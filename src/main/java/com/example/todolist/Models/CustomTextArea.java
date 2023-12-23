package com.example.todolist.Models;

import javafx.scene.control.TextArea;

public class CustomTextArea extends TextArea {
    int id;



    public CustomTextArea( int id,String s) {
        super(s);
//        super.setId(id);
        this.id = id;
    }


}
