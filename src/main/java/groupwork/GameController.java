package groupwork;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class GameController {
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
    void handlePlayerUpgradeButton(ActionEvent event) {
        statusLabel.setText("Upgrade!");
    }
    @FXML
    private void handleLogoutButton(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Parent root1 = FXMLLoader.load(getClass().getResource("resources/fxml/startGridPine.fxml"));
        Scene scene = new Scene(root1, 400, 600);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setScene(scene);
        double centerX = screenBounds.getMinX() + (screenBounds.getWidth() - scene.getWidth()) / 2;
        double centerY = screenBounds.getMinY() + (screenBounds.getHeight() - scene.getHeight()) / 2;
        stage.setX(centerX);
        stage.setY(centerY);
    }

    @FXML
    private TextField originTextField,destTextField,unitTextField;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/fxml/attack.fxml"));
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
            System.out.println("Attack: " + result.getKey() + ", : " + result.getValue());
        });
    }

    @FXML
    private TextField originTextFieldU, destTextFieldU, unitTextFieldU;
    @FXML
    private GridPane attackGridPaneU;

    @FXML
    private void handleMoveButton(ActionEvent event) throws IOException {
        // 创建一个移动对话框
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Move");
        dialog.setHeaderText("Please enter origin/destination/units");
        //add button
        ButtonType DoneButtonType = new ButtonType("Move", ButtonBar.ButtonData.OK_DONE);
        ButtonType CancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(DoneButtonType, CancelButtonType);
        // 从FXMLLoader实例中获取attackGridPane的引用
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/fxml/move.fxml"));
        attackGridPaneU = loader.load();
        dialog.getDialogPane().setContent(attackGridPaneU);
        originTextFieldU = (TextField) attackGridPaneU.lookup("#originTextFieldM");
        destTextFieldU = (TextField) attackGridPaneU.lookup("#destTextFieldM");
        unitTextFieldU = (TextField) attackGridPaneU.lookup("#unitTextFieldM");
        // 禁用确定按钮，除非文本框中有输入
        Node okButton = dialog.getDialogPane().lookupButton(DoneButtonType);
        okButton.setDisable(true);
        // 当文本框中有输入时，启用确定按钮
        originTextFieldU.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean disable = newValue.trim().isEmpty() || destTextFieldU.getText().isEmpty() || unitTextFieldU.getText().isEmpty();
            okButton.setDisable(disable);
        });
        destTextFieldU.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean disable = newValue.trim().isEmpty() || originTextFieldU.getText().isEmpty() || unitTextFieldU.getText().isEmpty();
            okButton.setDisable(disable);
        });
        unitTextFieldU.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean disable = newValue.trim().isEmpty() || destTextFieldU.getText().isEmpty() || originTextFieldU.getText().isEmpty();
            okButton.setDisable(disable);
        });
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == DoneButtonType) {
                return new Pair<>(originTextFieldU.getText(), destTextFieldU.getText());
            }
            return null;
        });

        // 显示对话框并等待用户响应
        dialog.showAndWait().ifPresent(result -> {
            System.out.println("Move: " + result.getKey() + ", : " + result.getValue());
        });


    }


    @FXML
    private TextField originTextFieldP, destTextFieldP, unitTextFieldP;
    @FXML
    private GridPane attackGridPaneP;

    @FXML
    private void handleUpgradeButton(ActionEvent event) throws IOException {
        // 创建一个移动对话框
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Upgrade");
        dialog.setHeaderText("Please enter origin/expected levels and units");
        //add button
        ButtonType DoneButtonType = new ButtonType("Upgrade", ButtonBar.ButtonData.OK_DONE);
        ButtonType CancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(DoneButtonType, CancelButtonType);
        // 从FXMLLoader实例中获取attackGridPane的引用
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/fxml/upgrade.fxml"));
        attackGridPaneP = loader.load();
        dialog.getDialogPane().setContent(attackGridPaneP);
        originTextFieldP = (TextField) attackGridPaneP.lookup("#currentLevel");
        destTextFieldP = (TextField) attackGridPaneP.lookup("#upGradeNumber");
        unitTextFieldP = (TextField) attackGridPaneP.lookup("#expectedLevel");
        // 禁用确定按钮，除非文本框中有输入
        Node okButton = dialog.getDialogPane().lookupButton(DoneButtonType);
        okButton.setDisable(true);
        // 当文本框中有输入时，启用确定按钮
        originTextFieldP.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean disable = newValue.trim().isEmpty() || destTextFieldP.getText().isEmpty() || unitTextFieldP.getText().isEmpty();
            okButton.setDisable(disable);
        });
        destTextFieldP.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean disable = newValue.trim().isEmpty() || originTextFieldP.getText().isEmpty() || unitTextFieldP.getText().isEmpty();
            okButton.setDisable(disable);
        });
        unitTextFieldP.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean disable = newValue.trim().isEmpty() || destTextFieldP.getText().isEmpty() || originTextFieldP.getText().isEmpty();
            okButton.setDisable(disable);
        });
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == DoneButtonType) {
                return new Pair<>(originTextFieldP.getText(), destTextFieldP.getText());
            }
            return null;
        });

        // 显示对话框并等待用户响应
        dialog.showAndWait().ifPresent(result -> {
            System.out.println("Upgrade: " + result.getKey() + ", : " + result.getValue());
        });


    }

    static Map<String, Scene> scenes = new HashMap<>();
    static int num = 0;

    @FXML
    public void handleSwitchButton(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        //find a label in the current stage
        if(num == 0) {
            //get current scene into scenes
            Scene scene = stage.getScene();
            Label gameIdLabel = (Label) scene.lookup("#GameId");
            String gameId = gameIdLabel.getText();
            scenes.put("gameGrid" + gameId, scene);
            num = Integer.parseInt(gameId);
        }
        if(num == 1) {
            if(scenes.containsKey("gameGrid2")) {
                stage.setScene(scenes.get("gameGrid2"));
                stage.show();
                System.out.println("gameGrid211");
            }
            else {
                Parent root1 = FXMLLoader.load(getClass().getResource("resources/fxml/gameGrid.fxml"));
                Label gameIdLabel = (Label) root1.lookup("#GameId");
                gameIdLabel.setText("2");
                Scene scene = new Scene(root1, 800, 600);
                stage.setScene(scene);
                stage.show();
                System.out.println("gameGrid212");

                scenes.put("gameGrid2", scene);

            }
            num = 2;
        }
        else {
            if(scenes.containsKey("gameGrid1")) {
                stage.setScene(scenes.get("gameGrid1"));
                stage.show();
                System.out.println("gameGrid111");
            }
            else {
                Parent root1 = FXMLLoader.load(getClass().getResource("resources/fxml/gameGrid.fxml"));
                Label gameIdLabel = (Label) root1.lookup("#GameId");
                gameIdLabel.setText("1");
                Scene scene = new Scene(root1, 800, 600);
                stage.setScene(scene);
                stage.show();
                System.out.println("gameGrid112");
                scenes.put("gameGrid1", scene);

            }
            num = 1;
        }
    }

}
