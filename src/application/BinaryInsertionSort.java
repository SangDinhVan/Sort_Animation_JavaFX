package application;

import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class BinaryInsertionSort implements DataReceiver {

    @FXML
    private HBox hbox;

    @FXML
    private TextField textField0, textField1, textField2, textField3, textField4, textField5,
            textField6, textField7, textField8, textField9, textField10, textField11;


    private TextField[] textFields;
    private int[] values; 

    @FXML
    public void initialize() {
        textFields = new TextField[] { textField0, textField1, textField2, textField3, textField4, textField5,
                textField6, textField7, textField8, textField9, textField10, textField11 };
    }
    
    @Override
    public void setArrayInput(String arrayInput) {
        String[] arrayValues = arrayInput.split(",");
        values = new int[arrayValues.length];

        for (int k = 0; k < arrayValues.length && k < textFields.length; k++) {
            try {
                int value = Integer.parseInt(arrayValues[k].trim());
                textFields[k].setText(String.valueOf(value)); 
                try {
                    values[k] = Integer.parseInt(textFields[k].getText());
                } catch (NumberFormatException e) {
                	showAlert("Lỗi", "Vui lòng nhập giá trị hợp lệ trong mảng tại vị trí " + k, AlertType.ERROR);
                    throw e;
                }
                textFields[k].setStyle("-fx-background-color: #F0F0F0;"); 
            } catch (NumberFormatException e) {
                showAlert("Lỗi", "Vui lòng nhập giá trị hợp lệ trong mảng tại vị trí " + k, AlertType.ERROR);
                throw e;
            }
        }
    }
    

    @FXML
    private void handleSwap() {
        binaryInsertionSort();
    }

    private void binaryInsertionSort() {
        binaryInsertionSortStep(1);
    }

    private void binaryInsertionSortStep(int i) {
        if (i < values.length) {
            int key = values[i];
            TextField keyField = textFields[i];

            keyField.setStyle("-fx-background-color: #FF6347;"); 

            int insertPos = binarySearch(values, key, 0, i - 1);

            if (insertPos == i) {
                keyField.setStyle("-fx-background-color: #80EE98;");
                binaryInsertionSortStep(i + 1);
            } else {
                shiftElementsRight(insertPos, i, () -> {
                    values[insertPos] = key;
                    textFields[insertPos].setText(String.valueOf(key));

                    textFields[insertPos].setStyle("-fx-background-color: #80EE98;");

                    textFields[i].setStyle("-fx-background-color: #F0F0F0;");

                    binaryInsertionSortStep(i + 1);
                });
            }
        } else {
            finalizeSort();
        }
    }

    private int binarySearch(int[] arr, int key, int low, int high) {
        if (high <= low) {
            return (key > arr[low]) ? (low + 1) : low;
        }
        int mid = (low + high) / 2;

        if (key == arr[mid]) {
            return mid + 1;
        }

        if (key > arr[mid]) {
            return binarySearch(arr, key, mid + 1, high);
        } else {
            return binarySearch(arr, key, low, mid - 1);
        }
    }

    private void shiftElementsRight(int fromIndex, int toIndex, Runnable callback) {

        TextField keyField = textFields[toIndex];
        keyField.setStyle("-fx-background-color: #FF6347;"); //cam

        TranslateTransition moveUp = new TranslateTransition(Duration.millis(500), keyField);
        moveUp.setByY(-100);

        double shiftDistance = keyField.getWidth() + hbox.getSpacing();

        List<TranslateTransition> shiftRightTransitions = new ArrayList<>();

        for (int index = fromIndex; index < toIndex; index++) {
            TextField textField = textFields[index];
            textField.setStyle("-fx-background-color: #ADD8E6;");
            TranslateTransition transition = new TranslateTransition(Duration.millis(500), textField);
            transition.setByX(shiftDistance);
            shiftRightTransitions.add(transition);
        }

        ParallelTransition shiftRight = new ParallelTransition();
        shiftRight.getChildren().addAll(shiftRightTransitions);

        TranslateTransition moveHorizontal = new TranslateTransition(Duration.millis(500), keyField);
        moveHorizontal.setByX(-(toIndex - fromIndex) * shiftDistance);

        TranslateTransition moveDown = new TranslateTransition(Duration.millis(500), keyField);
        moveDown.setByY(100);

        SequentialTransition sequentialTransition = new SequentialTransition(moveUp, shiftRight, moveHorizontal, moveDown);

        sequentialTransition.setOnFinished(e -> {
            int keyValue = values[toIndex];
            TextField keyTextField = textFields[toIndex];

                      if (toIndex - fromIndex >= 0) {
                System.arraycopy(values, fromIndex, values, fromIndex + 1, toIndex - fromIndex);
                System.arraycopy(textFields, fromIndex, textFields, fromIndex + 1, toIndex - fromIndex);
            }

            values[fromIndex] = keyValue;
            textFields[fromIndex] = keyTextField;
            textFields[fromIndex].setText(String.valueOf(keyValue));

            for (int index = fromIndex; index <= toIndex; index++) {
                textFields[index].setTranslateX(0);
                textFields[index].setTranslateY(0);
                textFields[index].setStyle("-fx-background-color: #F0F0F0;"); 
            }

            hbox.getChildren().clear();
            for (TextField textField : textFields) {
                hbox.getChildren().add(textField);
            }

            if (callback != null) {
                callback.run();
            }
        });

        sequentialTransition.play();
    }

    private void finalizeSort() {
        for (int k = 0; k < values.length; k++) {
            textFields[k].setStyle("-fx-background-color: #80EE98;");
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait(); 
    }
}
