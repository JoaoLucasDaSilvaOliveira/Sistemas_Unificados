package Control;

import Model.Empresa;
import Model.EXEPTIONS.InvalidFormatException;

/***
 * This class receives the calls from the user. It's purpose is to validate the entrys and call Empresa's constructor
 */
public final class EmpresaController{
    //estamos propagando o InvalidFormatException para, idealmente, a main ou onde será feita a entrada de dados a fim de
    //colocar essa resolução em um bloco while e resolver a exception
    public boolean validateEntrys (String CNPJ, String name, Empresa.Status status) throws InvalidFormatException{
        if(CNPJ == null || name == null || status == null) throw new NullPointerException("Não pode ter argumentos nulos como: CNPJ, Nome e/ou Status");
        validateCNPJ(CNPJ);
    }
    public void validateCNPJ(String CNPJ)throws InvalidFormatException {
        if (!CNPJ.matches("\\d{2}[.\\s]?\\d{3}[.\\s]?\\d{3}/\\d{4}-\\d{2}")) throw new InvalidFormatException(CNPJ);
    }
}
