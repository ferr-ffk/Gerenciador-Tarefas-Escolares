package br.com.ifsp.nando.gerenciadortarefasescolares.view;

import br.com.ifsp.nando.gerenciadortarefasescolares.Main;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.TipoTarefa;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        final String bullet = "\u2023";
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

        setAlignment(Pos.CENTER_LEFT);
        setSpacing(10f);
        setPadding(new Insets(10, 15, 10, 15));
        getChildren().addAll(simbolo, vBox, botaoEditar);
    }

    private void carregarCenaEditarCategoria() {
        Stage stageCriarConta = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GerenciarCategoria.fxml"));
        Scene scene = null;

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
