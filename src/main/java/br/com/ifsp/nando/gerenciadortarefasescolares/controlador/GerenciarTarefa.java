package br.com.ifsp.nando.gerenciadortarefasescolares.controlador;

import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Tarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.TipoTarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Usuario;
import br.com.ifsp.nando.gerenciadortarefasescolares.services.TarefaService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class GerenciarTarefa implements Initializable {

    @FXML
    private Label labelTitulo;

    @FXML
    private AnchorPane cena;

    @FXML
    private TextField fieldTitulo;

    @FXML
    private TextArea fieldDescricao;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button botaoEnviar;

    @FXML
    private ChoiceBox<TipoTarefa> choiceCategoria;

    private Tarefa tarefa;

    @FXML
    private void onClickEnviar() {
        Stage stage = (Stage) cena.getScene().getWindow();
        Usuario usuario = (Usuario) stage.getUserData();

        String titulo = fieldTitulo.getText();
        String descricao = fieldDescricao.getText();
        LocalDate date = datePicker.getValue();
        TipoTarefa categoria = new TipoTarefa("Nome", new Color(1, 1, 1, 1), usuario); // TODO: pegar categoria do choicebox

        Tarefa tarefa = new Tarefa(titulo, descricao, date, categoria, usuario);
        System.out.println(tarefa);

        TarefaService.createTarefa(tarefa);

        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // como o stage inicia nulo, é necssário usar o runLater pra deixar pra rodar após carregar td
        Platform.runLater(() -> {
            Stage stage = (Stage) cena.getScene().getWindow();

            // verifica se o dado do stage for o usuário
            Tarefa tarefa = stage.getUserData() instanceof Usuario ? null : (Tarefa) stage.getUserData();

            if (tarefa != null) {
                this.tarefa = tarefa;
                labelTitulo.setText("Editar tarefa:");

                fieldTitulo.setText(tarefa.getTitulo());
                fieldDescricao.setText(tarefa.getDescricao());
                datePicker.setValue(tarefa.getDataVencimento());

                choiceCategoria.getItems().setAll(); // TODO: setar pra categoria tb
            }
        });
    }
}
