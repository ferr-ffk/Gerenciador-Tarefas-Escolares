package br.com.ifsp.nando.gerenciadortarefasescolares.modelo;

import jakarta.persistence.*;
import javafx.scene.paint.Color;

import static br.com.ifsp.nando.gerenciadortarefasescolares.util.ColorUtil.toHexString;

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
    private String cor;

    @JoinColumn(name = "idUsuario")
    @ManyToOne(cascade = CascadeType.ALL)
    private Usuario idUsuario;

    public TipoTarefa() {}

    public TipoTarefa(String nome, Color cor, Usuario idUsuario) {
        super();
        this.nome = nome;
        this.excluida = false;
        this.cor = toHexString(cor);
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "CATEGORIA: {" + nome + ", " + cor + "}";
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
        this.excluida = true;
    }

    public void setCor(String cor) { this.cor = cor; }

    public String getCor() { return cor; }

}
