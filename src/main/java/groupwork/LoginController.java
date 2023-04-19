package groupwork;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        ArrayList<String> userName = new ArrayList<>(Arrays.asList("", "111", "222"));
        ArrayList<String> passWord = new ArrayList<>(Arrays.asList("", "111", "333"));
        boolean valid = false;
        for(int i = 0; i < userName.size(); i++) {
            if (username.equals(userName.get(i)) && password.equals(passWord.get(i))) {
                // 登录成功，显示主界面
                Parent root1 = FXMLLoader.load(getClass().getResource("resources/fxml/gameGrid.fxml"));
                Scene scene = new Scene(root1, 800, 600);
                Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(scene);
                double centerX = screenBounds.getMinX() + (screenBounds.getWidth() - scene.getWidth()) / 2;
                double centerY = screenBounds.getMinY() + (screenBounds.getHeight() - scene.getHeight()) / 2;
                stage.setX(centerX);
                stage.setY(centerY);
                stage.show();
                valid = true;
                break;
            }
        }
        if (!valid) {
            // Login failed, show error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error!");
            alert.setContentText("username or password is wrong!");
            alert.showAndWait();
        }
    }


    @FXML
    private void handleExit(ActionEvent event) throws IOException {
        System.exit(0);
    }
    @FXML
    public void handleForgotPassword() {
        // Create a new window
        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(e -> {
            // Close the window
            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.close();
        });
        // Show the window
        Label messageLabel = new Label("Please contact your administrator.");
        messageLabel.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(messageLabel, confirmButton);
        Scene scene = new Scene(vbox, 300, 200);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

//    @FXML
//    private void handleRegister(ActionEvent event) throws IOException {
//        Parent root1 = FXMLLoader.load(getClass().getResource("resources/fxml/register.fxml"));
//        Scene scene = new Scene(root1, 800, 600);
//        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//
//        Stage stage = (Stage) usernameField.getScene().getWindow();
//        stage.setScene(scene);
//        double centerX = screenBounds.getMinX() + (screenBounds.getWidth() - scene.getWidth()) / 2;
//        double centerY = screenBounds.getMinY() + (screenBounds.getHeight() - scene.getHeight()) / 2;
//        stage.setX(centerX);
//        stage.setY(centerY);
//        stage.show();
//    }

}