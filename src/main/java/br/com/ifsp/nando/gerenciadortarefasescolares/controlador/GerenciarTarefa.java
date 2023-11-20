package br.com.ifsp.nando.gerenciadortarefasescolares.controlador;

import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Tarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.TipoTarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Usuario;
import br.com.ifsp.nando.gerenciadortarefasescolares.services.TarefaService;
import br.com.ifsp.nando.gerenciadortarefasescolares.services.UsuarioService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
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
    private Label labelErro;

    @FXML
    private ChoiceBox<TipoTarefa> choiceCategoria;

    @FXML
    private ObservableList<TipoTarefa> tipoTarefas;

    private Tarefa tarefa;

    private boolean novaTarefa;

    @FXML
    private void onClickEnviar() {
        Stage stage = (Stage) cena.getScene().getWindow();
        Usuario usuario;
        TipoTarefa categoria; // fixme: ta dando detached dnv KKKKKKKKK

        String titulo = fieldTitulo.getText();
        String descricao = fieldDescricao.getText();
        LocalDate date = datePicker.getValue();

        boolean tarefaInvalida = titulo.isEmpty() | datePicker.getValue() == null;

        if (tarefaInvalida) {
            labelErro.setText("Os campos título e data não podem ser vazios!");
            return;
        }

        if (novaTarefa) {
            usuario = (Usuario) stage.getUserData();
            categoria = choiceCategoria.getValue();

            tarefa = new Tarefa(titulo, descricao, date, categoria, usuario);

            TarefaService.createTarefa(tarefa);
        } else {
            usuario = tarefa.getUsuario();
            categoria = choiceCategoria.getValue();

            tarefa = new Tarefa(titulo, descricao, date, categoria, usuario);
            TarefaService.updateTarefa(this.tarefa, tarefa);
        }

        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // como o stage inicia nulo, é necssário usar o runLater pra deixar pra rodar após carregar td
        Platform.runLater(() -> {
            Stage stage = (Stage) cena.getScene().getWindow();

            // verifica se o dado do stage for o usuário
            Tarefa tarefa = stage.getUserData() instanceof Usuario ? null : (Tarefa) stage.getUserData();

            novaTarefa = tarefa == null;

            if (!novaTarefa) {
                this.tarefa = tarefa;
                labelTitulo.setText("Editar tarefa:");

                fieldTitulo.setText(tarefa.getTitulo());
                fieldDescricao.setText(tarefa.getDescricao());
                datePicker.setValue(tarefa.getDataVencimento());

                tipoTarefas = FXCollections.observableList(UsuarioService.readCategoriasUsuario(tarefa.getUsuario()));

                choiceCategoria.setValue(tarefa.getTipoTarefa());
            } else {
                Usuario usuario = (Usuario) stage.getUserData();

                tipoTarefas = FXCollections.observableList(UsuarioService.readCategoriasUsuario(usuario));
                choiceCategoria.setValue(tipoTarefas.get(0));
            }

            choiceCategoria.setItems(tipoTarefas);
        });
    }
}
