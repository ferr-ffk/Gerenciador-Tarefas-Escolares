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

    private Stage stageLogin;

    @FXML
    private BorderPane cenaLogin;

    @FXML
    private Label loginTitulo;

    @FXML
    private Button botaoLogin;

    @FXML
    public void onCriarConta() throws IOException {
        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CriarConta.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        loginTitulo.setText("Gerenciador de tarefas v" + Main.VERSAO_GERENCIADOR);
    }
}
