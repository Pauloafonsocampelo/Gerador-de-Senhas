package com.example.gerador_de_senhas;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("GeradorSenhas.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 443, 600);
        stage.setTitle("Gerador de Senhas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}