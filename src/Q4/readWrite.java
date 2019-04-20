/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 *
 * @author Ali Abo Alkhear
 */
public class readWrite extends Application {

    @Override
    public void start(Stage primaryStage) {
        FlowPane fp = new FlowPane();
        fp.setHgap(12);
        fp.setPadding(new Insets(10));
        Label labelNo = new Label("Account Number : ");
        TextField AccNo = new TextField();
        AccNo.setPromptText("Account Number ");
        Label labelName = new Label("Account Name : ");
        TextField AccName = new TextField();
        AccName.setPromptText("Account Name ");
        Label labelBalance = new Label("Account Balance : ");
        TextField AccBalance = new TextField();
        AccBalance.setPromptText("Account Balance ");
        Button butSave = new Button("Save");
        Button butShow = new Button("Show");

        fp.getChildren().addAll(labelNo, AccNo, labelName, AccName,
                labelBalance, AccBalance, butSave, butShow);
        
        butSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Account account = new Account(Integer.valueOf(AccNo.getText()), AccName.getText(),
                            Integer.valueOf(AccBalance.getText()));
                    System.out.println(account);
                    FileOutputStream fos = new FileOutputStream("./src/Q4/Accounts.ser", true);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(account);
                    oos.flush();
                    oos.close();
                    fos.close();
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(readWrite.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(readWrite.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        butShow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FileInputStream fis = new FileInputStream("./src/Q4/Accounts.ser");
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    Account account = (Account) ois.readObject();
                    System.out.println(account);
                } catch (IOException ex) {
                    Logger.getLogger(readWrite.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(readWrite.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        Scene scene = new Scene(fp, 300, 150);

        primaryStage.setTitle("Account Information");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

}
