/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q5;

import java.awt.peer.TextFieldPeer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Ali Abo Alkhear
 */
public class Login extends Application {

    public static String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        FlowPane fp = new FlowPane();
        fp.setHgap(15);
        fp.setVgap(10);
        fp.setPadding(new Insets(20));

        Label labelName = new Label("Login Name :");
        TextField fieldName = new TextField();
        fieldName.setPromptText("Login Name");
        Label labelPassword = new Label(" Password : ");
        PasswordField fieldPassword = new PasswordField();
        fieldPassword.setPromptText("Password");

        Button buttonOK = new Button("OK");
        Button buttonExit = new Button("Exit");

        HBox box = new HBox();
        box.setSpacing(10);
        box.setPadding(new Insets(10));

        box.getChildren().addAll(buttonOK, buttonExit);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(fp);
        borderPane.setCenter(box);

        buttonExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        buttonOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FileInputStream fis = new FileInputStream("./src/Q5/login.txt");
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);

                        String line = br.readLine();
                        String[] data = line.split(" ");
                        if (fieldName.getText().equals(data[0])
                                && getMd5(fieldPassword.getText()).equals(data[1])) {
                            
                            GridPane rootGrid = new GridPane();
                            rootGrid.setAlignment(Pos.CENTER);
                            rootGrid.setPadding(new Insets(16));
                            rootGrid.setHgap(16);
                            rootGrid.setVgap(8);

                            Button button = new Button("Add Student");
                            Button button1 = new Button(".............................");
                            VBox vbox = new VBox(button, button1);
                            VBox.setMargin(button, new Insets(10, 10, 30, 10));
                            VBox.setMargin(button1, new Insets(10, 10, 10, 10));
                            
                            rootGrid.getChildren().add(vbox);
                            Scene scene = new Scene(rootGrid, 300, 250);
                            primaryStage.setTitle("Options Page");
                            primaryStage.setScene(scene);
                            primaryStage.show();
                            
                        } else {
                            Alert alertWarning = new Alert(AlertType.WARNING);
                            alertWarning.setTitle("Error");
                            alertWarning.setHeaderText("Error in Username and Password");
                            alertWarning.setContentText("Please try Again\n Enter Username and Password");
                            alertWarning.showAndWait();
                        
                        }
                    

                } catch (Exception ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        fp.getChildren().addAll(labelName, fieldName, labelPassword, fieldPassword);
        Scene scene = new Scene(borderPane, 300, 140);
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
