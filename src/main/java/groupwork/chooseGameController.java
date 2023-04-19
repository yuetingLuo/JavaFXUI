package groupwork;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class chooseGameController {

    @FXML
    private TextField gameNumberTextField;

    public void initialize() {
        gameNumberTextField.setPromptText("Enter Game 1 or Game 2");
    }

    public void startGame() throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("resources/fxml/gameGrid.fxml"));
        Label gameIdLabel = (Label) root1.lookup("#GameId");
        //gameNumberTextField.getText()不为1或者2时继续循环，直到输入为1或者2
        gameIdLabel.setText(gameNumberTextField.getText());


        Scene scene = new Scene(root1, 800, 600);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        Stage stage = (Stage) gameNumberTextField.getScene().getWindow();
        stage.setScene(scene);
        double centerX = screenBounds.getMinX() + (screenBounds.getWidth() - scene.getWidth()) / 2;
        double centerY = screenBounds.getMinY() + (screenBounds.getHeight() - scene.getHeight()) / 2;
        stage.setX(centerX);
        stage.setY(centerY);
        stage.show();

    }
}
