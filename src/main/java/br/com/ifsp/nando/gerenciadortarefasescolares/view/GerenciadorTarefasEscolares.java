package br.com.ifsp.nando.gerenciadortarefasescolares.view;

import br.com.ifsp.nando.gerenciadortarefasescolares.Main;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Usuario;
import br.com.ifsp.nando.gerenciadortarefasescolares.services.UsuarioService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GerenciadorTarefasEscolares extends Application implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UsuarioService.createUsuario(new Usuario("apelido", "nome", "123"));
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/br/com/ifsp/nando/gerenciadortarefasescolares/Icons/icon.jpg"))));

        stage.setTitle("Gerenciador de tarefas escolares");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            // previne o fechamento automático da janela
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
