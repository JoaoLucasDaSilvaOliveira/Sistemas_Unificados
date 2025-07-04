package Model.EXEPTIONS;

/**
 * Exceção lançada quando um argumento específico não é encontrado ou é inválido.
 */
public class NoSuchArgument extends Exception {
    /**
     * Construtor para criar uma nova exceção com uma mensagem específica.
     *
     * @param message A mensagem detalhando o motivo da exceção
     */
    public NoSuchArgument(String message) {
        super(message);
    }
}
