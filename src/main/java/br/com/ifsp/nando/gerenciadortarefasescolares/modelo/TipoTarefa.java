package br.com.ifsp.nando.gerenciadortarefasescolares.modelo;

import br.com.ifsp.nando.gerenciadortarefasescolares.util.ColorUtil;
import jakarta.persistence.*;
import javafx.scene.paint.Color;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import static br.com.ifsp.nando.gerenciadortarefasescolares.util.ColorUtil.paraHexString;

@Entity(name = "TipoTarefa")
@Table
public class TipoTarefa {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    @Column
    private String nome;

    @Column
    private Boolean excluida;

    @Column
    private String cor; // acontece que pra armazenar cor no banco de dados é uma bomba, então vai ter q armazenar o hex msm

    @JoinColumn(name = "idUsuario")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario idUsuario;

    public TipoTarefa() {
    }

    public TipoTarefa(String nome, Color cor, Usuario idUsuario) {
        super();
        this.nome = nome;
        this.excluida = false;
        this.cor = paraHexString(cor);
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getExcluida() {
        return excluida;
    }

    public void excluir() {
        this.excluida = !excluida;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Usuario getUsuario() {
        return this.idUsuario;
    }

    public Color getCor() {
        return ColorUtil.hexParaRGB(cor);
    }

}
