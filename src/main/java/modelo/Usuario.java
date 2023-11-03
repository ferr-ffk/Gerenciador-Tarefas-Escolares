package modelo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Os usuários devem poder fazer login no sistema. Cada usuário possui uma lista
 * de tarefas para depois monitorá-las
 *
 * @author Fernando Freitas
 *
 */
@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String apelido;

    @Column
    private String nome_usuario;

    @Column
    private String senha;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tarefas")
    private List<Tarefa> tarefas;

    public Usuario() {
    }

    public Usuario(String apelido, String nome_usuario, String senha) {
        super();
        this.apelido = apelido;
        this.nome_usuario = nome_usuario;
        this.senha = senha;
        this.tarefas = new ArrayList<Tarefa>();
    }

    public void adicionarTarefa(Tarefa t) {
        tarefas.add(t);
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "USUARIO [" + id + "]:" + apelido + ", " + nome_usuario;
    }

    public List<Tarefa> getTarefas() {
        return this.tarefas;
    }
}
