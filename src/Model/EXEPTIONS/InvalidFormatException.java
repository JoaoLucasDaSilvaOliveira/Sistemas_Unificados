package Model.EXEPTIONS;

import Model.ENUMS.ValidsFormats;

public class InvalidFormatException extends Exception {

    public InvalidFormatException(String message, ValidsFormats regex) {
        super("Formato inválido!\nEntrada do usuário: "+ message+"\nFormato esperado: "+ regex.getRegex());
    }
}
