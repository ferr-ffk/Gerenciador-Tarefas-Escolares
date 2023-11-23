package br.com.ifsp.nando.gerenciadortarefasescolares.controlador;

import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.TipoTarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Usuario;
import br.com.ifsp.nando.gerenciadortarefasescolares.services.TipoTarefaService;
import br.com.ifsp.nando.gerenciadortarefasescolares.util.ColorUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private Label labelErro;

    private TipoTarefa tipoTarefa;

    private boolean novaCategoria;

    @FXML
    private void criarCategoria() {
        String nome = nomeCategoria.getText();
        Color cor = corCategoria.getValue();

        boolean categoriaInvalida = nome.isEmpty();

        if(categoriaInvalida) {
            labelErro.setText("O nome da categoria não pode ser vazio!");

            return;
        }

        Usuario usuario;

        if(novaCategoria) {
            usuario = (Usuario) stage.getUserData();
            TipoTarefa tipoTarefa = new TipoTarefa(nome, cor, usuario);

            TipoTarefaService.createTipoTarefa(tipoTarefa);
        } else {
            tipoTarefa.setNome(nome);
            tipoTarefa.setCor(ColorUtil.paraHexString(cor));

            TipoTarefaService.updateTipoTarefa(this.tipoTarefa);
        }

        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            stage = (Stage) cenaCriarCategoria.getScene().getWindow();

            TipoTarefa tipoTarefa = stage.getUserData() instanceof Usuario ? null : (TipoTarefa) stage.getUserData();

            novaCategoria = tipoTarefa == null;

            if(!novaCategoria) {
                this.tipoTarefa = tipoTarefa;
                labelTitulo.setText("Editar categoria:");

                nomeCategoria.setText(tipoTarefa.getNome());
                corCategoria.setValue(tipoTarefa.getCor());
            }
        });
    }
}
