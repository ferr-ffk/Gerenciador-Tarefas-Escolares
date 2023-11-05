package br.com.ifsp.nando.gerenciadortarefasescolares.controlador;

import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CriarConta {

    @FXML
    public Button botao_criar_usuario;

    @FXML
    public TextField apelido_usuario;

    @FXML
    public TextField nome_usuario;

    @FXML
    public PasswordField senha;

    @FXML
    public PasswordField confirmar;

    public void onCriarUsuario() {
        boolean senhaValida = confirmar.getText().equals(senha.getText());

        if(!senhaValida) {
            return;
        }


        String apelido_banco = apelido_usuario.getText();

        String usuario_banco = nome_usuario.getText();

        String senha_banco = senha.getText();

        // TODO: criar método para armazenar no banco de dados
        Usuario usuario = new Usuario(apelido_banco, usuario_banco, senha_banco);
    }
}
