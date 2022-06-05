package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import javax.swing.*;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class GUI {
    private Text textHolder = new Text();
    private double oldHeight = 0;
    public void popUp2(String str){
        Stage popupwindow=new Stage();
        TextArea tf1 = new TextArea();
        tf1.setMaxSize(200,40);
        Label label1 = new Label("Write Leave Request");
        Button bt1=new Button("Send Request");
        HBox hbox1 = new HBox(label1, tf1);
        hbox1.setSpacing(20);
        VBox layout = new VBox(10);

        tf1.setWrapText(true);
        textHolder.textProperty().bind(tf1.textProperty());
        textHolder.layoutBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
                if (oldHeight != newValue.getHeight()) {
                    oldHeight = newValue.getHeight();
                    tf1.setPrefHeight(textHolder.getLayoutBounds().getHeight() + 20);
                    System.out.println(textHolder.getLayoutBounds().getHeight());
                }
            }
        });

        bt1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    String leave=tf1.getText();
                    String selectQuery = "INSERT INTO `request_leave` (`username`, `Leaves`) VALUES (?,?)";
                   PreparedStatement ps1 = MyConnection.getConnection().prepareStatement(selectQuery);
                    ps1 = MyConnection.getConnection().prepareStatement(selectQuery);
                    ps1.setString(1, str);
                    ps1.setString(2,leave);
                    if (ps1.executeUpdate() > 0) {
                        popUp("User Added to Present List");
                        popupwindow.close();
                    }

                } catch (Exception e) {
                    popUp(e.toString());
                }
            }


        });
        layout.getChildren().addAll(hbox1,bt1);

        layout.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout, 300, 250);

        popupwindow.setScene(scene1);

        popupwindow.show();
    }
    public void popUp(String str) {
        Stage popupwindow=new Stage();
        Label label1 = new Label(str);
        Button button1 = new Button("OK");


        button1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                popupwindow.close();
            }

        });


        VBox layout = new VBox(10);


        layout.getChildren().addAll(label1, button1);

        layout.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout, 300, 250);

        popupwindow.setScene(scene1);

        popupwindow.show();
    }

}