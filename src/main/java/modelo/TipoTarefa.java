package modelo;

import jakarta.persistence.*;

@Entity(name = "TipoTarefa")
@Table
public class TipoTarefa {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_categoria;

    @Column
    private String nome;

    @Column
    private Boolean excluida;

    public TipoTarefa () {}

    public TipoTarefa(String nome, Boolean excluida) {
        super();
        this.nome = nome;
        this.excluida = excluida;
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

}
