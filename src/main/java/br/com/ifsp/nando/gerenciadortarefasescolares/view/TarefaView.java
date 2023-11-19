package br.com.ifsp.nando.gerenciadortarefasescolares.view;

import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Tarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.TipoTarefa;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.time.format.DateTimeFormatter;

public class TarefaView extends HBox {

    private Tarefa tarefa;

    public TarefaView(Tarefa tarefa) {
        this.tarefa = tarefa;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Label titulo = new Label(tarefa.getTitulo());
        Label data = new Label(dtf.format(tarefa.getDataVencimento()));
        Label descricao = new Label(tarefa.getDescricao());

        TipoTarefa tipoTarefa = tarefa.getTipoTarefa();

        Rectangle simbolo = new Rectangle();
        simbolo.setFill(Color.valueOf(tipoTarefa.getCor()));

        getChildren().addAll(titulo, data, descricao, simbolo);
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }
}
