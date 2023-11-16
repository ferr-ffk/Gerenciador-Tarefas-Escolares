package br.com.ifsp.nando.gerenciadortarefasescolares.view;

import br.com.ifsp.nando.gerenciadortarefasescolares.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GerenciadorTarefasEscolares extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.getIcons().add(new Image(getClass().getResourceAsStream("/br/com/ifsp/nando/gerenciadortarefasescolares/Icons/icon.png")));

        stage.setTitle("Gerenciador de tarefas escolares");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();


        stage.setOnCloseRequest(event -> {
            event.consume();
            fecharJanela(stage);
        });
    }

    public void fecharJanela(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sair");
        alert.setHeaderText("Você está prestes a sair!");
        alert.setContentText("Qualquer alteração não salva será perdida!");

        if(alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
