package br.com.ifsp.nando.gerenciadortarefasescolares.controlador;

import br.com.ifsp.nando.gerenciadortarefasescolares.Main;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Relatorio;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Tarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.TipoTarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Usuario;
import br.com.ifsp.nando.gerenciadortarefasescolares.services.TarefaService;
import br.com.ifsp.nando.gerenciadortarefasescolares.services.UsuarioService;
import br.com.ifsp.nando.gerenciadortarefasescolares.util.JavaFXUtil;
import br.com.ifsp.nando.gerenciadortarefasescolares.view.GerenciadorTarefasEscolares;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Painel implements Initializable {

    Stage stage;

    @FXML
    private FlowPane painel;

    private static Usuario usuario;

    private ObservableList<Tarefa> tarefas;

    private ObservableList<TipoTarefa> categorias;

    @FXML
    private ListView<TarefaView> listViewTarefas;

    @FXML
    private ListView<TipoTarefaView> listaCategorias;

    @FXML
    private Label labelTitulo;

    @FXML
    private TextField fieldNovoApelido;

    @FXML
    private PasswordField fieldSenhaAtual;

    @FXML
    private PasswordField fieldNovaSenha;

    @FXML
    private PasswordField fieldConfirmarNovaSenha;

    @FXML
    private ChoiceBox<TipoTarefa> choiceFiltrarCategoria;

    @FXML
    private Label tituloRelatorio;

    @FXML
    private Label descricaoRelatorio;

    @FXML
    private Label paragrafoRelatorio;

    @FXML
    private Label labelErro;

    @FXML
    private void deletarConta() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deletar conta");
        alert.setHeaderText("Você está prestes a excluir PERMANENTEMENTE sua conta... tem certeza?");
        alert.setContentText("Para proseeguir, certifique que todas as tarefas e categorias foram excluídas anteriormente");

        if(alert.showAndWait().orElseThrow() == ButtonType.OK) {
            stage.close();
            UsuarioService.deleteUsuario(usuario);
        }
    }

    @FXML
    private void mudarApelido() {
        String novoApelido = fieldNovoApelido.getText();

        usuario.setApelido(novoApelido);
        UsuarioService.createUsuario(usuario);

        labelTitulo.setText("Bem vindo " + novoApelido + "!");
    }

    @FXML
    private void trocarSenha() {
        String senhaAtual = fieldSenhaAtual.getText();

        String novaSenha = fieldNovaSenha.getText();
        String senhaConfirmar = fieldConfirmarNovaSenha.getText();

        boolean senhaCorreta = senhaAtual.equals(usuario.getSenha());

        if (senhaCorreta) {
            boolean senhaConfirmada = novaSenha.equals(senhaConfirmar);

            if (senhaConfirmada) {
                usuario.setSenha(novaSenha);
                UsuarioService.createUsuario(usuario);
            } else {
                labelErro.setText("As senhas devem ser iguais!");
            }
        } else {
            labelErro.setText("Digite corretamente a senha atual...");
        }
    }

    @FXML
    private void gerarRelatorio() {
        Relatorio relatorio = Relatorio.gerarRelatorio(usuario);

        final String nome = usuario.getApelido().isEmpty() ? usuario.getNomeUsuario() : usuario.getApelido();
        final int tarefasCriadas = relatorio.getTarefasCriadas();
        final int tarefasConcluidas = relatorio.getTarefasConcluidas();
        final double porcentagem = relatorio.getPorcentagem();

        String desempenho;

        if (porcentagem >= 100.0f) {
            desempenho = "ótimo!!!";
        } else if (porcentagem > 80.0f) {
            desempenho = "muito massa";
        } else if (porcentagem > 60.0f) {
            desempenho = "bomm";
        } else if (porcentagem > 40.0f) {
            desempenho = "mediano né";
        } else {
            desempenho = " muito ruim :(";
        }

        tituloRelatorio.setText("Relatório de " + nome);
        descricaoRelatorio.setText("Seu desempenho foi " + desempenho);
        paragrafoRelatorio.setText(String.format("Você criou %d tarefas e concluiu %d delas. Sua porcentagem atual é de %.2f%%; parabéns!", tarefasCriadas, tarefasConcluidas, porcentagem));
    }

    @FXML
    private void logout() {
        fecharJanelaComAviso(stage);
    }

    @FXML
    private void cenaCriarTarefa() throws IOException {
        Stage stageCriarTarefa = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GerenciarTarefa.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = (Stage) painel.getScene().getWindow();

        usuario = (Usuario) stage.getUserData();

        JavaFXUtil.setJanelaPadrao(stageCriarTarefa);
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

        JavaFXUtil.setJanelaPadrao(stageCriarCategoria);
        stageCriarCategoria.setUserData(usuario);
        stageCriarCategoria.setScene(scene);
        stageCriarCategoria.show();
    }

    @FXML
    public void atualizar() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Exceção na execução do programa");

        final DialogPane dialogPane = dialog.getDialogPane();

        dialogPane.getButtonTypes().addAll(ButtonType.OK);
        dialogPane.getButtonTypes().addAll(ButtonType.CLOSE);

        dialogPane.setContentText("Essa exceção veio de um processo interno");
        dialog.initModality(Modality.APPLICATION_MODAL);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        try {
            System.out.println(usuario);
            choiceFiltrarCategoria.setItems(FXCollections.observableList(UsuarioService.readCategoriasUsuario(usuario)));

            atualizarTarefas();
            atualizarCategorias();
        } catch (Exception e) {
            GerenciadorTarefasEscolares.ExibirJanelaExcecao(e, pw, sw, dialogPane, dialog);
        }
    }

    /**
     * Atualiza as tarefas da ListView para renderizar
     */
    @FXML
    private void atualizarTarefas() {
        // tarefas.clear();
        tarefas = FXCollections.observableList(UsuarioService.readTarefaUsuario(usuario));

        List<TarefaView> tarefas1 = tarefas.stream().map(TarefaView::new).toList();
        listViewTarefas.setItems(FXCollections.observableList(tarefas1));
    }

    /**
     * Atualiza as categorias da ListView para renderizar
     */
    @FXML
    private void atualizarCategorias() {
        categorias = FXCollections.observableList(UsuarioService.readCategoriasUsuario(usuario));

        listaCategorias.getItems().clear();
        listaCategorias.refresh();

        categorias.forEach(categoria -> listaCategorias.getItems().add(new TipoTarefaView(categoria)));
    }

    @FXML
    private void filtrarCategoria() {
        TipoTarefa categoria = choiceFiltrarCategoria.getValue();

        ObservableList<Tarefa> tarefasCadastradas = FXCollections.observableList(UsuarioService.readTarefaUsuario(usuario));

        // obtem as tarefas que são do mesmo tipo do choiceBox
        tarefasCadastradas = FXCollections.observableList(tarefas
                .stream()
                .filter(tarefa -> tarefa.getTipoTarefa().equals(categoria))
                .toList());

        listViewTarefas.setItems(FXCollections.observableList(tarefasCadastradas.stream().map(TarefaView::new).toList()));
    }

    @FXML
    private void limparFiltro() {
        tarefas = FXCollections.observableList(UsuarioService.readTarefaUsuario(usuario));
        listViewTarefas.setItems(FXCollections.observableList(tarefas.stream().map(TarefaView::new).toList()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Exceção na execução do programa");

        final DialogPane dialogPane = dialog.getDialogPane();

        dialogPane.getButtonTypes().addAll(ButtonType.OK);
        dialogPane.getButtonTypes().addAll(ButtonType.CLOSE);

        dialogPane.setContentText("Essa exceção veio de um processo interno");
        dialog.initModality(Modality.APPLICATION_MODAL);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        try {
            // como o stage inicia nulo, é necssário usar o runLater pra deixar pra rodar após carregar td
            Platform.runLater(() -> {
                stage = (Stage) painel.getScene().getWindow();
                usuario = (Usuario) stage.getUserData();

                atualizarTarefas();
                atualizarCategorias();

                stage.setOnCloseRequest(event -> {
                    // previne o fechamento automático da janela
                    event.consume();
                    fecharJanelaComAviso(stage);
                });

                choiceFiltrarCategoria.setItems(FXCollections.observableList(UsuarioService.readCategoriasUsuario(usuario)));

                String nome = usuario.getApelido().isEmpty() ? usuario.getNomeUsuario() : usuario.getApelido();
                labelTitulo.setText("Bem vindo " + nome + "!");
            });
        } catch (Exception e) {
            GerenciadorTarefasEscolares.ExibirJanelaExcecao(e, pw, sw, dialogPane, dialog);
        }
    }

    private void fecharJanelaComAviso(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sair");
        alert.setHeaderText("Você está prestes a sair!");
        alert.setContentText("Qualquer alteração não salva será perdida!");

        if (alert.showAndWait().orElseThrow() == ButtonType.OK) {
            // filtra as tarefas que foram marcadas como concluídas
            tarefas = FXCollections.observableList(listViewTarefas
                    .getItems()
                    .stream().map(TarefaView::getTarefa)
                    .filter(tarefa -> !tarefa.getConcluida())
                    .toList());

            UsuarioService.deletarTarefasUsuario(usuario);
            tarefas.forEach(tarefa -> {
                System.out.println(tarefa.getUsuario());
                TarefaService.createTarefa(tarefa);
            });

            stage.close();
        }
    }
}