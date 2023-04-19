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
    }

    public static void main(String[] args) {
        launch(args);
    }

}