package ru.geekbrains.words;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;


public class HelloController {
    @FXML
    private TextField fieldMax;

    @FXML
    private TextField fieldMin;

    @FXML
    private TableColumn<RowWord, Integer> countColumn;

    @FXML
    private TableColumn<RowWord, String> dateColumn;

    @FXML
    private TableColumn<RowWord, String> wordColumn;

    @FXML
    private TableView<RowWord> wordTable;

    @FXML
    private Label resultLabel;

    @FXML
    private ListView<String> listResults;

    @FXML
    private TextField inputField;

    private final ObservableList<String> results = FXCollections.observableArrayList();

    @FXML
    void pushWordToTable() {
        String message = inputField.getText().trim();
        if (message.length() != 0) {
            addMessageToTable(message);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("строка не должна быть пустой");
            alert.show();
        }
    }

    private void addMessageToTable(String message) {
        ObservableList<RowWord> rowArray = wordTable.getItems();

        for (RowWord rowWord : rowArray) {
            if(message.equals(rowWord.getWord())) {
                rowWord.incCount();
                return;
            }
        }

        rowArray.add(new RowWord(message, 1));
    }

    @FXML
    void doRandom() {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE - 1;
        String minString = fieldMin.getText().trim();
        String maxString = fieldMax.getText().trim();

        if (minString.length() != 0) {
            try {
                min = Integer.parseInt(minString);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                showErrorAlert(min, e);
            }
        }

        if (maxString.length() != 0) {
            try {
                max = Integer.parseInt(maxString);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                showErrorAlert(max, e);
            }
        }

        int result = ThreadLocalRandom.current().nextInt(min, max + 1);

        resultLabel.setText(String.valueOf(result));
        String strDate = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss").format(new Date());
        listResults.getItems().add(0,String.format("(%s от %d до %d)  -- %d",strDate, min, max, result));
    }

    private void showErrorAlert(int value, NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка входных значений");
        alert.setHeaderText("Значение поля будет установлено на базовое: " + value);
        alert.setContentText(e.getMessage());
        alert.show();
    }

    @FXML
    void initialize() {
        wordColumn.setCellValueFactory(new PropertyValueFactory<>("word"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        wordTable.setItems(FXCollections.observableArrayList(
                new RowWord("a",1),
                new RowWord("b",2),
                new RowWord("c",3),
                new RowWord("hello",4)
        ));
    }

}