package br.com.ifsp.nando.gerenciadortarefasescolares.util;

import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class JavaFXUtil {

    public static void setJanelaPadrao(Stage stage) {
        stage.getIcons().add(new Image(Objects.requireNonNull(JavaFXUtil.class.getResourceAsStream("/br/com/ifsp/nando/gerenciadortarefasescolares/Icons/icon.jpg"))));
        stage.setTitle("Gerenciador de tarefas escolares");
    }
}
