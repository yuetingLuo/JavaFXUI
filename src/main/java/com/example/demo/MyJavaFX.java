package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Pair;

public class MyJavaFX extends Application {

    // 定义用户名和密码
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

//    private final WindowManager windowManager = new WindowManager();

    @Override
    public void start(Stage primaryStage) throws Exception {

        // 创建登录表单
        Label headerLabel = new Label("登录");
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
            if (username.equals(USERNAME) && password.equals(PASSWORD)) {
                // 登录成功，显示主界面

//                primaryStage.setScene(new Scene(root1, 800, 600));
                primaryStage.setScene(scene1);
            } else {
                // 显示错误消息
                statusLabel.setText("用户名或密码错误");
            }
        });

        // 找回密码按钮事件处理程序
        forgotPasswordButton.setOnAction(event -> {
            // 显示找回密码窗口
            Label messageLabel = new Label("请联系管理员找回密码。");
            messageLabel.setAlignment(Pos.CENTER);
            VBox vbox = new VBox(10);
            vbox.setAlignment(Pos.CENTER);
            vbox.getChildren().addAll(messageLabel);
            Scene scene = new Scene(vbox, 300, 200);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        });


        //login ui
        primaryStage.setScene(new Scene(gridPane, 300, 275));

        Button switchButton1 = new Button("Switch to Game 2");
        switchButton1.setFont(new Font("Cambria",25));
        Button switchButton2 = new Button("Switch to Game 1");
        switchButton2.setFont(new Font("Cambria",25));
//
        set2Pane(switchButton1, root1, 1);
        set2Pane(switchButton2, root2, 2);

        primaryStage.setTitle("Risc Game");
        // Create the Switch button

        Scene scene2 = new Scene(root2, 800, 600);
//        scene2.setFill(new ImagePattern(image));
        switchButton1.setOnAction(event -> {
            primaryStage.setScene(scene2);
        });

        switchButton2.setOnAction(event -> {
            primaryStage.setScene(scene1);

        });
        //Attach the icon to the stage/window
        primaryStage.getIcons().add(new Image("logo.png"));
//        primaryStage.getIcons().add(new Image(JavaFXIconExample.class.getResourceAsStream("/logo.png")));
        primaryStage.show();
