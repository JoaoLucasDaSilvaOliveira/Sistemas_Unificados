package Model.EXEPTIONS;

import Model.ENUMS.ValidsFormats;

/**
 * Exceção lançada quando um formato de entrada é inválido.
 * Esta exceção é utilizada para indicar que o formato dos dados fornecidos
 * não corresponde ao padrão esperado.
 */
public class InvalidFormatException extends Exception {

    /**
     * Construtor para criar uma nova exceção de formato inválido.
     *
     * @param message A mensagem contendo o valor de entrada inválido
     * @param regex   O formato esperado para a entrada
     */
    public InvalidFormatException(String message, ValidsFormats regex) {
        super("Formato inválido!\nEntrada do usuário: " + message + "\nFormato esperado: " + regex.getRegex());
    }
}
