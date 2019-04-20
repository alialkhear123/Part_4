/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part4;

import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author Ali Abo Alkhear
 */
public class  binaryFormat extends Application {

    @Override
    public void start(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        Button btn = new Button();
        btn.setText("Open");
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Window stage = null;
                File file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                    String fileAsString = file.toString();
                    try {
                        FileInputStream fis = new FileInputStream(new File(fileAsString));
                        byte[] infoBin = new byte[fis.available()];
                        fis.read(infoBin);
                        String bin = null;
                        for (byte b : infoBin) {
                            bin = Integer.toBinaryString(b);
                            System.out.print(bin);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(FileView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Binary File");
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
