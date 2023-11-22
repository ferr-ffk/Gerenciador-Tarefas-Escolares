package br.com.ifsp.nando.gerenciadortarefasescolares.view;

import br.com.ifsp.nando.gerenciadortarefasescolares.Main;
import br.com.ifsp.nando.gerenciadortarefasescolares.controlador.Painel;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.TipoTarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.services.TipoTarefaService;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class TipoTarefaView extends HBox {

    private TipoTarefa tipoTarefa;

    /**
     * Um componente de tipoTarefa em forma de item em uma lista. Possui o checkbox e um botão para editar
     *
     * @param tipoTarefa A tipoTarefa correspondente
     */
    public TipoTarefaView(TipoTarefa tipoTarefa) {
        this.tipoTarefa = tipoTarefa;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        VBox vBox = new VBox();

        Label titulo = new Label(tipoTarefa.getNome());

        vBox.getChildren().addAll(titulo);
        vBox.setSpacing(5f);

        Circle simbolo = new Circle(10, tipoTarefa.getCor());

        Button botaoEditar = new Button("Editar");
        botaoEditar.setOnAction(event -> {
            carregarCenaEditarCategoria();
        });
        Button botaoExcluir = new Button("Excluir");
        botaoExcluir.setOnAction(event -> removerCategoria(this));

        setAlignment(Pos.CENTER_LEFT);
        setSpacing(10f);
        setPadding(new Insets(10, 15, 10, 15));
        getChildren().addAll(simbolo, vBox, botaoEditar, botaoExcluir);
    }

    private void removerCategoria(TipoTarefaView tipoTarefaView) {
        try {
            TipoTarefaService.deleteTipoTarefa(tipoTarefaView.tipoTarefa);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Essa categoria não pode ser deletada!");
            alert.setContentText("Exclua as tarefas dependentes dela e tente novamente");

            if(alert.showAndWait().orElseThrow() == ButtonType.OK) {
                return;
            }
        }

        // atualiza o painel principal
        Painel painelController = new Painel();
        painelController.atualizar();
    }

    private void carregarCenaEditarCategoria() {
        Stage stageCriarConta = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GerenciarCategoria.fxml"));
        Scene scene;

        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stageCriarConta.setUserData(tipoTarefa);
        stageCriarConta.setScene(scene);
        stageCriarConta.show();
    }

    public TipoTarefa getTipoTarefa() {
        return tipoTarefa;
    }

    public void setTipoTarefa(TipoTarefa tipoTarefa) {
        this.tipoTarefa = tipoTarefa;
    }
}
