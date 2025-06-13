package application;

import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class BubbleSort implements DataReceiver {

    @FXML
    private HBox hbox;

    @FXML
    private TextField textField0, textField1, textField2, textField3, textField4, textField5,
            textField6, textField7, textField8, textField9, textField10, textField11;

    private TextField[] textFields;
    private int[] values;
    private int i = 0;
    private int j = 0;

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
                textFields[k].setStyle("-fx-background-color: #F0F0F0;"); 
            } catch (NumberFormatException e) {
                showAlert("Lỗi", "Vui lòng nhập giá trị hợp lệ trong mảng tại vị trí " + k, AlertType.ERROR);
                throw e;
            }
        }
    }



    public void swapFields(int index1, int index2, Runnable callback) {
        int tempValue = values[index1];
        values[index1] = values[index2];
        values[index2] = tempValue;

        TextField first = textFields[index1];
        TextField second = textFields[index2];

        first.setStyle("-fx-background-color: #ADD8E6;"); // Màu xanh lam nhạt
        second.setStyle("-fx-background-color: #FF6347;");

        double distanceX = second.getLayoutX() - first.getLayoutX();

        TranslateTransition moveFirstDown = new TranslateTransition(Duration.millis(400), first);
        moveFirstDown.setByY(-50);

        TranslateTransition moveSecondUp = new TranslateTransition(Duration.millis(400), second);
        moveSecondUp.setByY(+50);

        TranslateTransition moveFirstLeft = new TranslateTransition(Duration.millis(400), first);
        moveFirstLeft.setByX(distanceX);

        TranslateTransition moveSecondRight = new TranslateTransition(Duration.millis(400), second);
        moveSecondRight.setByX(-distanceX);

        TranslateTransition moveFirstUp = new TranslateTransition(Duration.millis(400), first);
        moveFirstUp.setByY(50);

        TranslateTransition moveSecondDown = new TranslateTransition(Duration.millis(400), second);
        moveSecondDown.setByY(-50);

        SequentialTransition firstSequential = new SequentialTransition(moveFirstDown, moveFirstLeft, moveFirstUp);
        SequentialTransition secondSequential = new SequentialTransition(moveSecondUp, moveSecondRight, moveSecondDown);

        ParallelTransition parallelTransition = new ParallelTransition(firstSequential, secondSequential);

        parallelTransition.setOnFinished(e -> {
            String temp = first.getText();
            first.setText(second.getText());
            second.setText(temp);

            first.setTranslateX(0);
            first.setTranslateY(0);
            second.setTranslateX(0);
            second.setTranslateY(0);

            first.setStyle("-fx-background-color: #F0F0F0;");
            second.setStyle("-fx-background-color: #F0F0F0;");

            updateBackgroundColors();

            if (callback != null) {
                callback.run();
            }
        });

        parallelTransition.play();
    }

    @FXML
    private void handleSwap() {
        i = 0;
        j = 0;
        bubbleSortStep();
    }


    private void bubbleSortStep() {
        if (i < values.length - 1) {
            if (j < values.length - i - 1) {
                if (values[j] > values[j + 1]) {
                    swapFields(j, j + 1, () -> {
                        j++;
                        bubbleSortStep();
                    });
                } else {
                    j++;
                    bubbleSortStep();
                }
            } else {
                j = 0;
                i++;
                updateBackgroundColors();
                bubbleSortStep();
            }
        } else {
            i++; 
            updateBackgroundColors();
        }
    }

    private void updateBackgroundColors() {
        for (int k = 0; k < textFields.length; k++) {
            if (k >= values.length - i) {
                textFields[k].setStyle("-fx-background-color: #80EE98;");
            } else {
                textFields[k].setStyle("-fx-background-color: #F0F0F0;");
            }
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
