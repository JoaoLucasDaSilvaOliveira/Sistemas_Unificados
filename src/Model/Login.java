package Model;

import Model.EXEPTIONS.NoSuchArgument;
import Model.EXEPTIONS.WrongPass;

/**
 * Interface genérica que define o contrato para autenticação de usuários no sistema.
 *
 * @param <T> o tipo de retorno da autenticação (ex: objeto de sessão, token, entidade de usuário etc.)
 */
public interface Login<T> {

    /**
     * Realiza a autenticação de um usuário com base em email e senha.
     *
     * @param email    o e-mail do usuário.
     * @param password a senha do usuário.
     * @return um objeto do tipo {@code T} representando o resultado da autenticação.
     * @throws NoSuchArgument se o e-mail ou a senha estiverem incorretos ou não encontrados.
     */
    public T toLogin(String email, String password) throws NoSuchArgument, WrongPass;

    /**
     * Exibe informações básicas sobre o login realizado, formatadas para o console.
     *
     * @param userName       nome do usuário.
     * @param email          e-mail do usuário.
     * @param enterpriseName nome da empresa associada.
     * @param CNPJ           CNPJ da empresa.
     */
    public static void showLoginInfos(String userName, String email, String enterpriseName, String CNPJ) {
        System.out.printf("Empresa: CNPJ: %-25s Nome: %s\n", CNPJ, enterpriseName);
        System.out.printf("Usuário: Nome: %-25s Email: %s\n", userName, email);
    }
}
