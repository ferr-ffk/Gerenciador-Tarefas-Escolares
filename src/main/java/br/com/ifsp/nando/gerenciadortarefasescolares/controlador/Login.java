package br.com.ifsp.nando.gerenciadortarefasescolares.controlador;

import br.com.ifsp.nando.gerenciadortarefasescolares.Main;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Usuario;
import br.com.ifsp.nando.gerenciadortarefasescolares.services.UsuarioService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Login implements Initializable {

    @FXML
    private BorderPane cenaLogin;

    @FXML
    private Label loginTitulo;

    @FXML
    private Button botaoLogin;

    @FXML
    private TextField entradaNomeUsuario;

    @FXML
    private PasswordField entradaSenhaUsuario;

    @FXML
    private Label labelErro;

    List<Usuario> usuarios;

    @FXML
    public void onLogin() {
        final String nomeUsuario = entradaNomeUsuario.getText();

        Optional<Usuario> usuarioCadastrado = usuarios.stream().filter(u -> u.getNome_usuario().equals(nomeUsuario)).findFirst();

        if(usuarioCadastrado.isPresent()) {
            boolean senhaValida = usuarioCadastrado.get().getSenha().equals(entradaSenhaUsuario.getText());

            if(senhaValida) {
                // usuario logado! prosseguir para o painel principal

                System.out.println("Usuário encontrado!");
            } else {
                // senha incorreta

                labelErro.setText("Usuário/senha incorretos!");
            }
        } else {
            // notificar que a senha/usuário estão incorretos

            labelErro.setText("Usuário não cadastrado!");
        }
    }

    @FXML
    public void onCriarConta() throws IOException {
        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CriarConta.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginTitulo.setText("Gerenciador de tarefas v" + Main.VERSAO_GERENCIADOR);

        usuarios = UsuarioService.readUsuarios();

        System.out.println(usuarios);
    };
}
