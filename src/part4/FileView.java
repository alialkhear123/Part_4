/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;

/**
 *
 * @author Ali Abo Alkhear
 */
public class FileView extends Application {



    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        FlowPane fp = new FlowPane();
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        Menu menuEdit = new Menu("Edit");

        MenuItem itemOpen = new MenuItem("Open");
        MenuItem itemClose = new MenuItem("Close");
        MenuItem itemSave = new MenuItem("Save");
        MenuItem itemExit = new MenuItem("Exit");
        MenuItem itemFont = new MenuItem("Font");
        MenuItem itemColor = new MenuItem("Color");

        TextArea area = new TextArea();
        ScrollPane labelScroller = new ScrollPane(area);
        area.setMaxWidth(300);
        area.setWrapText(true);

        menuBar.getMenus().addAll(menuFile, menuEdit);

        menuFile.getItems().addAll(itemOpen, itemSave, itemClose, itemExit);
        menuEdit.getItems().addAll(itemFont, itemColor);
        
        FileChooser fileChooser = new FileChooser();
        itemOpen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                Window stage = null;
                File file = fileChooser.showOpenDialog(stage);  
                if (file != null) {
                    String fileAsString = file.toString();
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileAsString));
                        String line = "";
                        String text = "";
                        while ((line = bufferedReader.readLine()) != null) {
                            text += line + "\n";
                        }
                        area.setText(text);
                        bufferedReader.close();
                    } catch (Exception ex) {
                        Logger.getLogger(FileView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    area.setText(null);
                }
            }
        }
        );
        
        itemSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Window stage = null;
                    File file = fileChooser.showOpenDialog(stage);
                    String filePath = file.getPath();
                    FileOutputStream fout = new FileOutputStream(filePath, true);
                    PrintWriter pw = new PrintWriter(fout);
                    pw.println("\n" + area.getText());
                    pw.flush();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FileView.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        
        itemClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                area.setText(null);
                area.setFont(new Font("Arial", 15));
                area.setStyle("-fx-text-fill: black ;");
                area.setEditable(false);
            }

        });

        itemExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        itemFont.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Font");
                dialog.setHeaderText("Enter A Font");
                dialog.setContentText("Font : ");

                Optional<String> result = dialog.showAndWait();
                
                int size = Integer.parseInt(result.get());
                area.setFont(new Font(size));             
            }
        });

        itemColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ChoiceDialog<String> dialog = new ChoiceDialog<>("Yellow", "Blue",
                        "Red" , "Cyan" , "Green" , "Orange" , "Pink");
                dialog.setTitle("Color");
                dialog.setHeaderText("Select a Color:");
                dialog.setContentText("Color : ");
                
                Optional<String> result = dialog.showAndWait();

                result.ifPresent(book -> {
                    System.out.println("Your Color: " + result.get());
                });
                switch (result.get()) {
                    case "Red":
                        area.setStyle("-fx-text-fill: red ;");
                        break;
                    case "Yellow":
                        area.setStyle("-fx-text-fill: yellow ;");
                        break;
                    case "Blue":
                        area.setStyle("-fx-text-fill: blue ;");
                        break;
                    case "Cyan":
                        area.setStyle("-fx-text-fill: cyan ;");
                        break;
                    case "Green":
                        area.setStyle("-fx-text-fill: green ;");
                        break;
                    case "Orange":
                        area.setStyle("-fx-text-fill: orange ;");
                        break;
                    case "Pink":
                        area.setStyle("-fx-text-fill: pink ;");
                        break;
                }
            }
        });

        fp.getChildren().addAll(menuBar, labelScroller);

        Scene scene = new Scene(fp, 300, 250);
        primaryStage.setTitle("File View");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
