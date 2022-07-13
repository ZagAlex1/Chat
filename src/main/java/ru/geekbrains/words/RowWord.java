package ru.geekbrains.words;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RowWord {
    private SimpleStringProperty word;
    private SimpleStringProperty date;
    private SimpleIntegerProperty count;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");

    public RowWord(String word, int count, Date date) {
        this.word = new SimpleStringProperty(word);
        this.count = new SimpleIntegerProperty(count);
        this.date = new SimpleStringProperty();
        setDate(date);
    }

    public RowWord(String word, int count) {
        this(word,count, new Date());
    }

    public String getWord() {
        return word.get();
    }

    public SimpleStringProperty wordProperty() {
        return word;
    }

    public void setWord(String word) {
        this.word.set(word);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(Date date) {
        this.date.set(simpleDateFormat.format(date));
    }

    public int getCount() {
        return count.get();
    }

    public SimpleIntegerProperty countProperty() {
        return count;
    }

    public void setCount(int count) {
        this.count.set(count);
    }

    public void incCount() {
        count.set(count.get() + 1);
        setDate(new Date());
    }
}
