package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;

public class MyJavaFX extends Application {

    // 定义用户名和密码
//    private static final String USERNAME = "";
    private static ArrayList<String> userName = new ArrayList<>(Arrays.asList("", "111", "222"));
    private  static  ArrayList<String> passWord = new ArrayList<>(Arrays.asList("", "111", "333"));
//    private static final String PASSWORD = "";

    @Override
    public void start(Stage primaryStage) throws Exception {

        // 创建登录表单
        Label headerLabel = new Label("Login");
        headerLabel.setFont(new Font("Arial", 20));
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Button forgotPasswordButton = new Button("Find password");

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
        buttonBox.getChildren().addAll(loginButton, forgotPasswordButton);
        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(buttonBox, 1, 2);

        Label statusLabel = new Label();

        BorderPane root1 = new BorderPane();
        BorderPane root2 = new BorderPane();

        Scene scene1 = new Scene(root1, 800, 600);

        // 登录按钮事件处理程序
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            for(int i = 0; i < userName.size(); i++) {
                if (username.equals(userName.get(i)) && password.equals(passWord.get(i))) {
                    // 登录成功，显示主界面
                    primaryStage.setScene(scene1);
                } else {
                    // 显示错误消息

                    statusLabel.setText("Username or password is incorrect.");
                }
            }
        });

        // 找回密码按钮事件处理程序
        forgotPasswordButton.setOnAction(event -> {

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
        });


        //login ui
        primaryStage.setScene(new Scene(gridPane, 300, 275));

        Button switchButton1 = new Button("Switch to Game 2");
        Button switchButton2 = new Button("Switch to Game 1");
//
        set2Pane(switchButton1, root1, 1);
        set2Pane(switchButton2, root2, 2);
        // Create the Switch button

        Scene scene2 = new Scene(root2, 800, 600);
        switchButton1.setOnAction(event -> {
            primaryStage.setScene(scene2);
        });

        switchButton2.setOnAction(event -> {
            primaryStage.setScene(scene1);

        });

        primaryStage.show();

    }

    public void set2Pane(Button switchButton,BorderPane root, int id){
        // 创建主界面

        HBox rowBow = new HBox(0);
        rowBow.setPadding(new Insets(10,10,10,10));
        //
        Label GameId = new Label("Game id: "+id);
        rowBow.getChildren().add(GameId);
        Label food = new Label("Food:       ");
        rowBow.getChildren().add(food);
        Label PlayerRank = new Label("Player Rank:  ");
        rowBow.getChildren().add(PlayerRank);
        Button upgrade = new Button("↑");
        rowBow.getChildren().add(upgrade);
        rowBow.setAlignment(Pos.CENTER);
        root.setTop(rowBow);
        VBox leftBox = new VBox(0);
        leftBox.setPadding(new Insets(10, 10, 10, 10));
        leftBox.setAlignment(Pos.CENTER);
        Button[] buttons = new Button[10];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            HBox rowBox = new HBox(0);
            rowBox.setAlignment(Pos.CENTER);
            int j = 0;
            if(i < 2){
                for (; j < 3; j++) {
                    Button button = new Button("Map " + (i * 3 + j + 1));
                    button.setPrefSize(100, 50);
                    buttons[index++] = button;
                    rowBox.getChildren().add(button);
                }
            }else{
                for (; j < 4; j++) {
                    Button button = new Button("Map " + (i * 3 + j + 1));
                    button.setPrefSize(75, 50);
                    buttons[index++]= button;
                    rowBox.getChildren().add(button);
                }
            }

            leftBox.getChildren().add(rowBox);
        }

        Label statusLabel = new Label();
        statusLabel.setAlignment(Pos.CENTER);

        VBox rightBox = new VBox(10);
        rightBox.setPadding(new Insets(10, 10, 10, 10));
        rightBox.setAlignment(Pos.CENTER);
        Button attackButton = new Button("Attack");
        Button moveButton = new Button("Move");
        Button upgradeButton = new Button("Upgrade");
        rightBox.getChildren().addAll(attackButton, moveButton, upgradeButton);

        HBox lastRowBox = new HBox(20);
        lastRowBox.setPadding(new Insets(10,10,10,10));
        Button doneButton = new Button("Done");

        lastRowBox.setAlignment(Pos.CENTER);
        lastRowBox.getChildren().add(doneButton);
        lastRowBox.getChildren().add(switchButton);
        root.setBottom(lastRowBox);
        root.setLeft(leftBox);
        root.setCenter(statusLabel);
        root.setRight(rightBox);

        // 左侧按钮事件处理程序
        for (Button button : buttons) {
            button.setOnAction(event -> {
                String buttonText = button.getText();
                statusLabel.setText("你点击了 " + buttonText);
            });
        }

        // 右侧按钮事件处理程序
        attackButton.setOnAction(event -> {
            statusLabel.setText("Attack！");
        });

        // 创建一个网格布局
        GridPane gridPaneDialog = new GridPane();
        gridPaneDialog.setPadding(new Insets(10));
        gridPaneDialog.setHgap(10);
        gridPaneDialog.setVgap(10);
        //create a dialog to input attack information
        attackButton.setOnAction(event -> {
            // create a dialog box
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Attack");
            dialog.setHeaderText("Please enter origin/destination/units");
            //add button
            ButtonType DoneButtonType = new ButtonType("Done", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(DoneButtonType, ButtonType.CANCEL);

            // create text box and label
            Label originLabel = new Label("From:");
            TextField originTextField = new TextField();
            Label destLabel = new Label("To:");
            TextField destTestField = new TextField();
            Label unitLabel = new Label("Units:");
            TextField unitTestField = new TextField();

            // add gridpane and
            gridPaneDialog.add(originLabel, 1, 1);
            gridPaneDialog.add(originTextField, 2, 1);
            gridPaneDialog.add(destLabel, 1, 2);
            gridPaneDialog.add(destTestField, 2, 2);
            gridPaneDialog.add(unitLabel, 1, 3);
            gridPaneDialog.add(unitTestField, 2, 3);

            // set content in gridpane
            dialog.getDialogPane().setContent(gridPaneDialog);

            // 禁用确定按钮，除非文本框中有输入
            Node okButton = dialog.getDialogPane().lookupButton(DoneButtonType);
            okButton.setDisable(true);

            // 当文本框中有输入时，启用确定按钮
            originTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                okButton.setDisable(newValue.trim().isEmpty() || destTestField.getText().isEmpty());
            });
            destTestField.textProperty().addListener((observable, oldValue, newValue) -> {
                okButton.setDisable(newValue.trim().isEmpty() || originTextField.getText().isEmpty());
            });

            // 获取用户输入
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == DoneButtonType) {
                    return new Pair<>(originTextField.getText(), destTestField.getText());
                }
                return null;
            });

            // 显示对话框并等待用户响应
            dialog.showAndWait().ifPresent(result -> {
                System.out.println(": " + result.getKey() + ", : " + result.getValue());
            });
        });


        moveButton.setOnAction(event -> {
            statusLabel.setText("Move！");
        });

        upgradeButton.setOnAction(event -> {
            statusLabel.setText("Upgrade!");
        });




    }

    public static void main(String[] args) {
        launch(args);
    }
}