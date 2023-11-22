package br.com.ifsp.nando.gerenciadortarefasescolares.modelo;

public class Relatorio {

    private final double porcentagem;

    private final int tarefasConcluidas;

    private final int tarefasCriadas;

    public int getTarefasConcluidas() {
        return tarefasConcluidas;
    }

    public int getTarefasCriadas() {
        return tarefasCriadas;
    }

    public double getPorcentagem() {
        return this.porcentagem;
    }

    private Relatorio(Usuario usuario) {
        tarefasConcluidas = usuario.getNumeroTarefasConcluidas();
        tarefasCriadas = usuario.getNumeroTarefasCriadas();

        this.porcentagem = (double) (tarefasConcluidas * 100) / tarefasCriadas;
    }

    public static Relatorio gerarRelatorio(Usuario usuario) {
       return new Relatorio(usuario);
    }

    @Override
    public String toString() {
        return "Relatorio{" +
                "porcentagem=" + porcentagem +
                ", tarefasConcluidas=" + tarefasConcluidas +
                ", tarefasCriadas=" + tarefasCriadas +
                '}';
    }
}
