package br.com.ifsp.nando.gerenciadortarefasescolares.controlador;

import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.TipoTarefa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

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
        String nome = nomeCategoria.getText();
        String cor = corCategoria.getValue().toString();

        TipoTarefa tipoTarefa = new TipoTarefa(nome, cor);
        System.out.println(tipoTarefa);
    }
}
