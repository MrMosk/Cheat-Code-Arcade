package menu;

import game.Pong;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
/**
 * A sample with a control that creates a decorated stage that is centered on
 * your desktop.
 */
public class Menu extends Application {
 
    public Parent createContent() {
    	
        //create a button for initializing our new stage
        Button button = new Button("Start Pong");
        button.setStyle("-fx-font-size: 24;");
        button.setDefaultButton(true);
        button.setOnAction((ActionEvent t) -> {
        	
            final Stage stage = new Stage();
 
            //create root node of scene, i.e. group
            Group rootGroup = new Group();
 
            //create scene with set width, height and color
            Scene scene = new Scene(rootGroup, 600, 600, Color.WHITESMOKE);
            Pong ping = new Pong();
            //set scene to stage
            stage.setScene(scene);
 
            //set title to stage
            stage.setTitle("Pong alpha");
            //center stage on screen
            stage.centerOnScreen();
           
            //show the stage
            stage.show();
 
            //add some node to scene
            Text text = new Text(20, 110, "Soon");
            text.setFill(Color.AQUA);
            text.setEffect(new Lighting());
            text.setFont(Font.font(Font.getDefault().getFamily(), 50));
 
            //add text to the main root group
            rootGroup.getChildren().add(text);
        });
        Button button2 = new Button("Leaderboard");
        button2.setStyle("-fx-font-size: 24;");
        button2.setDefaultButton(true);
        button2.setOnAction((ActionEvent t) -> {
        	
            final Stage stage = new Stage();
 
            //create root node of scene, i.e. group
            Group rootGroup = new Group();
 
            //create scene with set width, height and color
            Scene scene = new Scene(rootGroup, 600, 600, Color.WHITESMOKE);
 
            //set scene to stage
            stage.setScene(scene);
 
            //set title to stage
            stage.setTitle("Leaderboard alpha");
 
            //center stage on screen
            stage.centerOnScreen();
 
            //show the stage
            stage.show();
 
            //add some node to scene
            Text text = new Text(20, 110, "Soon");
            text.setFill(Color.AQUA);
            text.setEffect(new Lighting());
            text.setFont(Font.font(Font.getDefault().getFamily(), 50));
 
            //add text to the main root group
            rootGroup.getChildren().add(text);
        });
        Button button3 = new Button("go back");
        button3.setStyle("-fx-font-size: 24;");
        button3.setDefaultButton(true);
        button3.setOnAction((ActionEvent t) -> {
        	
            final Stage stage = new Stage();
 
            //create root node of scene, i.e. group
            Group rootGroup = new Group();
 
            //create scene with set width, height and color
            Scene scene = new Scene(rootGroup, 600, 600, Color.WHITESMOKE);
 
            //set scene to stage
            stage.setScene(scene);
 
            //set title to stage
            stage.setTitle("New stage");
 
            //center stage on screen
            stage.centerOnScreen();
 
            //show the stage
            stage.show();
 
            //add some node to scene
            Text text = new Text(20, 110, "Soon");
            text.setFill(Color.AQUA);
            text.setEffect(new Lighting());
            text.setFont(Font.font(Font.getDefault().getFamily(), 50));
 
            //add text to the main root group
            rootGroup.getChildren().add(text);
        });
        HBox buttonBox = new HBox(8);
        buttonBox.getChildren().addAll(button,button2,button3);
        // create vbox for desk and buttons
        VBox vb = new VBox(10);
        vb.getChildren().addAll(buttonBox);
        vb.setPadding(new Insets(15, 24, 15, 24));
        vb.setMaxSize(VBox.USE_PREF_SIZE, VBox.USE_PREF_SIZE);
        vb.setMinSize(VBox.USE_PREF_SIZE, VBox.USE_PREF_SIZE);
        
        return vb;
    }
 
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }
 
    /**
     * Java main for when running without JavaFX launcher
     */
    public static void main(String[] args) {
        launch(args);
    }
}