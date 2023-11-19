package br.com.ifsp.nando.gerenciadortarefasescolares.teste;

import br.com.ifsp.nando.gerenciadortarefasescolares.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestePainel extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Painel.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
