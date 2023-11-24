package br.com.ifsp.nando.gerenciadortarefasescolares.controlador;

import br.com.ifsp.nando.gerenciadortarefasescolares.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Sobre implements Initializable {

    @FXML
    private Label labelVersao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelVersao.setText("v" + Main.VERSAO_GERENCIADOR);
    }
}
