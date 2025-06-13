package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.Random;

public class MainController {
    
    @FXML
    private Pane contentPane;
    @FXML 
    private VBox vbox;
    @FXML
    private TextField inputArray; 
    @FXML
    private Button btnExit;
    @FXML
    private Label algorithmDescription; 
    @FXML
    private DataReceiver selectedAlgorithmController;
    
    private String arrayInput;

    private void loadAlgorithmView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent algorithmView = loader.load();
            selectedAlgorithmController = loader.getController();
            contentPane.getChildren().clear();
            contentPane.getChildren().add(algorithmView);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Lỗi", "Không thể tải tệp FXML.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void showBubbleSort() {
        String description = "Thuật toán Bubble Sort lặp qua danh sách và hoán đổi các phần tử kề nhau nếu chúng đang ở sai thứ tự. "
                             + "Quá trình này lặp lại cho đến khi danh sách được sắp xếp.\n"
                             + "Độ phức tạp: O(n^2)";
        algorithmDescription.setText(description);
        loadAlgorithmView("/application/BubbleSort.fxml");
    }

    @FXML
    private void showInterchangeSort() {
        String description = "Thuật toán Interchange Sort liên tục hoán đổi các phần tử sai vị trí cho đến khi danh sách được sắp xếp.\n"
                             + "Độ phức tạp: O(n^2)";
        algorithmDescription.setText(description);
        loadAlgorithmView("/application/InterchangeSort.fxml");
    }

    @FXML
    private void showInsertionSort() {
        String description = "Thuật toán Insertion Sort chèn từng phần tử từ danh sách chưa sắp xếp vào danh sách đã sắp xếp cho đến khi toàn bộ mảng được sắp xếp.\n"
                             + "Độ phức tạp: O(n^2)";
        algorithmDescription.setText(description);
        loadAlgorithmView("/application/InsertionSort.fxml");
    }

    @FXML
    private void showSelectionSort() {
        String description = "Selection Sort tìm phần tử nhỏ nhất và hoán đổi nó với phần tử đầu tiên trong danh sách chưa sắp xếp. "
                             + "Quá trình lặp lại cho đến khi mảng được sắp xếp.\n"
                             + "Độ phức tạp: O(n^2)";
        algorithmDescription.setText(description);
        loadAlgorithmView("/application/SelectionSort.fxml");
    }

    @FXML
    private void showMergeSort() {
        String description = "Merge Sort chia mảng thành hai nửa và tiếp tục chia cho đến khi còn một phần tử. "
                             + "Sau đó, các phần tử được trộn lại theo đúng thứ tự để tạo ra mảng đã sắp xếp.\n"
                             + "Độ phức tạp: O(n log n)";
        algorithmDescription.setText(description);
        loadAlgorithmView("/application/MergeSort.fxml");
    }

    @FXML
    private void showHeapSort() {
        String description = "Heap Sort xây dựng một Heap từ mảng cần sắp xếp, sau đó lần lượt lấy phần tử lớn nhất (hoặc nhỏ nhất) từ Heap và hoán đổi nó với phần tử cuối cùng trong mảng chưa sắp xếp, rồi giảm kích thước của Heap và lặp lại quá trình.\n"
                             + "Độ phức tạp: O(n log n)";
        algorithmDescription.setText(description);
        loadAlgorithmView("/application/HeapSort.fxml");
    }

    @FXML
    private void showLinearSearch() {
        String description = "Linear Search tìm kiếm từng phần tử trong mảng cho đến khi tìm thấy kết quả.\n"
                             + "Độ phức tạp: O(n), với n là số lượng phần tử trong mảng.";
        algorithmDescription.setText(description);
        loadAlgorithmView("/application/LinearSearch.fxml");
    }

    @FXML
    private void showBinarySearch() {
        String description = "Binary Search tìm kiếm phần tử trong mảng đã sắp xếp bằng cách chia đôi mảng.\n"
                             + "Độ phức tạp: O(log n), với n là số lượng phần tử trong mảng.";
        algorithmDescription.setText(description);
        loadAlgorithmView("/application/BinarySearch.fxml");
    }

    @FXML
    private void showRadixSort() {
        String description = "Radix Sort sắp xếp các số theo từng chữ số, từ hàng thấp đến cao.\n"
                             + "Độ phức tạp: O(n * k), với n là số phần tử và k là số chữ số lớn nhất.";
        algorithmDescription.setText(description);
        loadAlgorithmView("/application/RadixSort.fxml");
    }

    @FXML
    private void showQuickSort() {
        String description = "QuickSort sắp xếp bằng cách chọn một điểm pivot và phân chia mảng.\n"
                             + "Độ phức tạp: O(n log n) trong trường hợp trung bình.";
        algorithmDescription.setText(description);
        loadAlgorithmView("/application/QuickSort.fxml");
    }

    @FXML
    private void showShakerSort() {
        String description = "Shaker Sort là một biến thể của Bubble Sort, sắp xếp theo cả hai chiều.\n"
                             + "Độ phức tạp: O(n²) trong trường hợp xấu nhất.";
        algorithmDescription.setText(description);
        loadAlgorithmView("/application/ShakerSort.fxml");
    }

    @FXML
    private void showShellSort() {
        String description = "Shell Sort sắp xếp bằng cách so sánh các phần tử cách nhau một khoảng và giảm dần khoảng cách.\n"
                             + "Độ phức tạp: O(n log² n) trong trường hợp trung bình.";
        algorithmDescription.setText(description);
        loadAlgorithmView("/application/ShellSort.fxml");
    }

    @FXML
    private void showBinaryInsertionSort() {
        String description = "Binary Insertion Sort sử dụng Binary Search để tìm vị trí chèn phần tử.\n"
                             + "Độ phức tạp: O(n²) trong trường hợp xấu nhất.";
        algorithmDescription.setText(description);
        loadAlgorithmView("/application/BinaryInsertionSort.fxml");
    }

    private boolean validateInputFormat(String input) {
        String regex = "^\\d+(,\\d+){11}$";
        return input.matches(regex);
    }

    @FXML
    private void confirmInputOnly() {
        arrayInput = inputArray.getText();
        if (selectedAlgorithmController != null && arrayInput != null && !arrayInput.isEmpty()) {
            if (validateInputFormat(arrayInput)) {
                selectedAlgorithmController.setArrayInput(arrayInput);
            } else {
                showAlert("Lỗi", "Dữ liệu không hợp lệ. Vui lòng nhập 12 số và các số cách nhau bằng dấu phẩy.", Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Lỗi", "Vui lòng chọn thuật toán và nhập dữ liệu.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void resetInputField() {
        inputArray.setText("");
    }

    @FXML
    private void generateRandomNumbers() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            int randomNumber = random.nextInt(99);
            sb.append(randomNumber);
            if (i < 11) {
                sb.append(",");
            }
        }
        inputArray.setText(sb.toString());
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleExit() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
}
