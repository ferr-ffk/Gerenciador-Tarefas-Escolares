package br.com.ifsp.nando.gerenciadortarefasescolares.modelo;

import jakarta.persistence.*;

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
    private Integer idUsuario;

    @Column
    private String apelido;

    @Column
    private String nomeUsuario;

    @Column
    private String senha;

    @Transient
    private Relatorio relatorio;

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

    public Relatorio gerarRelatorio() {
        Relatorio relatorio = Relatorio.gerarRelatorio(this);

        this.relatorio = relatorio;
        return relatorio;
    }

    @Override
    public String toString() {
        return "USUARIO [" + idUsuario + "]: {" + apelido + ", " + nomeUsuario + ", " + senha + "}";
    }

}
