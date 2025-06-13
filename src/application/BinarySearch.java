package application;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.util.Arrays;

public class BinarySearch implements DataReceiver {

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

    private int left;
    private int right;

    @FXML
    public void initialize() {
        textFields = new TextField[]{textField0, textField1, textField2, textField3, textField4, textField5,
                textField6, textField7, textField8, textField9, textField10, textField11};
    }

    @Override
    public void setArrayInput(String arrayInput) {
        String[] arrayValues = arrayInput.split(",");
        values = new int[arrayValues.length];

        for (int k = 0; k < arrayValues.length && k < textFields.length; k++) {
            try {
                int value = Integer.parseInt(arrayValues[k].trim());
                textFields[k].setText(String.valueOf(value));
                values[k] = value;
            } catch (NumberFormatException e) {
            	showAlert("Lỗi", "Vui lòng nhập giá trị hợp lệ trong mảng tại vị trí " + k);
                throw e;
            }
        }
    }




    private boolean loadValueSearch() {
        try {
            searchValue = Integer.parseInt(textFieldSearch.getText());
            return true;
        } catch (NumberFormatException e) {
            showAlert("Lỗi", "Vui lòng nhập giá trị cần tìm hợp lệ.");
            return false;
        }
    }

    @FXML
    private void handleSearch() {
        resetFieldStyles();
        if (!loadValueSearch()) {
            return;
        }
        sortValuesAndUpdateTextFields();
        left = 0;
        right = values.length - 1;
        result.setText("");
        binarySearchStep();
    }

    private void sortValuesAndUpdateTextFields() {
        Arrays.sort(values);
        for (int i = 0; i < textFields.length; i++) {
            textFields[i].setText(String.valueOf(values[i]));
        }
    }

    private void binarySearchStep() {
        if (left <= right) {
            highlightRange(left, right, "-fx-background-color: #ADD8E6;");
            int mid = left + (right - left) / 2;
            textFields[mid].setStyle("-fx-background-color: #FF6347;");

            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> {
                if (values[mid] == searchValue) {
                    textFields[mid].setStyle("-fx-background-color: #80EE98;");
                    result.setText("Đã tìm thấy giá trị tại vị trí " + mid);
                } else {
                    resetFieldStyles();
                    if (values[mid] < searchValue) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                    binarySearchStep();
                }
            });
            pause.play();
        } else {
            result.setText("Không tìm thấy giá trị " + searchValue);
        }
    }

    private void highlightRange(int start, int end, String style) {
        for (int i = 0; i < textFields.length; i++) {
            if (i >= start && i <= end) {
                textFields[i].setStyle(style);
                translateUp(textFields[i], 50);
            } else {
                textFields[i].setStyle("-fx-background-color: #F0F0F0;");
                translateUp(textFields[i], 0);
            }
        }
    }

    private void resetFieldStyles() {
        for (TextField textField : textFields) {
            textField.setStyle("-fx-background-color:#F0F0F0 ;");
            translateUp(textField, 0);
        }
    }

    private void translateUp(TextField textField, int offset) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), textField);
        transition.setToY(-offset);
        transition.play();
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
