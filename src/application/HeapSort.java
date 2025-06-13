package application;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class HeapSort implements DataReceiver {
    @FXML
    private HBox hbox;

    @FXML
    private TextField textField0, textField1, textField2, textField3, textField4, textField5,
            textField6, textField7, textField8, textField9, textField10, textField11;

    @FXML
    private TextField textField02, textField12, textField22, textField32, textField42, textField52,
            textField62, textField72, textField82, textField92, textField102, textField112;

    private TextField[] textFieldsTree;
    private TextField[] textFieldsArray;
    private int[] values;
    private int heapSize;
    private int sortedIndex; // Tracks the number of sorted elements

    @FXML
    public void initialize() {
        textFieldsTree = new TextField[]{textField0, textField1, textField2, textField3, textField4, textField5,
                textField6, textField7, textField8, textField9, textField10, textField11};

        textFieldsArray = new TextField[]{textField02, textField12, textField22, textField32, textField42, textField52,
                textField62, textField72, textField82, textField92, textField102, textField112};

        // Initialize background colors
        for (TextField tf : textFieldsTree) {
            tf.setStyle("-fx-background-color: #F0F0F0;");
        }
        for (TextField tf : textFieldsArray) {
            tf.setStyle("-fx-background-color: #F0F0F0;");
        }
    }

    @Override
    public void setArrayInput(String arrayInput) {
        String[] arrayValues = arrayInput.split(",");
        values = new int[arrayValues.length];

        for (int k = 0; k < arrayValues.length && k < textFieldsArray.length; k++) {
            try {
                int value = Integer.parseInt(arrayValues[k].trim());
                textFieldsArray[k].setText(String.valueOf(value));
                textFieldsTree[k].setText(String.valueOf(value));
                values[k] = value;
                textFieldsArray[k].setStyle("-fx-background-color: #F0F0F0;"); 
                textFieldsTree[k].setStyle("-fx-background-color: #F0F0F0;"); 
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

        TextField firstTree = textFieldsTree[index1];
        TextField secondTree = textFieldsTree[index2];

        TextField firstArray = textFieldsArray[index1];
        TextField secondArray = textFieldsArray[index2];

        firstTree.setStyle("-fx-background-color: #ADD8E6;"); // Màu xanh lam nhạt
        secondTree.setStyle("-fx-background-color: #FF6347;");
        firstArray.setStyle("-fx-background-color: #ADD8E6;"); 
        secondArray.setStyle("-fx-background-color: #FF6347;");

        double deltaX = secondTree.getLayoutX() - firstTree.getLayoutX();
        double deltaY = secondTree.getLayoutY() - firstTree.getLayoutY();

        TranslateTransition moveFirstTree = new TranslateTransition(Duration.millis(1000), firstTree);
        moveFirstTree.setByX(deltaX);
        moveFirstTree.setByY(deltaY);

        TranslateTransition moveSecondTree = new TranslateTransition(Duration.millis(1000), secondTree);
        moveSecondTree.setByX(-deltaX);
        moveSecondTree.setByY(-deltaY);

        TranslateTransition moveFirstArray = new TranslateTransition(Duration.millis(1000), firstArray);
        moveFirstArray.setByX(secondArray.getLayoutX() - firstArray.getLayoutX());

        TranslateTransition moveSecondArray = new TranslateTransition(Duration.millis(1000), secondArray);
        moveSecondArray.setByX(firstArray.getLayoutX() - secondArray.getLayoutX());

        ParallelTransition parallelTransition = new ParallelTransition(moveFirstTree, moveSecondTree, moveFirstArray, moveSecondArray);

        parallelTransition.setOnFinished(e -> {
            String tempTree = firstTree.getText();
            firstTree.setText(secondTree.getText());
            secondTree.setText(tempTree);

            String tempArray = firstArray.getText();
            firstArray.setText(secondArray.getText());
            secondArray.setText(tempArray);

            firstTree.setTranslateX(0);
            firstTree.setTranslateY(0);
            secondTree.setTranslateX(0);
            secondTree.setTranslateY(0);

            firstArray.setTranslateX(0);
            secondArray.setTranslateX(0);

            firstTree.setStyle("-fx-background-color: #F0F0F0;");
            secondTree.setStyle("-fx-background-color: #F0F0F0;");
            firstArray.setStyle("-fx-background-color: #F0F0F0;");
            secondArray.setStyle("-fx-background-color: #F0F0F0;");


            updateBackgroundColors();

            if (callback != null) {
                callback.run();
            }
        });

        parallelTransition.play();
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleSwap() {

        heapSize = values.length;
        sortedIndex = values.length;
        heapSort(heapSize - 1);
    }

    private void buildMaxHeap(int i, Runnable callback) {
        if (i < 0) {
            if (callback != null) {
                callback.run();
            }
            return;
        }

        buildHeapStep(i, () -> buildMaxHeap(i - 1, callback));
    }

    private void buildHeapStep(int i, Runnable callback) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < heapSize && values[left] > values[largest]) {
            largest = left;
        }

        if (right < heapSize && values[right] > values[largest]) {
            largest = right;
        }

        if (largest != i) {
            final int f_largest = largest;
            swapFields(i, largest, () -> buildHeapStep(f_largest, callback));
        } else if (callback != null) {
            callback.run();
        }
    }

    private void heapSort(int currentIndex) {
        if (currentIndex < 0) {
            updateBackgroundColors();
            return;
        }

        buildMaxHeap(heapSize / 2 - 1, () -> {
            swapFields(0, currentIndex, () -> {

                sortedIndex = currentIndex;
                updateBackgroundColors();

                heapSize--;
                heapSort(currentIndex - 1);
            });
        });
    }

    private void updateBackgroundColors() {

        for (int k = 0; k < textFieldsTree.length; k++) {
            if (k >= sortedIndex) {
                textFieldsTree[k].setStyle("-fx-background-color: #80EE98;");
            } else {
                textFieldsTree[k].setStyle("-fx-background-color: #F0F0F0;");
            }
        }


        for (int k = 0; k < textFieldsArray.length; k++) {
            if (k >= sortedIndex) {
                textFieldsArray[k].setStyle("-fx-background-color: #80EE98;");
            } else {
                textFieldsArray[k].setStyle("-fx-background-color: #F0F0F0;");
            }
        }
    }
}
