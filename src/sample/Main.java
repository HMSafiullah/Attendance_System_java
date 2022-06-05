package sample;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.*;

import com.mysql.cj.MysqlConnection;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Callback;
import javafx.scene.control.TableColumn.CellDataFeatures;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.FileInputStream;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;
import java.util.logging.Level;
import java.sql.PreparedStatement;

public class Main extends Application {
    private ObservableList<ObservableList> data;
    private ObservableList<ObservableList> data1;
    private TableView tableview;
    private TableView tableview1;
    GUI gui=new GUI();
    java.util.Date date=new java.util.Date();
    java.sql.Date sqldate=new java.sql.Date(date.getTime());
    public void start(Stage primaryStage) {
        try {
            Text text = new Text("User Panel");
            text.setFill(Color.BLACK);
            Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 25);
            text.setFont(font);
            Button bt1 = new Button("Login");
            Button bt2 = new Button("Registration");
          //  Button bt3=  new Button("Back");
            HBox hbox = new HBox(bt1, bt2);
            hbox.setAlignment(Pos.BOTTOM_CENTER);
            hbox.setSpacing(40);
            FileInputStream input = new FileInputStream("4.jpg");
            // create a image
            Image image = new Image(input);
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            ImageViewPane viewPane = new ImageViewPane(imageView);
            // create a background image
            BackgroundImage backgroundimage = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            StackPane pane = new StackPane(viewPane);
            pane.getChildren().addAll(hbox, text);
            bt1.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent event) {
                    userPanelLogin(primaryStage);
                }
            });
            bt2.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent event) {
                    userRegistration(primaryStage);
                }
            });
           // bt3.setOnAction(new EventHandler<ActionEvent>() {

             //   public void handle(ActionEvent event) {
               //     start(primaryStage);
                //}
          //  });
            Scene scene = new Scene(pane);
            primaryStage.setTitle("Student Mangaement System");
            primaryStage.setWidth(600);
            primaryStage.setHeight(420);
            primaryStage.setScene(scene);
            primaryStage.setResizable(true);
            primaryStage.show();
        }catch (Exception e){
            gui.popUp(e.toString());
        }
    }
    public String userPanelLogin(Stage primaryStage){
        TextField tf1 = new TextField();
        TextField tf2 = new TextField();
        try {
            Text txt = new Text("User Login Panel");
            txt.setFill(Color.BLACK);
            Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 25);
            txt.setFont(font);
            Button bt2=new Button("Back");
            Button bt1=new Button("Login");
            Label label1 = new Label("User Name");
            Label label2 = new Label("Password");
            HBox hbox1 = new HBox(label1, tf1);
            HBox hbox2 = new HBox(label2, tf2);
            HBox hbox3 = new HBox(hbox1, hbox2,bt1,bt2);
            hbox3.setAlignment(Pos.BOTTOM_CENTER);
            hbox2.setAlignment(Pos.BOTTOM_CENTER);
            hbox1.setAlignment(Pos.BOTTOM_CENTER);
            FileInputStream input = new FileInputStream("4.jpg");
            // create a image
            Image image = new Image(input);
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            ImageViewPane viewPane = new ImageViewPane(imageView);
            // create a background image
            BackgroundImage backgroundimage = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            StackPane pane = new StackPane(viewPane);
            pane.getChildren().addAll(hbox3, txt);
            bt2.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent event) {
                    start(primaryStage);
                }
            });
            bt1.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent event) {
                    PreparedStatement ps1,ps2;
                    ResultSet rs1,rs2;
                    String userName=tf1.getText();
                    String userPassword=tf2.getText();
                    try {
                        String query1 = "SELECT * FROM `users` WHERE `username`=? AND `password`=?";
                        String query2 = "SELECT * FROM `admin` WHERE `username`=? AND `password`=?";
                        ps1 = MyConnection.getConnection().prepareStatement(query1);
                        ps2 = MyConnection.getConnection().prepareStatement(query2);
                        ps1.setString(1, userName);
                        ps1.setString(2, userPassword);
                        ps2.setString(1, userName);
                        ps2.setString(2, userPassword);
                        rs1 = ps1.executeQuery();
                        rs2 = ps2.executeQuery();
                        if (rs1.next()) {
                            userPanel(primaryStage,userName.toString());
                        }
                        if (rs2.next()){
                            adminPanel(primaryStage);
                        }
                    }catch (Exception e){
                        gui.popUp(e.toString());
                    }
                }
            });
            Scene scene = new Scene(pane);
            primaryStage.setTitle("Student Mangaement System");
            primaryStage.setWidth(600);
            primaryStage.setHeight(420);
            primaryStage.setScene(scene);
            primaryStage.setResizable(true);
            primaryStage.show();
        } catch (Exception e) {
            gui.popUp(e.toString());
        }
        String userName=tf1.getText();
        return userName;
    }
    public void userRegistration(Stage primaryStage){
        try {
            Text txt = new Text("User Registration Panel");
            txt.setFill(Color.BLACK);
            Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 25);
            txt.setFont(font);
            Button bt2=new Button("Back");
            Button bt1=new Button("Register");
            Label label1 = new Label("User Name");
            Label label2 = new Label("Password");
            Label label3 = new Label("Contact No.");
            Label label4= new Label("Email");
            TextField tf1 = new TextField();
            TextField tf2 = new TextField();
            TextField tf3 = new TextField();
            TextField tf4 = new TextField();
            HBox hbox1 = new HBox(label1, tf1);
            HBox hbox2 = new HBox(label2, tf2);
            hbox2.setSpacing(10);
            HBox hbox3 = new HBox(label3, tf3);
            HBox hbox4 = new HBox(label4, tf4);
            hbox4.setSpacing(30);
            HBox hbox6 = new HBox(bt1,bt2);
            hbox6.setSpacing(20);
            VBox vbox = new VBox(hbox1, hbox2,hbox3,hbox4,hbox6);
            vbox.setAlignment(Pos.TOP_LEFT);
            hbox1.setAlignment(Pos.TOP_LEFT);
            hbox2.setAlignment(Pos.TOP_LEFT);
            hbox3.setAlignment(Pos.TOP_LEFT);
            hbox4.setAlignment(Pos.TOP_LEFT);
            //hbox5.setAlignment(Pos.TOP_LEFT);
            hbox6.setAlignment(Pos.TOP_LEFT);
            FileInputStream input = new FileInputStream("4.jpg");
            // create a image
            Image image = new Image(input);
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            ImageViewPane viewPane = new ImageViewPane(imageView);
            // create a background image
            BackgroundImage backgroundimage = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            StackPane pane = new StackPane(viewPane);
            pane.getChildren().addAll(vbox, txt);
            bt2.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent event) {
                    start(primaryStage);
                }
            });
            bt1.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent event) {
                    String userName=tf1.getText();
                    String userPassword=tf2.getText();
                    String userContact=tf3.getText();
                    String userEmail=tf4.getText();
                    if (userName.equals("")){
                        gui.popUp("Add a user");
                    }
                    else if(userPassword.equals("")){
                        gui.popUp("Add a Password");
                    }
                    PreparedStatement ps1;
                    ResultSet rs1;
                    try {
                        String query1 = "SELECT* FROM users WHERE username='" + userName + "' ";
                        ps1 = MyConnection.getConnection().prepareStatement(query1);
                        rs1 = ps1.executeQuery();
                        if (rs1.next() == true) {
                            gui.popUp("Already Present");
                        } else {
                            PreparedStatement ps;
                            String query = "INSERT INTO `users`(`username`, `password`, `Contact No.`, `Email`, `presents`, `absents`, `leaves`, `images`,`Grade`) VALUES (?,?,?,?,0,0,0,?,'O')";
                            try {
                                ps = MyConnection.getConnection().prepareStatement(query);
                                ps.setString(1, userName);
                                ps.setString(2, userPassword);
                                ps.setString(3,userContact);
                                ps.setString(4,userEmail);
                                InputStream in = new FileInputStream("D:\\default.jpg");
                                ps.setBlob(5,in);
                                if (ps.executeUpdate() > 0) {
                                    gui.popUp("User Added");
                                }
                            } catch (Exception e) {
                                gui.popUp(e.toString());
                            }
                        }
                    }catch (Exception e){
                        gui.popUp(e.toString());
                    }
                }
            });
            Scene scene = new Scene(pane);
            primaryStage.setTitle("Student Mangaement System");
            primaryStage.setWidth(600);
            primaryStage.setHeight(420);
            primaryStage.setScene(scene);
            primaryStage.setResizable(true);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Error Occured");
        }
    }
    public void adminPanel(Stage primaryStage){
        try{
            Text text = new Text("Admin Panel");
            text.setFill(Color.BLACK);
            Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 25);
            text.setFont(font);
            Button bt1 = new Button("View Attendance Record of Every Student");
            Button bt2 = new Button("Create a report of user attendance");
            Button bt3 = new Button("View Leaves");
            Button bt4 = new Button("Grade");
            Button bt5 = new Button("Sign Out");
            VBox hbox = new VBox(bt1,bt2,bt3,bt4,bt5);
            hbox.setAlignment(Pos.TOP_LEFT);
            hbox.setSpacing(5);
            FileInputStream input = new FileInputStream("4.jpg");
            // create a image
            Image image = new Image(input);
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            ImageViewPane viewPane = new ImageViewPane(imageView);
            // create a background image
            BackgroundImage backgroundimage = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            StackPane pane = new StackPane(viewPane);
            pane.getChildren().addAll(hbox, text);
            bt1.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent event) {
                    Stage stage=new Stage();
                   // String arr="";
                    Button bt1=new Button("ADD");
                    Button bt2=new Button("Edit");
                    Button bt3=new Button("Delete");
                    HBox hbox=new HBox(bt1,bt2,bt3);
                    hbox.setSpacing(20);
                    hbox.setAlignment(Pos.BOTTOM_CENTER);
                    tableview = new TableView();
                    try {
                   // tableview = new TableView();
                        data = FXCollections.observableArrayList();
                        String SQL = "SELECT * from present";
                        PreparedStatement ps = MyConnection.getConnection().prepareStatement(SQL);
                        //ResultSet
                        ResultSet rs =ps.executeQuery();

                        /**********************************
                         * TABLE COLUMN ADDED DYNAMICALLY *
                         **********************************/
                        for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                            //We are using non property style for making dynamic table
                            final int j = i;
                            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                            col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>, ObservableValue<String>>(){
                                public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                                    return new SimpleStringProperty(param.getValue().get(j).toString());
                                }
                            });

                            tableview.getColumns().addAll(col);
                            System.out.println("Column ["+i+"] ");
                        }

                        /********************************
                         * Data added to ObservableList *
                         ********************************/
                        while(rs.next()){
                            //Iterate Row
                            ObservableList<String> row = FXCollections.observableArrayList();
                            for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                                //Iterate Column
                                row.add(rs.getString(i));
                            }
                            System.out.println("Row [1] added "+row );
                            data.add(row);

                        }

                        //FINALLY ADDED TO TableView
                        tableview.setItems(data);
                    }catch(Exception e){
                        e.printStackTrace();
                        System.out.println("Error on Building Data");
                    }
                    StackPane pane1 = new StackPane();
                    StackPane pane2 = new StackPane();
                    pane1.getChildren().addAll(tableview);
                    pane2.getChildren().addAll(hbox);
                    HBox hbox1=new HBox(pane1,pane2);
                    Scene scene = new Scene(hbox1);
                    stage.setScene(scene);
                    stage.show();
                            tableview.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                                if (newValue != null) {
                                     String arr=newValue.toString();
                                    System.out.println(newValue.toString());
                                    bt3.setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent event) {
                                            try {
                                                String query="DELETE FROM present WHERE id="+arr.charAt(1)+" ";
                                                PreparedStatement statement = MyConnection.getConnection().prepareStatement(query);
                                                System.out.println(arr.charAt(1));
                                                statement.executeUpdate();
                                               // ResultSet rs = statement.executeQuery();
                                                    gui.popUp("Deleted Successfully");
                                                    }catch(Exception e){
                                                        gui.popUp(e.toString());
                                                    }
                                }
                            });
                        }

                    });
                    bt1.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            Stage stage=new Stage();
                            Button bt1=new Button("OK");
                            TextField tf1=new TextField();
                            Label lb1=new Label("Name");
                            HBox hbox1=new HBox(lb1,tf1);
                            VBox vbox=new VBox(hbox1,bt1);
                            hbox1.setSpacing(20);
                            vbox.setSpacing(20);
                            vbox.setAlignment(Pos.CENTER);
                            bt1.setOnAction(new EventHandler<ActionEvent>() {
                                public void handle(ActionEvent event) {
                                    String Name=tf1.getText();
                                    System.out.println(Name);
                                    int id=1;
                                    try {
                                        String query1 = "SELECT id FROM present";
                                        PreparedStatement st = MyConnection.getConnection().prepareStatement(query1);
                                        ResultSet rs= st.executeQuery();
                                        while(rs.next()){
                                            id++;
                                        }
                                    }catch(Exception e){
                                        gui.popUp(e.toString());
                                    }

                            try {
                                String query="INSERT INTO `present` (`id`, `Name`, `Date`) VALUES ("+id+",?,?);";
                                PreparedStatement statement = MyConnection.getConnection().prepareStatement(query);
                                //statement.setInt(1,(int)id);
                                statement.setString(1,Name);
                                statement.setDate(2,sqldate);
                                statement.executeUpdate();
                               //  ResultSet rs = statement.executeQuery();
                                 //if(rs.next()) {
                                 //}
                            }catch(Exception e){
                                gui.popUp(e.toString());
                            }
                            stage.close();
                                }
                            });
                            StackPane pane=new StackPane();
                            pane.getChildren().add(vbox);
                            Scene scene=new Scene(pane);
                            stage.setScene(scene);
                            stage.show();
                        }

                    });
                    tableview.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                        if (newValue != null) {
                            String arr=newValue.toString();
                    bt2.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            Stage stage=new Stage();
                            Button bt1=new Button("OK");
                            TextField tf1=new TextField();
                            TextField tf2=new TextField();
                            TextField tf3=new TextField();
                            Label lb1=new Label("id");
                            Label lb2=new Label("Name");
                            Label lb3=new Label("Date");
                            HBox hbox1=new HBox(lb1,tf1);
                            HBox hbox2=new HBox(lb2,tf2);
                            HBox hbox3=new HBox(lb3,tf3);
                            VBox vbox=new VBox(hbox1,hbox2,hbox3,bt1);
                            hbox1.setSpacing(20);
                            hbox2.setSpacing(20);
                            hbox3.setSpacing(20);
                            vbox.setSpacing(20);
                            vbox.setAlignment(Pos.CENTER);
                            bt1.setOnAction(new EventHandler<ActionEvent>() {
                                                public void handle(ActionEvent event) {
                                                    String Name=tf2.getText();
                                                    String text = tf3.getText();
                                                    System.out.println(text);
                                                    //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                                    java.util.Date textFieldAsDate = null;

                                                    try {
                                                        textFieldAsDate  = sdf.parse(text);
                                                    } catch (ParseException pe) {
                                                        gui.popUp(pe.toString());
                                                    }
                                                   // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                                    java.sql.Date date = java.sql.Date.valueOf(sdf.format(textFieldAsDate));
                                                    System.out.println(date);
                                                    String str1 = tf1.getText();
                                                    int id = Integer.parseInt(str1);
                                                    try {
                                                        System.out.println(arr.charAt(1));
                                                        String query = "UPDATE present SET id=" + id + ",Name= ? ,Date= ? WHERE id=" + arr.charAt(1) + " ";
                                                        PreparedStatement statement = MyConnection.getConnection().prepareStatement(query);
                                                        statement.setString(1,Name);
                                                        statement.setDate(2,date);
                                                        statement.executeUpdate();
                                                        gui.popUp("Successfully Edited the Data");
                                                        stage.close();
                                                    } catch (Exception e) {
                                                        gui.popUp(e.toString());
                                                    }
                                                }
                                            });
                            StackPane pane=new StackPane();
                            pane.getChildren().add(vbox);
                            Scene scene=new Scene(pane);
                            stage.setScene(scene);
                            stage.show();
                        }

                    });
                        }

                    });
                    }
            });
            bt2.setOnAction(new EventHandler<ActionEvent>() {
                                public void handle(ActionEvent event) {
                                    Stage stage = new Stage();
                                    TextField tf1 = new TextField();
                                    TextField tf2 = new TextField();
                                    Label lb1 = new Label("Date From (yyyy-mm-dd)");
                                    Label lb2 = new Label("Date To(yyyy-mm-dd)");
                                    HBox hbox1 = new HBox(lb1, tf1);
                                    HBox hbox2 = new HBox(lb2, tf2);
                                    Button bt1 = new Button("OK");
                                    VBox vbox = new VBox(hbox1, hbox2,bt1);
                                    hbox1.setSpacing(20);
                                    hbox2.setSpacing(20);
                                    vbox.setSpacing(20);
                                    vbox.setAlignment(Pos.CENTER);
                                    bt1.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {
                                            String dateFrom = tf1.getText();
                                            String dateTo = tf2.getText();
                                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                            java.util.Date textFieldAsDate = null;

                                            try {
                                                textFieldAsDate  = sdf.parse(dateFrom);
                                            } catch (ParseException pe) {
                                                gui.popUp(pe.toString());
                                            }
                                            // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                            java.sql.Date date1 = java.sql.Date.valueOf(sdf.format(textFieldAsDate));
                                            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                                            java.util.Date textFieldAsDate1 = null;

                                            try {
                                                textFieldAsDate1  = sdf1.parse(dateTo);
                                            } catch (ParseException pe) {
                                                gui.popUp(pe.toString());
                                            }
                                            // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                            java.sql.Date date2 = java.sql.Date.valueOf(sdf1.format(textFieldAsDate1));
                                            try {
                                                executeQuery(date1, date2);
                                            }catch(Exception e){
                                                gui.popUp(e.toString());
                                            }
                                        }
                                    });
                                    StackPane pane=new StackPane(vbox);
                                    Scene scene=new Scene(pane);
                                    stage.setScene(scene);
                                    stage.show();

                                }
                            });
            bt3.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent event) {
                    String query,query1,query2,query3,query4,query5,query8,query7;
                    PreparedStatement ps,ps1,ps2,ps3,ps4,ps5,ps7,ps8;
                    ResultSet rs,rs1,rs2,rs3,rs4,rs5;
                    int p = 0;
                    try {
                        query = "SELECT 'username' FROM users";
                        ps = MyConnection.getConnection().prepareStatement(query);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            p++;
                        }
                        int i=0;
                        for (int q = 0; q < p; q++){
                            i++;
                            query1 = "SELECT username FROM users WHERE id=?";
                        ps1 = MyConnection.getConnection().prepareStatement(query1);
                        ps1.setInt(1, i);
                        rs1 = ps1.executeQuery();
                        while (rs1.next()) {
                            int j=0,k=0,l=0;
                            String Name = rs1.getString("username");
                            System.out.println(i);
                            System.out.println(Name);
                            query2 = "SELECT Name FROM present WHERE Name=?";
                            ps2 = MyConnection.getConnection().prepareStatement(query2);
                            ps2.setString(1, Name);
                            rs2 = ps2.executeQuery();
                            while (rs2.next()) {
                                j++;
                            }
                            System.out.println("presents : "+j);
                            query5="UPDATE users SET presents=? WHERE username=?";
                            ps5=MyConnection.getConnection().prepareStatement(query5);
                            ps5.setInt(1,j);
                            ps5.setString(2,Name);
                            ps5.executeUpdate();
                            query3 = "SELECT username FROM absent WHERE username=?";
                            ps3 = MyConnection.getConnection().prepareStatement(query3);
                            ps3.setString(1, Name);
                            rs3 = ps3.executeQuery();
                            while (rs3.next()) {
                                k++;
                            }
                            query7="UPDATE users SET absents=? WHERE username=?";
                            ps7=MyConnection.getConnection().prepareStatement(query7);
                            ps7.setInt(1,k);
                            ps7.setString(2,Name);
                            ps7.executeUpdate();
                            query4 = "SELECT username FROM request_leave WHERE username=?";
                            ps4 = MyConnection.getConnection().prepareStatement(query4);
                            ps4.setString(1, Name);
                            rs4 = ps4.executeQuery();
                            while (rs4.next()) {
                                l++;
                            }
                            query8="UPDATE users SET leaves= ? WHERE username=?";
                            ps8=MyConnection.getConnection().prepareStatement(query8);
                            ps8.setInt(1,l);
                            ps8.setString(2,Name);
                            ps8.executeUpdate();
                        }
                    }

                        Stage stage=new Stage();
                        tableview = new TableView();
                        try {
                            // Stage stage=new Stage();
                            // tableview = new TableView();
                            data = FXCollections.observableArrayList();
                            String SQL = "SELECT `id`, `username`, `leaves` FROM users";
                            PreparedStatement ps6 = MyConnection.getConnection().prepareStatement(SQL);
                            //ResultSet
                            ResultSet rs6 =ps6.executeQuery();

                            /**********************************
                             * TABLE COLUMN ADDED DYNAMICALLY *
                             **********************************/
                            for(int m=0 ; m<rs6.getMetaData().getColumnCount(); m++){
                                //We are using non property style for making dynamic table
                                final int n = m;
                                TableColumn col = new TableColumn(rs6.getMetaData().getColumnName(m+1));
                                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>, ObservableValue<String>>(){
                                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                                        return new SimpleStringProperty(param.getValue().get(n).toString());
                                    }
                                });

                                tableview.getColumns().addAll(col);
                                System.out.println("Column ["+m+"] ");
                            }

                            /********************************
                             * Data added to ObservableList *
                             ********************************/
                            while(rs6.next()){
                                //Iterate Row
                                ObservableList<String> row = FXCollections.observableArrayList();
                                for(int o=1 ; o<=rs6.getMetaData().getColumnCount(); o++){
                                    //Iterate Column
                                    row.add(rs6.getString(o));
                                }
                                System.out.println("Row [1] added "+row );
                                data.add(row);

                            }

                            //FINALLY ADDED TO TableView
                            tableview.setItems(data);
                        }catch(Exception e){
                            e.printStackTrace();
                            System.out.println("Error on Building Data");
                        }

                        //Main Scene
                        StackPane pane = new StackPane();
                        pane.getChildren().addAll(tableview);
                        Scene scene = new Scene(pane);
                        stage.setScene(scene);
                        stage.show();
                        Stage stage1=new Stage();
                        tableview1 = new TableView();
                        try {

                            // tableview = new TableView();
                            data1 = FXCollections.observableArrayList();
                            String SQL1 = "SELECT `username`, `Leaves` FROM `request_leave`";
                            PreparedStatement pss = MyConnection.getConnection().prepareStatement(SQL1);
                            //ResultSet
                            ResultSet rss =pss.executeQuery();

                            /**********************************
                             * TABLE COLUMN ADDED DYNAMICALLY *
                             **********************************/
                            for(int i1=0 ; i1<rss.getMetaData().getColumnCount(); i1++){
                                //We are using non property style for making dynamic table
                                final int j1 = i1;
                                TableColumn col1 = new TableColumn(rss.getMetaData().getColumnName(i1+1));
                                col1.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>, ObservableValue<String>>(){
                                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                                        return new SimpleStringProperty(param.getValue().get(j1).toString());
                                    }
                                });

                                tableview1.getColumns().addAll(col1);
                                System.out.println("Column ["+i1+"] ");
                            }

                            /********************************
                             * Data added to ObservableList *
                             ********************************/
                            while(rss.next()){
                                //Iterate Row
                                ObservableList<String> row1 = FXCollections.observableArrayList();
                                for(int i1=1 ; i1<=rss.getMetaData().getColumnCount(); i1++){
                                    //Iterate Column
                                    row1.add(rss.getString(i1));
                                }
                                System.out.println("Row [1] added "+row1 );
                                data1.add(row1);

                            }

                            //FINALLY ADDED TO TableView
                            tableview1.setItems(data1);
                        }catch(Exception e){
                            e.printStackTrace();
                            System.out.println("Error on Building Data");
                        }
                    //Stage stage1=new Stage();
                    StackPane pane1 = new StackPane();
                    pane1.getChildren().addAll(tableview1);
                    Scene scene1 = new Scene(pane1);
                    stage1.setScene(scene1);
                    stage1.show();
                    } catch (Exception e) {
                        gui.popUp(e.toString());
                    }
                }
            });
            bt4.setOnAction(new EventHandler<ActionEvent>(){
                public void handle(ActionEvent event){
                    try {
                        String query1 = "SELECT presents FROM users";
                        PreparedStatement ps1 = MyConnection.getConnection().prepareStatement(query1);
                        ResultSet rs1 = ps1.executeQuery();
                        String query2 = "SELECT username from users";
                        PreparedStatement ps2=MyConnection.getConnection().prepareStatement(query2);
                        ResultSet rs2=ps2.executeQuery();
                        while (rs1.next() && rs2.next()) {
                            if(rs1.getInt(1)>=26){
                                String query3="UPDATE `users` SET `Grade`=? WHERE username=?";
                                PreparedStatement ps3=MyConnection.getConnection().prepareStatement(query3);
                                ps3.setString(1,"A");
                                ps3.setString(2, rs2.getString(1));
                                System.out.println(rs2.getString(1));
                                ps3.executeUpdate();
                            }
                            if(rs1.getInt(1)>=10 && rs1.getInt(1)<26){
                                String query3="UPDATE `users` SET `Grade`=? WHERE username=?";
                                PreparedStatement ps3=MyConnection.getConnection().prepareStatement(query3);
                                ps3.setString(1,"B");
                                ps3.setString(2, rs2.getString(1));
                                System.out.println(rs2.getString(1));
                                ps3.executeUpdate();
                            }
                            if (rs1.getInt(1)<10){
                                String query3="UPDATE users SET Grade=? WHERE username=?";
                                PreparedStatement ps3=MyConnection.getConnection().prepareStatement(query3);
                                ps3.setString(1,"C");
                                ps3.setString(2, rs2.getString(1));
                                System.out.println(rs2.getString(1));
                                ps3.executeUpdate();
                            }
                        }
                        Stage stage=new Stage();
                        tableview = new TableView();
                        try {

                            // tableview = new TableView();
                            data = FXCollections.observableArrayList();
                            String SQL = "SELECT `username`, `Grade` FROM `users`";
                            PreparedStatement ps = MyConnection.getConnection().prepareStatement(SQL);
                            //ResultSet
                            ResultSet rs =ps.executeQuery();

                            /**********************************
                             * TABLE COLUMN ADDED DYNAMICALLY *
                             **********************************/
                            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                                //We are using non property style for making dynamic table
                                final int j = i;
                                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>, ObservableValue<String>>(){
                                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                                        return new SimpleStringProperty(param.getValue().get(j).toString());
                                    }
                                });

                                tableview.getColumns().addAll(col);
                                System.out.println("Column ["+i+"] ");
                            }

                            /********************************
                             * Data added to ObservableList *
                             ********************************/
                            while(rs.next()){
                                //Iterate Row
                                ObservableList<String> row = FXCollections.observableArrayList();
                                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                                    //Iterate Column
                                    row.add(rs.getString(i));
                                }
                                System.out.println("Row [1] added "+row );
                                data.add(row);

                            }

                            //FINALLY ADDED TO TableView
                            tableview.setItems(data);
                        }catch(Exception e){
                            e.printStackTrace();
                            System.out.println("Error on Building Data");
                        }
                    }catch(Exception e){
                        gui.popUp(e.toString());
                    }
                    Stage stage=new Stage();
                    StackPane pane = new StackPane();
                    pane.getChildren().addAll(tableview);
                    Scene scene = new Scene(pane);
                    stage.setScene(scene);
                    stage.show();
                }
            });
            bt5.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    userPanelLogin(primaryStage);
                }

            });
            Scene scene = new Scene(pane);
            primaryStage.setTitle("Student Mangaement System");
            primaryStage.setWidth(600);
            primaryStage.setHeight(420);
            primaryStage.setScene(scene);
            primaryStage.setResizable(true);
            primaryStage.show();
        }catch (Exception e){
            gui.popUp(e.toString());
        }
    }
    public void userPanel (Stage primaryStage,String str){
        try {
            HBox hb=new HBox();
            ResultSet resultSet;
            File file=new File("D:\\"+str+".jpg");
            FileOutputStream fos=new FileOutputStream(file);
            byte b[];
            Blob blob;
            // java.util.Date date=new java.util.Date();
            // java.sql.Date sqldate=new java.sql.Date(date.getTime());
            System.out.println(str);
            Text txt = new Text("User Login");
            txt.setFill(Color.BLACK);
            Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 25);
            txt.setFont(font);
            Button bt1 = new Button("Present");
            Button bt2 = new Button("Absent");
            Button bt3 = new Button("View");
            Button bt4 = new Button("Request Leave");
            Button bt5 = new Button("Back");
            Button bt6 = new Button("Change Pic");
            HBox hbox = new HBox(bt1, bt2, bt3, bt4, bt5, bt6);
            hbox.setSpacing(30);
            hbox.setAlignment(Pos.BOTTOM_CENTER);
            FileInputStream input = new FileInputStream("4.jpg");
            String query = "SELECT images from users WHERE username= ?";
            PreparedStatement ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, str);
            resultSet = ps.executeQuery();
            if(resultSet.next()) {
                blob=resultSet.getBlob("images");
                b=blob.getBytes(1,(int)blob.length());
               // Blob imageBlob = resultSet.getBlob("images");
               // InputStream binaryStream = imageBlob.getBinaryStream(1, imageBlob.length());
                fos.write(b);
               // byte[] img = resultSet.getBytes("Image");
                Image imagePr = new Image(new FileInputStream("D:\\"+str+".jpg"));

                //Setting the image view
                ImageView imageViewPr = new ImageView(imagePr);
                imageViewPr.setFitHeight(150);
                imageViewPr.setFitWidth(150);
                Group root = new Group(imageViewPr);
                root.setLayoutX(130);
                root.setLayoutY(100);
                imageViewPr.setPreserveRatio(true);
                hb.getChildren().add(root);
            }
            // create a image
            Image image = new Image(input);
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            ImageViewPane viewPane = new ImageViewPane(imageView);
            // create a background image
            BackgroundImage backgroundimage = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            hb.setAlignment(Pos.TOP_RIGHT);
            VBox vb=new VBox(hb);
            vb.setAlignment(Pos.TOP_RIGHT);
            StackPane pane = new StackPane(viewPane);
            pane.getChildren().addAll(txt,vb,hbox);
            bt1.setOnAction(new EventHandler<ActionEvent>() {
                //if (Date().Date)
                public void handle(ActionEvent event) {
                    PreparedStatement ps1, ps2,ps3;
                    ResultSet rs1, rs2,rs3;
                    try {
                        String query1 = "SELECT* FROM present WHERE Name='" + str + "' ";
                        String query2 = "SELECT* FROM absent WHERE username='" + str + "' ";
                        String query3 = "SELECT Name FROM `present` WHERE Name=? AND Date=?";
                        ps3 = MyConnection.getConnection().prepareStatement(query3);
                        ps1 = MyConnection.getConnection().prepareStatement(query1);
                        ps2 = MyConnection.getConnection().prepareStatement(query2);
                        ps3.setString(1,str);
                        ps3.setDate(2,sqldate);
                        rs1 = ps1.executeQuery();
                        rs2 = ps2.executeQuery();
                        rs3 = ps3.executeQuery();
                        //&&
                       // while () {
                        //( ||)
                            if ((rs2.next() == true || rs1.next() == true) && rs3.next()==true ) {
                                gui.popUp("Attendance Already Marked for today");
                            }
                        else{
                                try {
                                    String selectQuery = "INSERT INTO `present`(`Name`, `Date`) VALUES(?,?)";
                                    ps1 = MyConnection.getConnection().prepareStatement(selectQuery);
                                    ps1.setString(1, str);
                                    ps1.setDate(2, sqldate);
                                    if (ps1.executeUpdate() > 0) {
                                        gui.popUp("User Added to Present List");
                                    }

                                } catch (Exception e) {
                                    gui.popUp(e.toString());
                                }
                            }

                    } catch (Exception e) {
                        gui.popUp(e.toString());
                    }
                }
            });
            bt2.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent event) {
                    PreparedStatement ps1,ps2,ps3;
                    ResultSet rs1,rs2,rs3;
                    try {
                        String query1 = "SELECT* FROM present WHERE Name='"+str+"' ";
                        String query2 = "SELECT* FROM absent WHERE username='"+str+"' ";
                        String query3 = "SELECT Name FROM `present` WHERE Name=? AND Date=?";
                        ps1 = MyConnection.getConnection().prepareStatement(query1);
                        ps2 = MyConnection.getConnection().prepareStatement(query2);
                        ps3 = MyConnection.getConnection().prepareStatement(query3);
                        ps3.setString(1,str);
                        ps3.setDate(2,sqldate);
                        rs1 = ps1.executeQuery();
                        rs2 = ps2.executeQuery();
                        rs3 = ps3.executeQuery();
                        if ((rs1.next()==true || rs2.next()==true) && rs3.next()==true ){
                            gui.popUp("Attendance Already Marked for today");
                        }
                        else {
                            try {
                                String selectQuery = "INSERT INTO `absent`(`username`, `Date`) VALUES(?,?)";
                                ps1 = MyConnection.getConnection().prepareStatement(selectQuery);
                                ps1.setString(1, str);
                                ps1.setDate(2, sqldate);
                                if (ps1.executeUpdate() > 0) {
                                    gui.popUp("User Added to Absent List");
                                }

                            } catch (Exception e) {
                                gui.popUp(e.toString());
                            }
                            /*try{
                                String selectQuery="INSERT INTO `absent`(`username`, `Date`) VALUES (?,?)";
                                ps1 = MyConnection.getConnection().prepareStatement(selectQuery);
                                ps1.setString(1, str);
                                ps2.setDate(2,sqldate);
                                if (ps1.executeUpdate() > 0) {
                                    gui.popUp("User Added to Absent List List");
                                }

                            } catch(Exception e){
                                gui.popUp(e.toString());
                            }*/
                        }
                    }catch (Exception e){
                        gui.popUp("fuck saad");
                    }
                }
            });
            bt3.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent event) {
                    Stage stage=new Stage();
                    tableview = new TableView();
                    try {
                       // Stage stage=new Stage();
                       // tableview = new TableView();
                        data = FXCollections.observableArrayList();
                        String SQL = "SELECT * from present";
                        PreparedStatement ps = MyConnection.getConnection().prepareStatement(SQL);
                        //ResultSet
                        ResultSet rs =ps.executeQuery();

                        /**********************************
                         * TABLE COLUMN ADDED DYNAMICALLY *
                         **********************************/
                        for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                            //We are using non property style for making dynamic table
                            final int j = i;
                            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                            col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>, ObservableValue<String>>(){
                                public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                                    return new SimpleStringProperty(param.getValue().get(j).toString());
                                }
                            });

                            tableview.getColumns().addAll(col);
                            System.out.println("Column ["+i+"] ");
                        }

                        /********************************
                         * Data added to ObservableList *
                         ********************************/
                        while(rs.next()){
                            //Iterate Row
                            ObservableList<String> row = FXCollections.observableArrayList();
                            for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                                //Iterate Column
                                row.add(rs.getString(i));
                            }
                            System.out.println("Row [1] added "+row );
                            data.add(row);

                        }

                        //FINALLY ADDED TO TableView
                        tableview.setItems(data);
                    }catch(Exception e){
                        e.printStackTrace();
                        System.out.println("Error on Building Data");
                    }

                    //Main Scene
                    StackPane pane = new StackPane();
                    pane.getChildren().addAll(tableview);
                    Scene scene = new Scene(pane);
                    stage.setScene(scene);
                    stage.show();
                }
            });
            bt4.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent event) {
                    PreparedStatement ps;
                    ResultSet rs;
                    try {
                        String query = "SELECT* FROM request_leave WHERE username='"+str+"' ";
                        ps = MyConnection.getConnection().prepareStatement(query);
                        rs = ps.executeQuery();
                        if (rs.next()==true) {
                            gui.popUp("Already Present");
                        }
                        else {
                            try{
                                gui.popUp2(str);

                                //String selectQuery="INSERT INTO request_leave(username) VALUES (?)";
                               // ps = MyConnection.getConnection().prepareStatement(selectQuery);
                               // ps.setString(1, str);
                               // if (ps.executeUpdate() > 0) {
                                 //   gui.popUp("User Added to List");
                               // }

                           } catch(Exception e){
                                gui.popUp(e.toString());
                            }
                        }
                    }catch (Exception e){
                        gui.popUp(e.toString());
                    }
                }
            });
            bt5.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent event) {
                    userPanelLogin(primaryStage);
                }
            });
            bt6.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    JFileChooser fc = new JFileChooser();
                    if (event.getSource() == bt6) {
                        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                        int returnValue = jfc.showOpenDialog(null);
                        // int returnValue = jfc.showSaveDialog(null);

                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = jfc.getSelectedFile();
                            try {
                                String query = "UPDATE users SET images=? WHERE username=?";
                                PreparedStatement ps = MyConnection.getConnection().prepareStatement(query);
                                InputStream in = new FileInputStream("" + selectedFile.getAbsolutePath() + "");
                                ps.setBlob(1, in);
                                ps.setString(2,str);
                                ps.executeUpdate();
                            }catch(Exception e){
                                gui.popUp(e.toString());
                            }
                          //  System.out.println(selectedFile.getAbsolutePath());
                        }
                    }
                }

            });
            Scene scene1 =new Scene(pane);
            //Scene scene2=new Scene(root);
            primaryStage.setTitle("Student Mangaement System");
            primaryStage.setWidth(600);
            primaryStage.setHeight(420);
            primaryStage.setScene(scene1);
           // primaryStage.setScene(scene2);
            primaryStage.setResizable(true);
            primaryStage.show();
        } catch (Exception e) {
            gui.popUp(e.toString());
        }
    }
    private void executeQuery(java.util.Date startDate, java.util.Date endDate) throws SQLException {
        tableview = new TableView();
        Stage stage =new Stage();
        try {
            // tableview = new TableView();
            data = FXCollections.observableArrayList();
            String SQL = "SELECT* FROM present WHERE Date BETWEEN ? AND ?";
            PreparedStatement ps = MyConnection.getConnection().prepareStatement(SQL);
            ps.setDate(1,new java.sql.Date(startDate.getTime()));
            ps.setDate(2,new java.sql.Date(endDate.getTime()));
            //ResultSet
            ResultSet rs =ps.executeQuery();

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>, ObservableValue<String>>(){
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
                System.out.println("Column ["+i+"] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        StackPane pane=new StackPane(tableview);
        Scene scene=new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String args[])
    {
        MyConnection.getConnection();
        launch(args);
    }
}

class ImageViewPane extends Region {

    private ObjectProperty<ImageView> imageViewProperty = new SimpleObjectProperty<ImageView>();

    public ObjectProperty<ImageView> imageViewProperty() {
        return imageViewProperty;
    }

    public ImageView getImageView() {
        return imageViewProperty.get();
    }

    public void setImageView(ImageView imageView) {
        this.imageViewProperty.set(imageView);
    }

    public ImageViewPane() {
        this(new ImageView());
    }

    @Override
    protected void layoutChildren() {
        ImageView imageView = imageViewProperty.get();
        if (imageView != null) {
            imageView.setFitWidth(getWidth());
            imageView.setFitHeight(getHeight());
            layoutInArea(imageView, 0, 0, getWidth(), getHeight(), 0, HPos.CENTER, VPos.CENTER);
        }
        super.layoutChildren();
    }

    public ImageViewPane(ImageView imageView) {
        imageViewProperty.addListener(new ChangeListener<ImageView>() {

            @Override
            public void changed(ObservableValue<? extends ImageView> arg0, ImageView oldIV, ImageView newIV) {
                if (oldIV != null) {
                    getChildren().remove(oldIV);
                }
                if (newIV != null) {
                    getChildren().add(newIV);
                }
            }
        });
        this.imageViewProperty.set(imageView);
    }
}