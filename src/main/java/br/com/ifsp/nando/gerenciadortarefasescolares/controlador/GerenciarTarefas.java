package br.com.ifsp.nando.gerenciadortarefasescolares.controlador;

import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Tarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.TipoTarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Usuario;
import br.com.ifsp.nando.gerenciadortarefasescolares.services.TarefaService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDate;

public class GerenciarTarefas {

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

    @FXML
    private void onClickEnviar() {
        Stage stage = (Stage) cena.getScene().getWindow();
        Usuario usuario = (Usuario) stage.getUserData();

        System.out.println(usuario);

        String titulo = fieldTitulo.getText();
        String descricao = fieldDescricao.getText();
        LocalDate date = datePicker.getValue();
        TipoTarefa categoria = new TipoTarefa("Nome", new Color(0, 0, 0, 0), usuario); // TODO: pegar categoria do choicebox

        Tarefa tarefa = new Tarefa(titulo, descricao, date, categoria, usuario);
        TarefaService.createTarefa(tarefa);

        System.out.println(tarefa);
        stage.close();
    }

}
