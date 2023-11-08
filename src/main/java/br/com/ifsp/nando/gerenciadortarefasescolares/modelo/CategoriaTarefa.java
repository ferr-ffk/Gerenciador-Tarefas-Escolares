package br.com.ifsp.nando.gerenciadortarefasescolares.modelo;

import jakarta.persistence.*;

@Entity(name = "TipoTarefa")
@Table
public class CategoriaTarefa {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_categoria;

    @Column
    private String nome;

    @Column
    private Boolean excluida;

    @Column
    private String cor;

    public CategoriaTarefa() {}

    public CategoriaTarefa(String nome, String cor) {
        super();
        this.nome = nome;
        this.excluida = false;
        this.cor = cor;
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
