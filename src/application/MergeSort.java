package application;

import java.util.Arrays;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;

public class MergeSort implements DataReceiver {

    @FXML
    private TextField textField0, textField1, textField2, textField3, textField4, textField5, textField6, textField7;
    @FXML
    private TextField textField10, textField11, textField12, textField13, textField14, textField15, textField16, textField17;
    @FXML
    private TextField textField20, textField21, textField22, textField23, textField24, textField25, textField26, textField27;
    @FXML
    private TextField textField30, textField31, textField32, textField33, textField34, textField35, textField36, textField37;

    private TextField[] textFields;
    private TextField[] textFields1;
    private TextField[] textFields2;
    private TextField[] textFields3;
    private int[] values;

    @FXML
    public void initialize() {
        textFields = new TextField[]{textField0, textField1, textField2, textField3, textField4, textField5, textField6, textField7};
        textFields1 = new TextField[]{textField10, textField11, textField12, textField13, textField14, textField15, textField16, textField17};
        textFields2 = new TextField[]{textField20, textField21, textField22, textField23, textField24, textField25, textField26, textField27};
        textFields3 = new TextField[]{textField30, textField31, textField32, textField33, textField34, textField35, textField36, textField37};

        for (TextField textField : textFields1) {
            textField.setVisible(false);
            textField.setManaged(false);
        }
        for (TextField textField : textFields2) {
            textField.setVisible(false);
            textField.setManaged(false);
        }
        for (TextField textField : textFields3) {
            textField.setVisible(false);
            textField.setManaged(false);
        }
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
            	showAlert("Lỗi", "Vui lòng nhập giá trị hợp lệ trong mảng tại vị trí " + k, AlertType.ERROR);
                throw e;
            }
        }
    }


    @FXML
    private void handleMergeSort() {

        Timeline timeline = new Timeline();

        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(1), event -> {
            for (int j = 0; j <= 7; j++) {
                textFields1[j].setText(textFields[j].getText());
                textFields1[j].setStyle("-fx-border-color: blue;");
                textFields1[j].setVisible(true);
                textFields1[j].setManaged(true);
            }
        });

        KeyFrame clearBorderKeyFrame1 = new KeyFrame(Duration.seconds(1.5), event -> {
            for (int j = 0; j <= 7; j++) {
                textFields1[j].setStyle("");
            }
        });

        KeyFrame keyFrame21 = new KeyFrame(Duration.seconds(2), event -> {
            for (int j = 0; j <= 3; j++) {
                textFields2[j].setText(textFields1[j].getText());
                textFields2[j].setStyle("-fx-border-color: blue;");
                textFields2[j].setVisible(true);
                textFields2[j].setManaged(true);
            }
        });

        KeyFrame clearBorderKeyFrame21 = new KeyFrame(Duration.seconds(2.5), event -> {
            for (int j = 0; j <= 3; j++) {
                textFields2[j].setStyle("");
            }
        });

        KeyFrame keyFrame31 = new KeyFrame(Duration.seconds(3), event -> {
            for (int j = 0; j <= 1; j++) {
                textFields3[j].setText(textFields2[j].getText());
                textFields3[j].setStyle("-fx-border-color: blue;");
                textFields3[j].setVisible(true);
                textFields3[j].setManaged(true);
            }
        });

        KeyFrame clearBorderKeyFrame31 = new KeyFrame(Duration.seconds(3.5), event -> {
            for (int j = 0; j <= 1; j++) {
                textFields3[j].setStyle("");
            }
        });

        KeyFrame keyFrame41 = new KeyFrame(Duration.seconds(4), event -> {
            int mergedLeft = Math.min(Integer.parseInt(textFields3[0].getText()), Integer.parseInt(textFields3[1].getText()));
            int mergedRight = Math.max(Integer.parseInt(textFields3[0].getText()), Integer.parseInt(textFields3[1].getText()));
            textFields2[0].setText(Integer.toString(mergedLeft));
            textFields2[1].setText(Integer.toString(mergedRight));
            textFields2[0].setStyle("-fx-border-color: yellow;");
            textFields2[1].setStyle("-fx-border-color: yellow;");
            textFields3[0].clear();
            textFields3[1].clear();
            textFields3[0].setVisible(false);
            textFields3[0].setManaged(false);
            textFields3[1].setVisible(false);
            textFields3[1].setManaged(false);
        });

        KeyFrame clearBorderKeyFrame41 = new KeyFrame(Duration.seconds(4.5), event -> {
            textFields2[0].setStyle("");
            textFields2[1].setStyle("");
        });

        KeyFrame keyFrame32 = new KeyFrame(Duration.seconds(5), event -> {
            for (int j = 2; j <= 3; j++) {
                textFields3[j].setText(textFields2[j].getText());
                textFields3[j].setStyle("-fx-border-color: blue;");
                textFields3[j].setVisible(true);
                textFields3[j].setManaged(true);
            }
        });

        KeyFrame clearBorderKeyFrame32 = new KeyFrame(Duration.seconds(5.5), event -> {
            for (int j = 2; j <= 3; j++) {
                textFields3[j].setStyle("");
            }
        });

        KeyFrame keyFrame42 = new KeyFrame(Duration.seconds(6), event -> {
            int mergedLeft = Math.min(Integer.parseInt(textFields3[2].getText()), Integer.parseInt(textFields3[3].getText()));
            int mergedRight = Math.max(Integer.parseInt(textFields3[2].getText()), Integer.parseInt(textFields3[3].getText()));
            textFields2[2].setText(Integer.toString(mergedLeft));
            textFields2[3].setText(Integer.toString(mergedRight));
            textFields2[2].setStyle("-fx-border-color: yellow;");
            textFields2[3].setStyle("-fx-border-color: yellow;");
            textFields3[2].clear();
            textFields3[3].clear();
            textFields3[2].setVisible(false);
            textFields3[2].setManaged(false);
            textFields3[3].setVisible(false);
            textFields3[3].setManaged(false);
        });

        KeyFrame clearBorderKeyFrame42 = new KeyFrame(Duration.seconds(6.5), event -> {
            textFields2[2].setStyle("");
            textFields2[3].setStyle("");
        });

        KeyFrame keyFrame51 = new KeyFrame(Duration.seconds(7), event -> {
            try {
                int[] tmp = new int[4];
                for (int i = 0; i < 4; i++) {
                    tmp[i] = Integer.parseInt(textFields2[i].getText());
                }
                Arrays.sort(tmp);
                for (int i = 0; i < 4; i++) {
                    textFields1[i].setText(Integer.toString(tmp[i]));
                    textFields1[i].setStyle("-fx-border-color: pink;");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi khi chuyển giá trị TextField thành số nguyên trong KeyFrame51");
            }
            for (int i = 0; i < 4; i++) {
                textFields2[i].clear();
            }

            for (int i = 0; i < 4; i++) {
                textFields2[i].setVisible(false);
                textFields2[i].setManaged(false);
            }
        });

        KeyFrame clearBorderKeyFrame51 = new KeyFrame(Duration.seconds(7.5), event -> {
            for (int i = 0; i < 4; i++) {
                textFields1[i].setStyle("");
            }
        });

        KeyFrame keyFrame22 = new KeyFrame(Duration.seconds(8), event -> {
            for (int j = 4; j <= 7; j++) {
                textFields2[j].setText(textFields1[j].getText());
                textFields2[j].setVisible(true);
                textFields2[j].setManaged(true);
            }
        });

        KeyFrame keyFrame33 = new KeyFrame(Duration.seconds(9), event -> {
            for (int j = 4; j <= 5; j++) {
                textFields3[j].setText(textFields2[j].getText());
                textFields3[j].setStyle("-fx-border-color: blue;");
                textFields3[j].setVisible(true);
                textFields3[j].setManaged(true);
            }
        });

        KeyFrame clearBorderKeyFrame33 = new KeyFrame(Duration.seconds(9.5), event -> {
            for (int j = 4; j <= 5; j++) {
                textFields3[j].setStyle("");
            }
        });

        KeyFrame keyFrame43 = new KeyFrame(Duration.seconds(10), event -> {
            int mergedLeft = Math.min(Integer.parseInt(textFields3[4].getText()), Integer.parseInt(textFields3[5].getText()));
            int mergedRight = Math.max(Integer.parseInt(textFields3[4].getText()), Integer.parseInt(textFields3[5].getText()));
            textFields2[4].setText(Integer.toString(mergedLeft));
            textFields2[5].setText(Integer.toString(mergedRight));
            textFields2[4].setStyle("-fx-border-color: yellow;");
            textFields2[5].setStyle("-fx-border-color: yellow;");
            textFields3[4].clear();
            textFields3[5].clear();
            textFields3[4].setVisible(false);
            textFields3[4].setManaged(false);
            textFields3[5].setVisible(false);
            textFields3[5].setManaged(false);
        });

        KeyFrame clearBorderKeyFrame43 = new KeyFrame(Duration.seconds(10.5), event -> {
            textFields2[4].setStyle("");
            textFields2[5].setStyle("");
        });

        KeyFrame keyFrame34 = new KeyFrame(Duration.seconds(11), event -> {
            for (int j = 6; j <= 7; j++) {
                textFields3[j].setText(textFields2[j].getText());
                textFields3[j].setStyle("-fx-border-color: blue;");
                textFields3[j].setVisible(true);
                textFields3[j].setManaged(true);
            }
        });

        KeyFrame clearBorderKeyFrame34 = new KeyFrame(Duration.seconds(11.5), event -> {
            for (int j = 6; j <= 7; j++) {
                textFields3[j].setStyle("");
            }
        });

        KeyFrame keyFrame44 = new KeyFrame(Duration.seconds(12), event -> {
            int mergedLeft = Math.min(Integer.parseInt(textFields3[6].getText()), Integer.parseInt(textFields3[7].getText()));
            int mergedRight = Math.max(Integer.parseInt(textFields3[6].getText()), Integer.parseInt(textFields3[7].getText()));
            textFields2[6].setText(Integer.toString(mergedLeft));
            textFields2[7].setText(Integer.toString(mergedRight));
            textFields2[6].setStyle("-fx-border-color: yellow;");
            textFields2[7].setStyle("-fx-border-color: yellow;");
            textFields3[6].clear();
            textFields3[7].clear();
            textFields3[6].setVisible(false);
            textFields3[6].setManaged(false);
            textFields3[7].setVisible(false);
            textFields3[7].setManaged(false);
        });

        KeyFrame clearBorderKeyFrame44 = new KeyFrame(Duration.seconds(12.5), event -> {
            textFields2[6].setStyle("");
            textFields2[7].setStyle("");
        });

        KeyFrame keyFrame52 = new KeyFrame(Duration.seconds(13), event -> {
            try {
                int[] tmp = new int[4];
                for (int i = 0; i < 4; i++) {
                    tmp[i] = Integer.parseInt(textFields2[i + 4].getText());
                }
                Arrays.sort(tmp);
                for (int i = 0; i < 4; i++) {
                    textFields1[i + 4].setText(Integer.toString(tmp[i]));
                    textFields1[i + 4].setStyle("-fx-border-color: pink;");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi khi chuyển giá trị TextField thành số nguyên trong KeyFrame52");
            }
            for (int i = 0; i < 4; i++) {
                textFields2[i + 4].clear();
            }

            for (int i = 4; i <= 7; i++) {
                textFields2[i].setVisible(false);
                textFields2[i].setManaged(false);
            }
        });

        KeyFrame clearBorderKeyFrame52 = new KeyFrame(Duration.seconds(13.5), event -> {
            for (int i = 4; i <= 7; i++) {
                textFields1[i].setStyle("");
            }
        });

        KeyFrame keyFrame6 = new KeyFrame(Duration.seconds(14), event -> {
            int[] tmp = new int[8];
            for (int i = 0; i <= 7; i++) {
                tmp[i] = Integer.parseInt(textFields1[i].getText());
            }
            Arrays.sort(tmp);
            for (int i = 0; i <= 7; i++) {
                textFields[i].setText(Integer.toString(tmp[i]));
                textFields[i].setStyle("-fx-border-color: green;");
            }
            for (int i = 0; i <= 7; i++) {
                textFields1[i].clear();
            }
            for (int i = 0; i <= 7; i++) {
                textFields1[i].setVisible(false);
                textFields1[i].setManaged(false);
            }
        });

        KeyFrame clearBorderKeyFrame6 = new KeyFrame(Duration.seconds(14.5), event -> {
            for (int i = 0; i <= 7; i++) {
                textFields[i].setStyle("");
            }
        });

        timeline.getKeyFrames().addAll(keyFrame1, clearBorderKeyFrame1, keyFrame21, clearBorderKeyFrame21, 
            keyFrame31, clearBorderKeyFrame31, keyFrame41, clearBorderKeyFrame41, keyFrame32, clearBorderKeyFrame32, 
            keyFrame42, clearBorderKeyFrame42, keyFrame51, clearBorderKeyFrame51, keyFrame22, keyFrame33, 
            clearBorderKeyFrame33, keyFrame43, clearBorderKeyFrame43, keyFrame34, clearBorderKeyFrame34, 
            keyFrame44, clearBorderKeyFrame44, keyFrame52, clearBorderKeyFrame52, keyFrame6, clearBorderKeyFrame6);

        timeline.play();
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
