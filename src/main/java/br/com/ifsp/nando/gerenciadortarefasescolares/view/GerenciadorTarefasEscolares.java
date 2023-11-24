package br.com.ifsp.nando.gerenciadortarefasescolares.view;

import br.com.ifsp.nando.gerenciadortarefasescolares.Main;
import br.com.ifsp.nando.gerenciadortarefasescolares.util.JavaFXUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.io.StringWriter;

public class GerenciadorTarefasEscolares extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            JavaFXUtil.setJanelaPadrao(stage);

            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            ExibirJanelaExcecao(e);
        }
    }

    public static void ExibirJanelaExcecao(Exception e) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Exceção na execução do programa");

        final DialogPane dialogPane = dialog.getDialogPane();

        dialogPane.getButtonTypes().addAll(ButtonType.OK);
        dialogPane.getButtonTypes().addAll(ButtonType.CLOSE);

        dialogPane.setContentText("Essa exceção veio de um processo interno");
        dialog.initModality(Modality.APPLICATION_MODAL);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        e.printStackTrace(pw);
        pw.close();

        Label label = new Label("Stacktrace da exceção:");

        TextArea textArea = new TextArea(sw.toString());
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);

        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane raiz = new GridPane();
        raiz.setVisible(true);
        raiz.setMaxWidth(Double.MAX_VALUE);
        raiz.add(label, 0, 0);
        raiz.add(textArea, 0, 1);
        dialogPane.setExpandableContent(raiz);
        dialog.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}
