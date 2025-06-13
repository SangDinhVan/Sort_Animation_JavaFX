package application;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;

public class RadixSort implements DataReceiver {

    @FXML
    private HBox inputHBox;

    @FXML
    private TextField textfield0, textfield1, textfield2, textfield3, textfield4,
            textfield5, textfield6, textfield7, textfield8, textfield9,
            textfield10, textfield11;

    @FXML
    private TextField textField00, textField01, textField02, textField03, textField04,
            textField05, textField06, textField07, textField08, textField09,
            textField010, textField011, textField012;

    @FXML
    private TextField textField10, textField11, textField12, textField13, textField14,
            textField15, textField16, textField17, textField18, textField19,
            textField110, textField111, textField112;

    @FXML
    private TextField textField20, textField21, textField22, textField23, textField24,
            textField25, textField26, textField27, textField28, textField29,
            textField210, textField211, textField212;

    @FXML
    private TextField textField30, textField31, textField32, textField33, textField34,
            textField35, textField36, textField37, textField38, textField39,
            textField310, textField311, textField312;

    @FXML
    private TextField textField40, textField41, textField42, textField43, textField44,
            textField45, textField46, textField47, textField48, textField49,
            textField410, textField411, textField412;

    @FXML
    private TextField textField50, textField51, textField52, textField53, textField54,
            textField55, textField56, textField57, textField58, textField59,
            textField510, textField511, textField512;

    @FXML
    private TextField textField60, textField61, textField62, textField63, textField64,
            textField65, textField66, textField67, textField68, textField69,
            textField610, textField611, textField612;

    @FXML
    private TextField textField70, textField71, textField72, textField73, textField74,
            textField75, textField76, textField77, textField78, textField79,
            textField710, textField711, textField712;

    @FXML
    private TextField textField80, textField81, textField82, textField83, textField84,
            textField85, textField86, textField87, textField88, textField89,
            textField810, textField811, textField812;

    @FXML
    private TextField textField90, textField91, textField92, textField93, textField94,
            textField95, textField96, textField97, textField98, textField99,
            textField910, textField911, textField912;

    private TextField[] textfields;
    private TextField[][] textFields;
    private int[] values;

    @FXML
    public void initialize() {
        textfields = new TextField[]{
                textfield0, textfield1, textfield2, textfield3, textfield4,
                textfield5, textfield6, textfield7, textfield8, textfield9,
                textfield10, textfield11
        };

        textFields = new TextField[][]{
                {textField00, textField01, textField02, textField03, textField04, textField05, textField06, textField07, textField08, textField09, textField010, textField011, textField012},
                {textField10, textField11, textField12, textField13, textField14, textField15, textField16, textField17, textField18, textField19, textField110, textField111, textField112},
                {textField20, textField21, textField22, textField23, textField24, textField25, textField26, textField27, textField28, textField29, textField210, textField211, textField212},
                {textField30, textField31, textField32, textField33, textField34, textField35, textField36, textField37, textField38, textField39, textField310, textField311, textField312},
                {textField40, textField41, textField42, textField43, textField44, textField45, textField46, textField47, textField48, textField49, textField410, textField411, textField412},
                {textField50, textField51, textField52, textField53, textField54, textField55, textField56, textField57, textField58, textField59, textField510, textField511, textField512},
                {textField60, textField61, textField62, textField63, textField64, textField65, textField66, textField67, textField68, textField69, textField610, textField611, textField612},
                {textField70, textField71, textField72, textField73, textField74, textField75, textField76, textField77, textField78, textField79, textField710, textField711, textField712},
                {textField80, textField81, textField82, textField83, textField84, textField85, textField86, textField87, textField88, textField89, textField810, textField811, textField812},
                {textField90, textField91, textField92, textField93, textField94, textField95, textField96, textField97, textField98, textField99, textField910, textField911, textField912}
        };

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 12; j++) {
                textFields[i][j].setVisible(false);
            }
        }
    }

    @Override
    public void setArrayInput(String arrayInput) {
        String[] arrayValues = arrayInput.split(",");
        values = new int[arrayValues.length];
        for (int k = 0; k < arrayValues.length && k < textfields.length; k++) {
            try {
                int value = Integer.parseInt(arrayValues[k].trim());
                textfields[k].setText(String.valueOf(value));
                values[k] = value;
            } catch (NumberFormatException e) {
            	showAlert("Lỗi", "Vui lòng nhập giá trị hợp lệ trong mảng tại vị trí " + k, AlertType.ERROR);
                throw e;
            }
        }
    }

    private void loadValuesFromTextFields() {
        values = new int[textfields.length];
        for (int k = 0; k < textfields.length; k++) {
            try {
                values[k] = Integer.parseInt(textfields[k].getText());
            } catch (NumberFormatException e) {
            	showAlert("Lỗi", "Vui lòng nhập giá trị hợp lệ trong mảng tại vị trí " + k, AlertType.ERROR);
                throw e;
            }
        }
    }

    @FXML
    private void handleSort() {
        for (TextField textField : textfields) {
            if (textField.getText() == null || textField.getText().trim().isEmpty()) {
                showAlert("Lỗi", "Vui lòng xác nhận giá trị mảng. Một hoặc nhiều ô trống.", Alert.AlertType.ERROR);
                return;
            }
        }
        loadValuesFromTextFields();

        new Thread(() -> {
            radixSort(values);
            Platform.runLater(() -> {
                for (int i = 0; i < values.length; i++) {
                    textfields[i].setText(String.valueOf(values[i]));
                }
            });
        }).start();
    }

    private void radixSort(int[] arr) {
        int max = getMax(arr);
//        int maxDigits = String.valueOf(max).length();
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    private int getMax(int[] arr) {
        int max = arr[0];
        for (int val : arr) {
            if (val > max)
                max = val;
        }
        return max;
    }

    
    private void countingSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        int[] countCopy = new int[10];
        System.arraycopy(count, 0, countCopy, 0, 10);
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            int row = digit;
            int col = count[digit] - 1 - (digit > 0 ? countCopy[digit - 1] : 0);
            int index = i;
            int value = arr[i];
            Platform.runLater(() -> {
                textfields[index].setText("");
                textFields[row][col].setText(String.valueOf(value));
                textFields[row][col].setVisible(true);
            });
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count[digit]--;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
            int value = arr[i];
            int digit = (value / exp) % 10;
            int col = i - (digit > 0 ? countCopy[digit - 1] : 0);
            int index = i;
            Platform.runLater(() -> {
                textfields[index].setText(String.valueOf(value));
                textFields[digit][col].setText("");
                textFields[digit][col].setVisible(false);
            });
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
