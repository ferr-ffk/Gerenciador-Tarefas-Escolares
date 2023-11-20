package br.com.ifsp.nando.gerenciadortarefasescolares.services;

import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Tarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.TipoTarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Usuario;
import br.com.ifsp.nando.gerenciadortarefasescolares.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UsuarioService {

    private final static Session session = HibernateUtil.getSessionFactory().openSession();

    /**
     * <p> Cria um novo usuário no banco
     *
     * @param u O usuário a ser cadastrado
     */
    public static void createUsuario(Usuario u) {
        Transaction transaction = session.beginTransaction();
        session.persist(u);
        transaction.commit();
    }

    /**
     * Método que busca todos os usuários no banco
     *
     * @return A lista de usuários cadastrados
     */
    public static List<Usuario> readUsuarios() {
        return session.createQuery("from Usuario", Usuario.class).list();
    }

    /**
     * Retorna um usuário cadastrado no banco de dados
     *
     * @param id O id do usuário a ser buscado
     * @return O usuário se este existir, nulo se não for cadastrado
     */
    public static Usuario readUsuario(Integer id) {
        return session.get(Usuario.class, id);
    }

    /**
     * Retorna a lista de tarefas de um usuário a partir do objeto Usuario fornecido
     *
     * @param usuario O usuário a ser buscado
     * @return Todas as tarefas cadastradas no banco
     */
    public static List<Tarefa> readTarefaUsuario(Usuario usuario) {
        return session.createQuery("FROM Tarefa t WHERE t.idUsuario = :id", Tarefa.class).setParameter("id", usuario).list();
    }

    /**
     * Retorna a lista de categorias pertencentes a um usuário
     *
     * @param usuario O usuario a ser buscado
     * @return A lista de categorias que o usuário criou
     */
    public static List<TipoTarefa> readCategoriasUsuario(Usuario usuario) {
        return session.createQuery("FROM TipoTarefa t WHERE t.idUsuario = :id", TipoTarefa.class).setParameter("id", usuario).list();
    }

    /**
     * Altera um usuário no banco
     *
     * @param id o id do usuário a ser alterado
     * @param u  os dados do novo usuário
     */
    public static void updateUsuario(Integer id, Usuario u) {
        Transaction transaction = session.getTransaction();
        Usuario usuario = readUsuario(id);

        if (usuario == null) {
            throw new RuntimeException("O usuário de id " + id + " não existe!");
        }

        usuario = new Usuario(u.getNomeUsuario(), u.getApelido(), u.getSenha());

        session.persist(usuario);
        transaction.commit();
    }

    /**
     * Deleta permanentemente um usuário no banco de dados à partir do id fornecido
     *
     * @param id o id do usuário a ser deletado
     */
    public static void deleteUsuario(Integer id) {
        Transaction transaction = session.beginTransaction();
        Usuario u = session.get(Usuario.class, id);

        if (u == null) {
            throw new RuntimeException("Usuário não encontrado!");
        }

        session.remove(u);
        transaction.commit();
    }

    /**
     * Deleta permanentemente um usuário no banco de dados á partir do objeto fornecido
     *
     * @param usuario O usuário a ser deletado
     */
    public static void deleteUsuario(Usuario usuario) {
        Transaction transaction = session.beginTransaction();
        session.remove(usuario);
        transaction.commit();
    }
}
