package br.com.ifsp.nando.gerenciadortarefasescolares.controlador;

import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.TipoTarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CriarCategoria {

    @FXML
    private FlowPane cenaCriarCategoria;

    @FXML
    private TextField nomeCategoria;

    @FXML
    private ColorPicker corCategoria;

    @FXML
    private Button botaoCriarCategoria;

    public void criarCategoria(ActionEvent event) {
        Stage stage = (Stage) cenaCriarCategoria.getScene().getWindow();
        Usuario usuario = (Usuario) stage.getUserData();

        String nome = nomeCategoria.getText();
        Color cor = corCategoria.getValue();

        TipoTarefa tipoTarefa = new TipoTarefa(nome, cor, usuario);
        System.out.println(tipoTarefa);
    }
}
