package br.com.ifsp.nando.gerenciadortarefasescolares.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * A entidade tarefa representa um dos afazares do usuário, poderá alterar o nome, adicionar uma descrição e concluí-las
 *
 * @author fefe
 */
@Entity(name = "Tarefa")
@Table
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String titulo;

    @Column
    private String descricao;

    @Column
    private LocalDate dataVencimento;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCategoria")
    private TipoTarefa idCategoria;

    @Column
    private Boolean concluida;

    @Column
    private Boolean excluida;

    @JoinColumn(name = "idUsuario")
    @ManyToOne
    private Usuario idUsuario;

    public Tarefa() {}

    public Tarefa(String titulo, String descricao, LocalDate dataVencimento, TipoTarefa idCategoria, Usuario idUsuario) {
        super();
        this.titulo = titulo;
        this.descricao = descricao;

        this.dataVencimento = dataVencimento;

        this.idUsuario = idUsuario;
        this.idUsuario.criarTarefa();
        this.idCategoria = idCategoria;

        this.excluida = false;
        this.concluida = false;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Boolean getStatus() {
        return concluida;
    }

    public void concluir() {
        this.concluida = !concluida;
    }

    public boolean getConcluida() { return concluida; }

    public boolean getExcluida() { return excluida; }

    public void excluir() {
        this.excluida = !excluida;
    }

    public TipoTarefa getTipoTarefa() { return this.idCategoria; }

    public Usuario getUsuario() { return this.idUsuario; }

    @Override
    public String toString() {
        return "TAREFA: {" + titulo + ", " + descricao + ", CATEGORIA: (" + idCategoria + "), DATA: (" + dataVencimento.toString() + ")}";
    }
}
