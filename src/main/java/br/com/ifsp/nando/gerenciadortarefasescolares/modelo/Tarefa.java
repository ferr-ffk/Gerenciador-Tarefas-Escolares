package br.com.ifsp.nando.gerenciadortarefasescolares.modelo;

import jakarta.persistence.*;

import java.util.Date;

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
    private Date dataVencimento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_categoria")
    private TipoTarefa categoria;

    @Column
    private Boolean concluida;

    @Column
    private Boolean excluida;

    public Tarefa() {}

    public Tarefa(String titulo, String descricao, Date dataVencimento, TipoTarefa categoria) {
        super();
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
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

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
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
}
