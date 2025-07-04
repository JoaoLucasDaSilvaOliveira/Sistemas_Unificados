package Model.EXEPTIONS;

/**
 * Exceção lançada quando uma instância já existe no sistema.
 * Esta exceção é utilizada para indicar tentativas de criar objetos duplicados.
 */
public class ExistingInstance extends Exception {
    /**
     * Construtor para a exceção de instância existente.
     *
     * @param message A mensagem descritiva do erro
     */
    public ExistingInstance(String message) {
        super(message);
    }
}
