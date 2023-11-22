package br.com.ifsp.nando.gerenciadortarefasescolares.controlador;

import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Tarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.TipoTarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Usuario;
import br.com.ifsp.nando.gerenciadortarefasescolares.services.TarefaService;
import br.com.ifsp.nando.gerenciadortarefasescolares.services.UsuarioService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private List<TipoTarefa> tipoTarefasPersonalizadas;

    @FXML
    private Circle corCategoria;

    private Tarefa tarefa;

    private boolean criandoNovaTarefa;

    @FXML
    private void onClickEnviar() {
        Stage stage = (Stage) cena.getScene().getWindow();
        Usuario usuario;
        TipoTarefa categoria;

        String titulo = fieldTitulo.getText();
        String descricao = fieldDescricao.getText();
        LocalDate date = datePicker.getValue();

        boolean tarefaInvalida = titulo.isEmpty() | datePicker.getValue() == null;

        if (tarefaInvalida) {
            labelErro.setText("Os campos título e data não podem ser vazios!");
            return;
        }

        if (criandoNovaTarefa) {
            usuario = (Usuario) stage.getUserData();
            categoria = choiceCategoria.getValue();

            tarefa = new Tarefa(titulo, descricao, date, categoria, usuario);

            TarefaService.createTarefa(tarefa);
        } else {
            usuario = tarefa.getUsuario();
            categoria = choiceCategoria.getValue();

            tarefa = new Tarefa(titulo, descricao, date, categoria, usuario);
            TarefaService.deleteTarefa(this.tarefa);
            TarefaService.createTarefa(tarefa);
        }

        stage.close();
    }

    @FXML
    private void onClickChoiceCategoria() {
        // muda a cor do círculo para a cor correspondente da categoria
        corCategoria.setFill(choiceCategoria.getValue().getCor());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // como o stage inicia nulo, é necssário usar o runLater pra deixar pra rodar após carregar td
        Platform.runLater(() -> {
            Stage stage = (Stage) cena.getScene().getWindow();

            final TipoTarefa categoria3;
            final TipoTarefa categoria1;
            final TipoTarefa categoria2;

            // verifica se o dado do stage for o usuário
            Tarefa tarefa = stage.getUserData() instanceof Usuario ? null : (Tarefa) stage.getUserData();

            criandoNovaTarefa = tarefa == null;


            if (!criandoNovaTarefa) {
                this.tarefa = tarefa;
                labelTitulo.setText("Editar tarefa:");

                fieldTitulo.setText(tarefa.getTitulo());
                fieldDescricao.setText(tarefa.getDescricao());
                datePicker.setValue(tarefa.getDataVencimento());

                tipoTarefasPersonalizadas = UsuarioService.readCategoriasUsuario(tarefa.getUsuario());

                choiceCategoria.setValue(tarefa.getTipoTarefa());

                categoria1 = new TipoTarefa("Pessoal", Color.LIGHTCORAL, tarefa.getUsuario());
                categoria2 = new TipoTarefa("Escolar", Color.GOLD, tarefa.getUsuario());
                categoria3 = new TipoTarefa("Lazer", Color.DARKGREEN, tarefa.getUsuario());
            } else {
                Usuario usuario = (Usuario) stage.getUserData();

                tipoTarefasPersonalizadas = FXCollections.observableList(UsuarioService.readCategoriasUsuario(usuario));
                choiceCategoria.setValue(tipoTarefasPersonalizadas.get(0));

                categoria1 = new TipoTarefa("Pessoal", Color.LIGHTCORAL, usuario);
                categoria2 = new TipoTarefa("Escolar", Color.GOLD, usuario);
                categoria3 = new TipoTarefa("Lazer", Color.DARKGREEN, usuario);
            }

            final List<TipoTarefa> listaTipoTarefasPadrao = Arrays.asList(categoria1, categoria2, categoria3);
            final List<TipoTarefa> merge = new ArrayList<>();

            merge.addAll(listaTipoTarefasPadrao);
            merge.addAll(tipoTarefasPersonalizadas);

            choiceCategoria.getItems().setAll(FXCollections.observableList(merge));
        });
    }
}
