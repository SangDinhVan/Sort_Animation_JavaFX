package application;

import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;

public class QuickSort implements DataReceiver {

    @FXML
    private HBox hbox;

    @FXML
    private TextField textField0, textField1, textField2, textField3, textField4, textField5,
            textField6, textField7, textField8, textField9, textField10, textField11;

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

        TranslateTransition moveFirstDown = new TranslateTransition(Duration.millis(300), first);
        moveFirstDown.setByY(-50);

        TranslateTransition moveSecondUp = new TranslateTransition(Duration.millis(300), second);
        moveSecondUp.setByY(+50);

        TranslateTransition moveFirstLeft = new TranslateTransition(Duration.millis(300), first);
        moveFirstLeft.setByX(distanceX);

        TranslateTransition moveSecondRight = new TranslateTransition(Duration.millis(300), second);
        moveSecondRight.setByX(-distanceX);

        TranslateTransition moveFirstUp = new TranslateTransition(Duration.millis(300), first);
        moveFirstUp.setByY(50);

        TranslateTransition moveSecondDown = new TranslateTransition(Duration.millis(300), second);
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
        quickSort(0, values.length - 1, () -> {
        });
    }

    private void quickSort(int low, int high, Runnable callback) {
        if (low < high) {
            partition(low, high, pivotIndex -> {
                
                quickSort(low, pivotIndex - 1, () -> {
                    
                    quickSort(pivotIndex + 1, high, callback);
                });
            });
        } else {
            if (low == high) {
                TextField field = textFields[low];
                field.setStyle("-fx-background-color: #80EE98;"); 
            }
            if (callback != null) {
                callback.run();
            }
        }
    }

    private void partition(int low, int high, RunnableWithPivot callback) {
        TextField pivotField = textFields[high];
        pivotField.setStyle("-fx-background-color: #FFD700;"); // Vàng cho pivot

        AtomicInteger i = new AtomicInteger(low - 1);

        partitionStep(low, high, i, pivotIndex -> {
            textFields[pivotIndex].setStyle("-fx-background-color: #80EE98;"); 
            callback.run(pivotIndex);
        });
    }

    private void partitionStep(int j, int high, AtomicInteger i, RunnableWithPivot callback) {
        if (j < high) {
            TextField currentField = textFields[j];
            currentField.setStyle("-fx-background-color: #FADADD;"); 

            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(e -> {
                if (values[j] < values[high]) {
                    i.incrementAndGet();
                    int k = i.get();

                    swapFields(k, j, () -> {
                        currentField.setStyle("-fx-background-color: #F0F0F0;");
                        partitionStep(j + 1, high, i, callback);
                    });
                } else {
                    currentField.setStyle("-fx-background-color: #FADADD;");                  
                    PauseTransition resetColorPause = new PauseTransition(Duration.seconds(0.3));
                    resetColorPause.setOnFinished(event -> {
                        currentField.setStyle("-fx-background-color: #F0F0F0;");
                        partitionStep(j + 1, high, i, callback);
                    });
                    resetColorPause.play();
                }
            });
            pause.play();
        } else {
            int k = i.get() + 1;
            swapFields(k, high, () -> {
                textFields[k].setStyle("-fx-background-color: #80EE98;"); 
                callback.run(k);
            });
        }
    }

    @FunctionalInterface
    interface RunnableWithPivot {
        void run(int pivotIndex);
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
