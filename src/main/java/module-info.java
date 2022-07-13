module ru.geekbrains.chat {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens ru.geekbrains.words to javafx.fxml;
    exports ru.geekbrains.words;
}