//        primaryStage.hide();

    }

    public void set2Pane(Button switchButton,BorderPane root, int id){
        // 创建主界面
        ImagePattern imagePattern = new ImagePattern(new Image("bg.jpg"),0,0,0.5,0.5,true);
        root.setBackground(new Background(new BackgroundFill(imagePattern,CornerRadii.EMPTY, Insets.EMPTY)));
        VBox colBox = new VBox(0);
        HBox rowBow = new HBox(0);
        rowBow.setPadding(new Insets(10,10,10,10));
        //
        Label gameId = new Label("Game id: "+id);
        gameId.setFont(new Font("Cambria",22));
        rowBow.getChildren().add(gameId);

        Label food = new Label("   Food:  "+(id*65+23));
        food.setFont(new Font("Cambria",22));
        rowBow.getChildren().add(food);
        Label playerRank = new Label("  Your Rank: "+(id+1)+"  ");
        playerRank.setFont(new Font("Cambria",22));
        rowBow.getChildren().add(playerRank);
        Button upgrade = new Button("↑");
        rowBow.getChildren().add(upgrade);
        rowBow.setAlignment(Pos.CENTER);
        root.setTop(rowBow);

        colBox.getChildren().add(rowBow);
        HBox rowBow2 = new HBox(0);
        rowBow2.setPadding(new Insets(10,10,10,10));
        //
        Label numberOfPlayer = new Label("#Player: "+(id+1));
        numberOfPlayer.setFont(new Font("Cambria",19));
        rowBow2.getChildren().add(numberOfPlayer);
        Label color = new Label("  Your territories are blue.  ");
        color.setFont(new Font("Cambria",19));
        rowBow2.getChildren().add(color);
        rowBow2.setAlignment(Pos.CENTER);
        colBox.getChildren().add(rowBow2);
        root.setTop(colBox);

        //add map button
        VBox leftBox = new VBox(0);
        leftBox.setPadding(new Insets(10, 10, 10, 10));
        leftBox.setAlignment(Pos.CENTER);
        Button[] buttons = new Button[10];
        generateMap(leftBox, buttons,id+1);

        Label statusLabel = new Label();
        statusLabel.setAlignment(Pos.CENTER);

        VBox rightBox = new VBox(10);
        rightBox.setPadding(new Insets(10, 10, 10, 10));
        rightBox.setAlignment(Pos.CENTER);
        Button attackButton = new Button("Attack");
        attackButton.setFont(new Font("Cambria",22));
        Button moveButton = new Button("Move");
        moveButton.setFont(new Font("Cambria",25));
        Button upgradeButton = new Button("Upgrade");
        upgradeButton.setFont(new Font("Cambria",19));
        rightBox.getChildren().addAll(attackButton, moveButton, upgradeButton);

        HBox lastRowBox = new HBox(20);
        lastRowBox.setPadding(new Insets(10,10,10,10));
        Button doneButton = new Button("Done");
        doneButton.setFont(new Font("Cambria",25));

        lastRowBox.setAlignment(Pos.CENTER);
        lastRowBox.getChildren().add(doneButton);
        lastRowBox.getChildren().add(switchButton);
        root.setBottom(lastRowBox);
        root.setLeft(leftBox);
        root.setCenter(statusLabel);
        root.setRight(rightBox);

        // 左侧按钮事件处理程序
        for(int i = 0; i < buttons.length; i++){
            Button button = buttons[i];
            button.setOnAction(event -> {
                String buttonText = button.getText();
                statusLabel.setText("Owner: Player 1\nName: Condor\nLevel:\nWasps:3\nBees:4\nAnts:2");
                statusLabel.setFont(new Font("Cambria", 20));
                statusLabel.setStyle("-fx-font-weight: bold");
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

        upgradeButton.setOnAction(event -> {
            statusLabel.setText("Upgrade！");
        });
    }

    //id is used to check how many map in this game, reserved
    public void generateMap(VBox leftBox, Button[] buttons, int numsOfPlayer){

        int index = 0;
        for (int i = 0; i < 3; i++) {
            HBox rowBox = new HBox(0);
            rowBox.setAlignment(Pos.CENTER);
            int j = 0;
            if(i < 2){
                for (; j < 3; j++) {
                        Button button = new Button("Map " + (i * 3 + j + 1));
                        button.setFont(new Font("Cambria",16));
                        button.setPrefSize(100, 50);
                        buttons[index++] = button;
                        if(index < 6){
                            BackgroundFill backgroundFill = new BackgroundFill(Paint.valueOf("#8ba583"),  CornerRadii.EMPTY, Insets.EMPTY);
                            Background background = new Background(backgroundFill);
                            button.setBackground(background);

                        }else{
                            BackgroundFill backgroundFill = new BackgroundFill(Paint.valueOf("#f2e6ce"),  CornerRadii.EMPTY, Insets.EMPTY);
                            Background background = new Background(backgroundFill);
                            button.setBackground(background);
                        }
                        BorderStroke borderStroke = new BorderStroke(Paint.valueOf("#3D3D3D"), BorderStrokeStyle.SOLID,  CornerRadii.EMPTY, new BorderWidths(1));
                        Border border = new Border(borderStroke);
                        button.setBorder(border);
                        rowBox.getChildren().add(button);

                    }
                }else{
                    for (; j < 4; j++) {
                        Button button = new Button("Map " + (i * 3 + j + 1));
                        button.setFont(new Font("Cambria",16));
                        button.setPrefSize(75, 50);
                        buttons[index++]= button;

                        rowBox.getChildren().add(button);
                    }
                }
            leftBox.getChildren().add(rowBox);
        }
        String[] colors;
        if(numsOfPlayer == 3){
            colors = new String[]{"#f2e6ce", "#8ba583", "#8ba583", "#f2e6ce", "#f2e6ce", "#8ba583", "#74759b", "#74759b", "#74759b", "#A6A6A8"};
            buttons[9].setText("");
        }else{
            colors = new String[]{"#f2e6ce", "#f2e6ce", "#f2e6ce", "#f2e6ce", "#f2e6ce", "#8ba583", "#8ba583", "#8ba583", "#8ba583", "#8ba583"};
        }
        for(int i = 0; i < buttons.length; i++){
            Button button = buttons[i];
            BackgroundFill backgroundFill = new BackgroundFill(Paint.valueOf(colors[i]),  CornerRadii.EMPTY, Insets.EMPTY);
            Background background = new Background(backgroundFill);
            button.setBackground(background);
            BorderStroke borderStroke = new BorderStroke(Paint.valueOf("#3D3D3D"), BorderStrokeStyle.SOLID,  CornerRadii.EMPTY, new BorderWidths(1));
            Border border = new Border(borderStroke);
            button.setBorder(border);

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}