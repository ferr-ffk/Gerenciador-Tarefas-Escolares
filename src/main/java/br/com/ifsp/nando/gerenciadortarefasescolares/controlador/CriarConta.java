package br.com.ifsp.nando.gerenciadortarefasescolares.controlador;

import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.TipoTarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Usuario;
import br.com.ifsp.nando.gerenciadortarefasescolares.services.TipoTarefaService;
import br.com.ifsp.nando.gerenciadortarefasescolares.services.UsuarioService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class CriarConta {

    @FXML
    private AnchorPane cenaCriarConta;

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

    @FXML
    private Label labelErro;

    public void onCriarUsuario() {
        List<Usuario> usuarios = UsuarioService.readUsuarios();

        boolean senhaValida = confirmar.getText().equals(senha.getText());

        if(!senhaValida) {
            labelErro.setText("As senhas devem ser iguais!");

            return;
        }

        String apelido_banco = apelido_usuario.getText();
        String usuario_banco = nome_usuario.getText();
        String senha_banco = senha.getText();

        boolean nomeUsuarioJaCadastrado = usuarios.stream().anyMatch(usuario -> usuario.getNomeUsuario().equals(usuario_banco));

        if(senha_banco.isEmpty() || usuario_banco.isEmpty()) {
            labelErro.setText("Os campos não podem ser nulos!");
            return;
        }

        if(nomeUsuarioJaCadastrado) {
            labelErro.setText("O nome de usuário " + usuario_banco + " já existe!");
            return;
        }

        fecharJanela();

        Usuario usuario = new Usuario(apelido_banco, usuario_banco, senha_banco);

        // o objeto usuário é inicializado com nenhuma categoria no banco
        // criando categorias padrão para uso do usuário
        TipoTarefa categoria1 = new TipoTarefa("Pessoal", Color.LIGHTCORAL, usuario);
        TipoTarefa categoria2 = new TipoTarefa("Escolar", Color.GOLD, usuario);
        TipoTarefa categoria3 = new TipoTarefa("Lazer", Color.DARKGREEN, usuario);

        UsuarioService.createUsuario(usuario);
        TipoTarefaService.createTipoTarefa(categoria1);
        TipoTarefaService.createTipoTarefa(categoria2);
        TipoTarefaService.createTipoTarefa(categoria3);
    }

    /**
     * Obtém a janela atual do contexto e fecha ela (sem avisos)
     */
    private void fecharJanela() {
        Stage stageCriarConta = (Stage) cenaCriarConta.getScene().getWindow();
        stageCriarConta.close();
    }

}
