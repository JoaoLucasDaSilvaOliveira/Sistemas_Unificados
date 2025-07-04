package Model.EXEPTIONS;

/**
 * Exceção lançada quando uma referência específica não é encontrada no sistema.
 * Esta exceção é utilizada para indicar que um objeto ou recurso solicitado não existe.
 */
public class NoSuchReference extends Exception {
    /**
     * Constrói uma nova exceção NoSuchReference com a mensagem especificada.
     *
     * @param message A mensagem detalhando o motivo da exceção
     */
    public NoSuchReference(String message) {
        super(message);
    }
}
