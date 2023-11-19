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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario")
    private Usuario idUsuario;

    public Tarefa() {}

    public Tarefa(String titulo, String descricao, LocalDate dataVencimento, TipoTarefa categoria, Usuario idUsuario) {
        super();
        this.titulo = titulo;
        this.descricao = descricao;

        this.dataVencimento = dataVencimento;

        this.idCategoria = categoria;
        this.concluida = false;
        this.idUsuario = idUsuario;
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
        this.concluida = true;
    }

    public void excluir() {
        this.excluida = true;
    }

    public TipoTarefa getTipoTarefa() { return this.idCategoria; }

    public Usuario getIdUsuario() {
        return this.idUsuario;
    }

    @Override
    public String toString() {
        return "TAREFA: {" + titulo + ", " + descricao + ", CATEGORIA: (" + idCategoria + "), DATA: (" + dataVencimento.toString() + ")}";
    }
}
