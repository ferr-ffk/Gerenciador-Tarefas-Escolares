package br.com.ifsp.nando.gerenciadortarefasescolares.controlador;

import br.com.ifsp.nando.gerenciadortarefasescolares.Main;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Tarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.TipoTarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Usuario;
import br.com.ifsp.nando.gerenciadortarefasescolares.services.UsuarioService;
import br.com.ifsp.nando.gerenciadortarefasescolares.view.TarefaView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Painel implements Initializable {

    @FXML
    private FlowPane painel;

    private Usuario usuario;

    private ObservableList<Tarefa> tarefas;

    @FXML
    private Tab tabTarefas;

    @FXML
    private Tab tabCategorias;

    @FXML
    private ListView<TarefaView> listaTarefas;

    @FXML
    private ListView<TipoTarefa> listaCategorias;

    @FXML
    public void onClickTabTarefas() {
//        Stage stage = (Stage) painel.getScene().getWindow();
//
//        usuario = (Usuario) stage.getUserData();
//
//        tarefas = FXCollections.observableList(UsuarioService.readTarefaUsuario(usuario));
//
//        tarefas.forEach(tarefa -> {
//            System.out.println(tarefa);
//            listaTarefas.getItems().add(new TarefaView(tarefa));
//        });
    }

    @FXML
    private void atualizar() {
        Stage stage = (Stage) painel.getScene().getWindow();

        usuario = (Usuario) stage.getUserData();

        System.out.println(usuario);

        tarefas = FXCollections.observableList(UsuarioService.readTarefaUsuario(usuario));

        System.out.println(tarefas);

        // passa o método para rodar depoisw
        Platform.runLater(() -> tarefas.forEach(tarefa -> listaTarefas.getItems().add(new TarefaView(tarefa))));
    }

    @FXML
    private void logout() {}

    @FXML
    private void gerenciarTarefas() throws IOException {
        Stage stageCriarTarefa = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GerenciarTarefas.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = (Stage) painel.getScene().getWindow();

        usuario = (Usuario) stage.getUserData();

        stageCriarTarefa.setUserData(usuario);
        stageCriarTarefa.setScene(scene);
        stageCriarTarefa.show();
    }

    @FXML
    private void gerenciarCategorias() throws IOException {
        Stage stageCriarCategoria = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CriarCategoria.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = (Stage) painel.getScene().getWindow();

        usuario = (Usuario) stage.getUserData();

        stageCriarCategoria.setUserData(usuario);
        stageCriarCategoria.setScene(scene);
        stageCriarCategoria.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}

/*
 *  TODO: fazer a label de bem vindo no painel
 *  TODO: pegar a lista de tarefas e colocar na tab Tarefas
 *  TODO: pegar a lista de categorias e colocar na tab Categorias
 *
 *  TODO: descobrir pq krls ele n ta exibindo as tarefas
 *
 *  TODO: Colocar o ngc de editar a tarefa e a categoria
 *
 *  apesar disso, sabia q o projeto ta quase pronto? parabens lindo vc fez bem
 */
