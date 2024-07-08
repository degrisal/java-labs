package com.kami.study.apfivethlab; //пакет, в котором находится класс Application.

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;//импортируем несколько кла.
// ссов из библиотеки JavaFX: FXMLLoader, Scene и Stage.
import javafx.stage.Stage;  //Эти классы используются для создания графического интерфейса приложения.

import java.io.IOException;
  //Класс Application наследуется от класса javafx.application.
  // Application, что позволяет нам создать графическое приложение.

public class Application extends javafx.application.Application {
     //Метод start() переопределен из класса Application и вызывается при запуске приложения.
    // Внутри этого метода происходит инициализация графического интерфейса.
     @Override
     public void start(Stage stage) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("primary.fxml"));
         Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
         stage.setTitle("Lab");
         stage.setScene(scene);
         stage.show();
     }

    public static void main(String[] args) {
        launch();
    }
}