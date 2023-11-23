package br.com.ifsp.nando.gerenciadortarefasescolares.services;

import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Tarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TarefaService {

    static Session session = HibernateUtil.getSessionFactory().openSession();

    /**
     * <p> Cria uma nova tarefa no banco
     *
     * @param t A tarefa a ser cadastrado
     */
    public static void createTarefa(Tarefa t) {
        Transaction transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
    }

    /**
     * Retorna uma tarefa cadastrada no banco de dados
     *
     * @param id O id da tarefa a ser buscado
     * @return A tarefa se esta existir, nulo se não for cadastrada
     */
    public static Tarefa readTarefa(Integer id) {
        return session.get(Tarefa.class, id);
    }

    /**
     * Método que busca todas as tarefas no banco
     *
     * @return A lista de tarefas cadastrados
     */
    public static List<Tarefa> readTarefas() {
        return session.createQuery("from Tarefa", Tarefa.class).list();
    }

    /**
     * Atualiza uma tarefa no banco, fornecendo o objeto de persistência e um novo objeto com os atributos novos
     *
     * @param tarefaAntiga A tarefa antiga a ser atualizada
     */
    public static void updateTarefa(Tarefa tarefaAntiga) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(tarefaAntiga);

        transaction.commit();
        session.close();
    }

    /**
     * Deleta permanentemente uma tarefa no banco de dados
     *
     * @param id o id da tarefa a ser deletada
     */
    public static void deleteTarefa(Integer id) {
        Tarefa t = session.get(Tarefa.class, id);

        if (t == null) {
            throw new RuntimeException("Tarefa não encontrada!");
        }

        Transaction transaction = session.beginTransaction();
        session.remove(t);
        transaction.commit();
    }

    /**
     * Deleta permanentemente uma tarefa no banco de dados à partir do objeto fornecido
     *
     * @param tarefa O objeto tarefa a ser removida
     */
    public static void deleteTarefa(Tarefa tarefa) {
        Transaction transaction = session.beginTransaction();
        session.remove(tarefa);
        transaction.commit();
    }
}
