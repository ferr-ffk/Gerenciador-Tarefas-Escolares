package br.com.ifsp.nando.gerenciadortarefasescolares.view;

import br.com.ifsp.nando.gerenciadortarefasescolares.Main;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Tarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.TipoTarefa;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class TarefaView extends HBox {

    private Tarefa tarefa;

    /**
     * Um componente de tarefa em forma de item em uma lista. Possui o checkbox e um botão para editar
     *
     * @param tarefa A tarefa correspondente
     */
    public TarefaView(Tarefa tarefa) {
        final String bullet = "\u2023";
        this.tarefa = tarefa;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String data = dtf.format(tarefa.getDataVencimento());

        VBox vBox = new VBox();

        Label titulo = new Label(tarefa.getTitulo() + " " + bullet + " " + data);
        Label descricao = new Label(tarefa.getDescricao());

        vBox.getChildren().addAll(titulo, descricao);
        vBox.setSpacing(2.5f);

        TipoTarefa tipoTarefa = tarefa.getTipoTarefa();
        Circle simbolo = new Circle(10, tipoTarefa.getCor());

        Button botaoEditar = new Button("Editar");
        botaoEditar.setOnAction(event -> carregarCenaEditarTarefa());

        CheckBox botaoConcluir = new CheckBox("Concluir");
        botaoConcluir.setOnAction(event -> this.tarefa.concluir());

        setAlignment(Pos.CENTER_LEFT);
        setSpacing(7.5f);
        setPadding(new Insets(10, 15, 10, 15));
        boolean descricaoVazia = descricao.getText().isEmpty();

        if (descricaoVazia) {
            getChildren().addAll(simbolo, titulo, botaoEditar, botaoConcluir);
        } else {
            getChildren().addAll(simbolo, vBox, botaoEditar, botaoConcluir);
        }
    }

    private void carregarCenaEditarTarefa() {
        Stage stageCriarConta = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GerenciarTarefa.fxml"));
        Scene scene;

        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stageCriarConta.setUserData(tarefa);
        stageCriarConta.setScene(scene);
        stageCriarConta.show();
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }
}
