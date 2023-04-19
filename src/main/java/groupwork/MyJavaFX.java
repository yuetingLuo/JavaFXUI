package groupwork;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;

public class MyJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent gridPane = FXMLLoader.load(getClass().getResource("resources/fxml/startGridPine.fxml"));
        primaryStage.setScene(new Scene(gridPane, 400, 600));
        primaryStage.setTitle("Risk Game");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("resources/image/logo.png")));
        primaryStage.show();

        Label statusLabel = new Label();
        BorderPane root1 = new BorderPane();
        BorderPane root2 = new BorderPane();

        Scene scene1 = new Scene(root1, 800, 600);

        Button switchButton1 = new Button("Switch to Game 2");
        Button switchButton2 = new Button("Switch to Game 1");

        Scene scene2 = new Scene(root2, 800, 600);
        switchButton1.setOnAction(event -> {
            primaryStage.setScene(scene2);
        });

        switchButton2.setOnAction(event -> {
            primaryStage.setScene(scene1);

        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}