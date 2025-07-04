package DAO;

import java.util.*;

/**
 * Interface genérica que define operações básicas de persistência para objetos do tipo {@code T}.
 *
 * @param <T> tipo da entidade que será manipulada pelo DAO.
 * @param <K> tipo da chave usada para buscas específicas (ex: ID, CNPJ).
 * @param <V> tipo do valor usado para buscas por valor (ex: nome, outro campo).
 */
public interface OperacoesDAO<T, K, V> {

    /**
     * Adiciona um novo objeto ao repositório.
     *
     * @param creating objeto a ser criado.
     * @return {@code true} se a criação foi bem-sucedida, {@code false} caso contrário.
     */
    boolean create(T creating);

    /**
     * Retorna uma lista com todos os objetos armazenados.
     *
     * @return lista de todos os objetos.
     */
    List<T> searchAll();

    /**
     * Busca um objeto pelo valor da chave primária.
     *
     * @param searching chave para busca.
     * @return objeto correspondente, ou {@code null} se não encontrado.
     */
    T searchByKey(K searching);

    /**
     * Busca um objeto pelo valor de algum campo não-chave.
     *
     * @param searching valor para busca.
     * @return objeto correspondente, ou {@code null} se não encontrado.
     */
    T searchByValue(V searching);

    /**
     * Remove um objeto do repositório.
     *
     * @param deleting objeto a ser removido.
     * @return {@code true} se a remoção foi bem-sucedida, {@code false} caso contrário.
     */
    boolean delete(T deleting);

    /**
     * Atualiza um objeto existente no repositório.
     *
     * @param updating objeto com dados atualizados.
     * @return {@code true} se a atualização foi bem-sucedida, {@code false} caso contrário.
     */
    boolean update(T updating);

    /**
     * Salva uma lista completa de objetos no repositório.
     *
     * @param savingList lista de objetos a ser salva.
     * @return {@code true} se o salvamento foi bem-sucedido, {@code false} caso contrário.
     */
    boolean saveList(List<T> savingList);
}
