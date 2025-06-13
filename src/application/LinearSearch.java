package application;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class LinearSearch implements DataReceiver {

    @FXML
    private HBox hbox;

    @FXML
    private TextField textField0, textField1, textField2, textField3, textField4, textField5,
            textField6, textField7, textField8, textField9, textField10, textField11;

    @FXML 
    private TextField textFieldSearch;

    @FXML
    private Label result;

    private TextField[] textFields;
    private int[] values;
    private int searchValue;
    private int i = 0;

    @Override
    public void setArrayInput(String arrayInput) {
        String[] arrayValues = arrayInput.split(",");
        values = new int[arrayValues.length];

        for (int k = 0; k < arrayValues.length && k < textFields.length; k++) {
            try {
                int value = Integer.parseInt(arrayValues[k].trim());
                textFields[k].setText(String.valueOf(value));
                values[k] = value;
                textFields[k].setStyle("-fx-background-color: #F0F0F0;"); 
            } catch (NumberFormatException e) {
            	showAlert("Lỗi", "Vui lòng nhập giá trị hợp lệ trong mảng tại vị trí " + k);
                throw e;
            }
        }
    }

    @FXML
    public void initialize() {
        textFields = new TextField[]{textField0, textField1, textField2, textField3, textField4, textField5,
                textField6, textField7, textField8, textField9, textField10, textField11};
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean loadValueSearch() {
        try {
            searchValue = Integer.parseInt(textFieldSearch.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @FXML
    private void handleSearch() {
        resetFieldStyles();
        if (!loadValueSearch()) {
            showAlert("Lỗi", "Vui lòng nhập giá trị cần tìm hợp lệ.");
            return;
        }
        i = 0;
        result.setText("");
        linearSearchStep();
    }

    private void linearSearchStep() {
        if (i < values.length) {
            textFields[i].setStyle("-fx-background-color: #ADD8E6;"); // Màu xanh lam nhạt;
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(event -> {
                if (values[i] == searchValue) {
                    textFields[i].setStyle("-fx-background-color: #80EE98;");
                    result.setText("Đã tìm thấy giá trị tại vị trí: " + i);
                } else {
                    textFields[i].setStyle("-fx-background-color: #F0F0F0;");
                    i++;
                    linearSearchStep();
                }
            });
            pause.play();
        } else {
            result.setText("Không tìm thấy giá trị " + searchValue);
        }
    }

    private void resetFieldStyles() {
        for (TextField textField : textFields) {
            textField.setStyle("-fx-background-color: #F0F0F0;");
        }
    }
}
