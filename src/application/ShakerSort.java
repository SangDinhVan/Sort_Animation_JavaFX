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

public class ShakerSort implements DataReceiver {

    @FXML
    private HBox hbox;

    @FXML
    private TextField textField0, textField1, textField2, textField3, textField4, textField5, textField6, textField7,
            textField8, textField9, textField10, textField11;

    private TextField[] textFields;
    private int[] values;

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

        first.setStyle("-fx-background-color: #ADD8E6;"); 
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

            if (callback != null) {
                callback.run();
            }
        });

        parallelTransition.play();
    }

    @FXML
    private void handleSwap() {
        int left = 0;
        int right = values.length - 1;
        shakeSort(left, right);
    }

    private void shakeSort(int left, int right) {
        int[] bounds = {left, right};
        shakeSortLeftToRight(bounds);
    }

    private void shakeSortLeftToRight(int[] bounds) {
        if (bounds[0] < bounds[1]) {
            performLeftToRightStep(bounds, bounds[0]);
        } else {
            finalizeSort();
        }
    }

    private void performLeftToRightStep(int[] bounds, int currentIndex) {
        if (currentIndex < bounds[1]) {
            if (values[currentIndex] > values[currentIndex + 1]) {
                swapFields(currentIndex, currentIndex + 1, () -> {
                    if (currentIndex + 1 == bounds[1]) {
                        textFields[currentIndex + 1].setStyle("-fx-background-color: #80EE98;");
                    }
                    performLeftToRightStep(bounds, currentIndex + 1);
                });
            } else {
                performLeftToRightStep(bounds, currentIndex + 1);
            }
        } else {
            textFields[bounds[1]].setStyle("-fx-background-color: #80EE98;");
            bounds[1]--;
            shakeSortRightToLeft(bounds);
        }
    }

    private void shakeSortRightToLeft(int[] bounds) {
        if (bounds[0] < bounds[1]) {
            performRightToLeftStep(bounds, bounds[1]);
        } else {
            finalizeSort();
        }
    }

    private void performRightToLeftStep(int[] bounds, int currentIndex) {
        if (currentIndex > bounds[0]) {
            if (values[currentIndex] < values[currentIndex - 1]) {
                swapFields(currentIndex, currentIndex - 1, () -> {
                    if (currentIndex - 1 == bounds[0]) {
                        textFields[currentIndex - 1].setStyle("-fx-background-color: #80EE98;");
                    }
                    performRightToLeftStep(bounds, currentIndex - 1);
                });
            } else {
                performRightToLeftStep(bounds, currentIndex - 1);
            }
        } else {
            textFields[bounds[0]].setStyle("-fx-background-color: #80EE98;");
            bounds[0]++;
            shakeSortLeftToRight(bounds);
        }
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
