package br.com.ifsp.nando.gerenciadortarefasescolares.controlador;

import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CriarConta {

    Stage stageCriarConta;

    @FXML
    private BorderPane cenaCriarConta;

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

    public void onCriarUsuario(ActionEvent event) {
        boolean senhaValida = confirmar.getText().equals(senha.getText());

        if(!senhaValida) {
            return;
        }

        String apelido_banco = apelido_usuario.getText();

        String usuario_banco = nome_usuario.getText();

        String senha_banco = senha.getText();

        // TODO: criar método para armazenar no banco de dados
        Usuario usuario = new Usuario(apelido_banco, usuario_banco, senha_banco);

        System.out.println(usuario);

        fecharJanela(event);
    }

    public void fecharJanela(ActionEvent event) {
        stageCriarConta = (Stage) cenaCriarConta.getScene().getWindow();
        stageCriarConta.close();
    }

    public void fecharJanelaComAviso(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Sair");
        alert.setHeaderText("Você está prestes a sair!");
        alert.setContentText("Qualquer alteração não salva será perdida!");

        if(alert.showAndWait().get() == ButtonType.OK) {
            stageCriarConta = (Stage) cenaCriarConta.getScene().getWindow();
            stageCriarConta.close();
        }
    }
}
