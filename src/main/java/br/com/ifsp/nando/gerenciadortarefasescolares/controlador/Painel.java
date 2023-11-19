package br.com.ifsp.nando.gerenciadortarefasescolares.controlador;

import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Painel implements Initializable {

    @FXML
    private Pane painel;

    @FXML
    private Label label;

    private Usuario usuario;

    @FXML
    public void teste() {
        Stage stage = (Stage) painel.getScene().getWindow();

        usuario = (Usuario) stage.getUserData();

        System.out.println(usuario);

        label.setText(usuario.getNome_usuario());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Stage stage = (Stage) painel.getScene().getWindow();
//
//        usuario = (Usuario) stage.getUserData();

//        label.setText("oie");
    }
}
