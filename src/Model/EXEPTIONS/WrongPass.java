package Model.EXEPTIONS;

/**
 * Exceção lançada quando uma senha incorreta é fornecida.
 * Esta exceção estende RuntimeException e é usada para tratar erros de autenticação.
 */
public class WrongPass extends Exception {
    /**
     * Constrói uma nova exceção WrongPass com a mensagem especificada.
     *
     * @param message A mensagem detalhando o erro de senha
     */
    public WrongPass(String message) {
        super(message);
    }
}
