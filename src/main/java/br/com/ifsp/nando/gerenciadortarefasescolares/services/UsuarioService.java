package br.com.ifsp.nando.gerenciadortarefasescolares.services;

import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Tarefa;
import br.com.ifsp.nando.gerenciadortarefasescolares.modelo.Usuario;
import br.com.ifsp.nando.gerenciadortarefasescolares.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UsuarioService {

    static Session session = HibernateUtil.getSessionFactory().openSession();

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
     * Retorna a lista de tarefas de um usuário a partir do id fornecido dele
     *
     * @param id O id do usuário a ser buscado
     * @return Todas as tarefas cadastradas no banco
     */
    public static List<Tarefa> readTarefaUsuario(Integer id) {
        return session.createQuery("from Tarefa where Tarefa.usuario = :id", Tarefa.class).setParameter("id", id).list();
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
     * Altera um usuário no banco
     *
     * @param id o id do usuário a ser alterado
     * @param u os dados do novo usuário
     */
    public static void updateUsuario(Integer id, Usuario u) {
        Usuario usuario = readUsuario(id);

        usuario = new Usuario(u.getNome_usuario(), u.getApelido(), u.getSenha());

        Transaction transaction = session.getTransaction();
        session.persist(usuario);
        transaction.commit();
    }

    /**
     * Deleta permanentemente um usuário no banco de dados
     *
     * @param id o id do usuário a ser deletado
     */
    public static void deleteUsuario(Integer id) {
        Usuario u = session.get(Usuario.class, id);

        if(u == null) {
            throw new RuntimeException("Usuário não encontrado!");
        }

        Transaction transaction = session.beginTransaction();
        session.remove(u);
        transaction.commit();
    }


}
