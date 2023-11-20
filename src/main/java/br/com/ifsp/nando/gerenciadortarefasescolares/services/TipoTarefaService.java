package br.com.ifsp.nando.gerenciadortarefasescolares.services;

import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.TipoTarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TipoTarefaService {

    private final static Session session = HibernateUtil.getSessionFactory().openSession();

    /**
     * Cria uma nova categoria amarrada a um usuário no banco de dados à partir de uma instância dela
     *
     * @param tipoTarefa A nova categoria a ser adicionada
     */
    public static void createTipoTarefa(TipoTarefa tipoTarefa) {
        Transaction transaction = session.beginTransaction();
        session.persist(tipoTarefa);
        transaction.commit();
    }

    /**
     * Retorna a lista de todas as categorias cadastradas
     *
     * @return Uma lista de todas categorias cadastradas
     */
    public static List<TipoTarefa> readTipoTarefas() {
        return session.createQuery("FROM TipoTarefa ", TipoTarefa.class).list();
    }

    /**
     * Retorna uma categoria à partir do id fornecido
     *
     * @param id O id da categoria a ser removida
     * @return A categoria se  encontrada, nulo se não existir
     */
    public static TipoTarefa readTipoTarefa(Integer id) {
        return session.get(TipoTarefa.class, id);
    }

    /**
     * Atualiza uma categoria à partir de um novo objeto fornecido
     *
     * @param id O id da categoria
     * @param novaTipoTarefa Os dados da nova categoria
     */
    public static void updateTipoTarefa(Integer id, TipoTarefa novaTipoTarefa) {
        Transaction transaction = session.beginTransaction();
        TipoTarefa tipoTarefa = readTipoTarefa(id);

        if(tipoTarefa == null) {
            throw new RuntimeException("A tarefa de id " + id + " não existe!");
        }

        tipoTarefa = novaTipoTarefa;

        session.persist(tipoTarefa);
        transaction.commit();
    }

    /**
     * Deleta uma tarefa do banco de dados à partir do id fornecido
     *
     * @param id O id da categoria a ser removida
     */
    public static void deleteTipoTarefa(Integer id) {
        Transaction transaction = session.beginTransaction();
        TipoTarefa tipoTarefa = readTipoTarefa(id);

        if(tipoTarefa == null) {
            throw new RuntimeException("TipoTarefa não encontrado!");
        }

        session.remove(tipoTarefa);
        transaction.commit();
    }

    /**
     * Deleta uma tarefa do banco de dados à partir do objeto fornecido
     *
     * @param tipoTarefa A categoria a ser removida
     */
    public static void deleteTipoTarefa(TipoTarefa tipoTarefa) {
        Transaction transaction = session.beginTransaction();
        session.remove(tipoTarefa);
        transaction.commit();
    }
}
