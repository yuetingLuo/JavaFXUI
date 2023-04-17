package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        ArrayList<String> userName = new ArrayList<>(Arrays.asList("", "111", "222"));
        ArrayList<String> passWord = new ArrayList<>(Arrays.asList("", "111", "333"));
        boolean valid = false;
        for(int i = 0; i < userName.size(); i++) {
            if (username.equals(userName.get(i)) && password.equals(passWord.get(i))) {
                // 登录成功，显示主界面
//                primaryStage.setScene(scene1);
                valid = true;
                break;
            }
        }
        if (!valid) {
            // 登录失败，提示错误信息
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误");
            alert.setHeaderText("登录失败");
            alert.setContentText("用户名或密码错误！");
            alert.showAndWait();
        }
    }


}