package br.com.ifsp.nando.gerenciadortarefasescolares.controlador;

import br.com.ifsp.nando.gerenciadortarefasescolares.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {

    @FXML
    public BorderPane cena_login;

    @FXML
    public Label login_titulo;

    @FXML
    public Button botao_criar_conta;

    @FXML
    public void onClicKBotaoCriarConta() throws IOException {
        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CriarConta.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 380);

        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        login_titulo.setText("Gerenciador de tarefas v" + Main.VERSAO_GERENCIADOR);
    }
}
