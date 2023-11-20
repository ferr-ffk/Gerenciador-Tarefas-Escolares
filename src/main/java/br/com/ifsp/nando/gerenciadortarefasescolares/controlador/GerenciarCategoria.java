package br.com.ifsp.nando.gerenciadortarefasescolares.controlador;

import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.TipoTarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Usuario;
import br.com.ifsp.nando.gerenciadortarefasescolares.services.TipoTarefaService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GerenciarCategoria implements Initializable {

    private Stage stage;

    @FXML
    private Label labelTitulo;

    @FXML
    private AnchorPane cenaCriarCategoria;

    @FXML
    private TextField nomeCategoria;

    @FXML
    private ColorPicker corCategoria;

    @FXML
    private Button botaoCriarCategoria;

    private TipoTarefa tipoTarefa;

    private boolean novaTarefa;

    @FXML
    private void criarCategoria() {
        String nome = nomeCategoria.getText();

        Color cor = corCategoria.getValue();

        Usuario usuario;

        if(novaTarefa) {
            usuario = (Usuario) stage.getUserData();
        } else {
            usuario = tipoTarefa.getUsuario();
        }

        // fixme: o getUserData retorna a tarefa quando for editar

        TipoTarefa tipoTarefa = new TipoTarefa(nome, cor, usuario);

        System.out.println(tipoTarefa);

        TipoTarefaService.createTipoTarefa(tipoTarefa);

        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            stage = (Stage) cenaCriarCategoria.getScene().getWindow();

            TipoTarefa tipoTarefa = stage.getUserData() instanceof Usuario ? null : (TipoTarefa) stage.getUserData();

            novaTarefa = tipoTarefa == null;

            if(!novaTarefa) {
                this.tipoTarefa = tipoTarefa;
                labelTitulo.setText("Editar categoria:");

                nomeCategoria.setText(tipoTarefa.getNome());
                corCategoria.setValue(tipoTarefa.getCor());
            }
        });
    }
}
