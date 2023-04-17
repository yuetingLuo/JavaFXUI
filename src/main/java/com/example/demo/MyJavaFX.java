package com.example.demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class MyJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent gridPane = FXMLLoader.load(getClass().getResource("startGridPine.fxml"));
        primaryStage.setScene(new Scene(gridPane, 300, 275));
        primaryStage.show();

        Label statusLabel = new Label();
        BorderPane root1 = new BorderPane();
        BorderPane root2 = new BorderPane();

        Scene scene1 = new Scene(root1, 800, 600);
        //login ui

        Button switchButton1 = new Button("Switch to Game 2");
        Button switchButton2 = new Button("Switch to Game 1");
//
//        set2Pane(switchButton1, root1, 1);
//        set2Pane(switchButton2, root2, 2);
        // Create the Switch button

        Scene scene2 = new Scene(root2, 800, 600);
        switchButton1.setOnAction(event -> {
            primaryStage.setScene(scene2);
        });

        switchButton2.setOnAction(event -> {
            primaryStage.setScene(scene1);

        });
    }

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
//    @FXML private TextField usernameField;
//    @FXML private PasswordField passwordField;

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
                Parent root1 = FXMLLoader.load(getClass().getResource("secondGrid.fxml"));
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
            // 登录失败，提示错误信息
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error!");
            alert.setContentText("username or password is wrong!");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleForgotPassword() {
        // 创建一个确认按钮
        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(e -> {
            // 关闭该窗口
            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.close();
        });
        // 显示找回密码窗口
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
    @FXML
    private Label statusLabel;

    @FXML
    private void handleButtonClick(ActionEvent event) {
        // 获取事件源按钮的文本
        String buttonText = ((Button) event.getSource()).getText();

        // 更新状态标签
        statusLabel.setText("You have clicked on " + buttonText);
    }
    @FXML
    void handleUpgradeButton(ActionEvent event) {
        statusLabel.setText("Upgrade!");
    }
    @FXML
    private void handleLogoutButton(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Parent root1 = FXMLLoader.load(getClass().getResource("startGridPine.fxml"));
        Scene scene = new Scene(root1, 300, 275);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setScene(scene);
        double centerX = screenBounds.getMinX() + (screenBounds.getWidth() - scene.getWidth()) / 2;
        double centerY = screenBounds.getMinY() + (screenBounds.getHeight() - scene.getHeight()) / 2;
        stage.setX(centerX);
        stage.setY(centerY);
    }

    @FXML
    private TextField originTextField;

    @FXML
    private TextField destTextField;

    @FXML
    private TextField unitTextField;

    @FXML
    private GridPane attackGridPane;

    @FXML
    private void handleAttackButton(ActionEvent event) throws IOException {
        // 创建一个攻击对话框
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Attack");
        dialog.setHeaderText("Please enter origin/destination/units");
        //add button
        ButtonType DoneButtonType = new ButtonType("Attack", ButtonBar.ButtonData.OK_DONE);
        ButtonType CancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(DoneButtonType, CancelButtonType);
        // 从FXMLLoader实例中获取attackGridPane的引用
        FXMLLoader loader = new FXMLLoader(getClass().getResource("attack.fxml"));
        attackGridPane = loader.load();
        dialog.getDialogPane().setContent(attackGridPane);
        originTextField = (TextField) attackGridPane.lookup("#originTextField");
        destTextField = (TextField) attackGridPane.lookup("#destTextField");
        unitTextField = (TextField) attackGridPane.lookup("#unitTextField");
        // 禁用确定按钮，除非文本框中有输入
        Node okButton = dialog.getDialogPane().lookupButton(DoneButtonType);
        okButton.setDisable(true);
        // 当文本框中有输入时，启用确定按钮
        originTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean disable = newValue.trim().isEmpty() || destTextField.getText().isEmpty() || unitTextField.getText().isEmpty();
            okButton.setDisable(disable);
        });
        destTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean disable = newValue.trim().isEmpty() || originTextField.getText().isEmpty() || unitTextField.getText().isEmpty();
            okButton.setDisable(disable);
        });
        unitTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean disable = newValue.trim().isEmpty() || destTextField.getText().isEmpty() || originTextField.getText().isEmpty();
            okButton.setDisable(disable);
        });
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == DoneButtonType) {
                return new Pair<>(originTextField.getText(), destTextField.getText());
            }
            return null;
        });

        // 显示对话框并等待用户响应
        dialog.showAndWait().ifPresent(result -> {
            System.out.println(": " + result.getKey() + ", : " + result.getValue());
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}