package br.com.ifsp.nando.gerenciadortarefasescolares.modelo;

import br.com.ifsp.nando.gerenciadortarefasescolares.services.UsuarioService;
import jakarta.persistence.*;

/**
 * Os usuários devem poder fazer login no sistema. Cada usuário possui uma lista
 * de tarefas para depois monitorá-las
 *
 * @author Fernando Freitas
 */
@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer idUsuario;

    @Column
    private String apelido;

    @Column
    private String nomeUsuario;

    @Column
    private String senha;

    @Column
    private int numeroTarefasConcluidas;

    @Column
    private int numeroTarefasCriadas;

    @Column
    private boolean excluido;

    public Usuario() {
    }

    public Usuario(String apelido, String nomeUsuario, String senha) {
        super();
        this.apelido = apelido;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nome_usuario) {
        this.nomeUsuario = nome_usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void concluirTarefa() {
        this.numeroTarefasConcluidas++;
    }

    public void criarTarefa() {
        this.numeroTarefasCriadas++;
    }

    public int getNumeroTarefasConcluidas() {
        return this.numeroTarefasConcluidas;
    }

    public int getNumeroTarefasCriadas() {
        return this.numeroTarefasCriadas;
    }

    @Override
    public String toString() {
        return "USUARIO [" + idUsuario + "]: {" + apelido + ", " + nomeUsuario + ", " + senha + ", concluiu " + numeroTarefasConcluidas + " de " + numeroTarefasCriadas + "}";
    }

    public void excluir() {
        this.excluido = true;
        UsuarioService.updateUsuario(this);
    }

    public boolean isExcluido() {
        return this.excluido;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() { return this.idUsuario; }
}
