package br.com.ifsp.nando.gerenciadortarefasescolares.controlador;

import br.com.ifsp.nando.gerenciadortarefasescolares.Main;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Tarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.TipoTarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Usuario;
import br.com.ifsp.nando.gerenciadortarefasescolares.services.UsuarioService;
import br.com.ifsp.nando.gerenciadortarefasescolares.view.TarefaView;
import br.com.ifsp.nando.gerenciadortarefasescolares.view.TipoTarefaView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Painel implements Initializable {

    Stage stage;

    @FXML
    private FlowPane painel;

    private static Usuario usuario;

    private ObservableList<Tarefa> tarefas;

    private ObservableList<TipoTarefa> categorias;

    @FXML
    private ListView<TarefaView> listaTarefas;

    @FXML
    private ListView<TipoTarefaView> listaCategorias;

    @FXML
    private Label labelTitulo;

    @FXML
    private void logout() {
        stage.close();
    }

    @FXML
    private void cenaCriarTarefa() throws IOException {
        Stage stageCriarTarefa = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GerenciarTarefa.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = (Stage) painel.getScene().getWindow();

        usuario = (Usuario) stage.getUserData();

        stageCriarTarefa.setUserData(usuario);
        stageCriarTarefa.setScene(scene);
        stageCriarTarefa.show();
    }

    @FXML
    private void cenaCriarCategoria() throws IOException {
        Stage stageCriarCategoria = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GerenciarCategoria.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage = (Stage) painel.getScene().getWindow();

        usuario = (Usuario) stage.getUserData();

        stageCriarCategoria.setUserData(usuario);
        stageCriarCategoria.setScene(scene);
        stageCriarCategoria.show();
    }

    /**
     * Atualiza as tarefas da ListView para renderizar
     */
    @FXML
    public void atualizarTarefas() {
        tarefas = FXCollections.observableList(UsuarioService.readTarefaUsuario(usuario));

        listaTarefas.getItems().clear();
        listaTarefas.refresh();

        tarefas.forEach(tarefa -> listaTarefas.getItems().add(new TarefaView(tarefa)));
    }

    /**
     * Atualiza as categorias da ListView para renderizar
     */
    @FXML
    public void atualizarCategorias() {
        categorias = FXCollections.observableList(UsuarioService.readCategoriasUsuario(usuario));

        listaCategorias.getItems().clear();
        listaCategorias.refresh();

        categorias.forEach(categoria -> listaCategorias.getItems().add(new TipoTarefaView(categoria)));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // como o stage inicia nulo, é necssário usar o runLater pra deixar pra rodar após carregar td
        Platform.runLater(() -> {
            stage = (Stage) painel.getScene().getWindow();
            usuario = (Usuario) stage.getUserData();

            atualizarTarefas();
            atualizarCategorias();

            String nome = usuario.getApelido().isEmpty() ? usuario.getNomeUsuario() : usuario.getApelido();
            labelTitulo.setText("Bem vindo " + nome + "!");
        });
    }
}

/*
 *  TODO: pegar a lista de categorias e colocar na tab Categorias
 *
 *  TODO: Colocar o ngc de editar a tarefa e a categoria
 *
 *  apesar disso, sabia q o projeto ta quase pronto? parabens lindo vc fez bem
 */
