package Control;

import DAO.EmpresaDAO;
import Model.Empresa;
import Model.EXEPTIONS.InvalidFormatException;
import Model.ENUMS.*;

import java.util.Iterator;
import java.util.List;

/***
 * This class receives the calls from the user. It's purpose is to validate the entrys and call Empresa's constructor
 */
public final class EmpresaController{
    //estamos propagando o InvalidFormatException para, idealmente, a main ou onde será feita a entrada de dados a fim de
    //colocar essa resolução em um bloco while e resolver a exception

    public static Empresa validateEntrys (Empresa empresa) throws InvalidFormatException{
        if(empresa.getCNPJ() == null || empresa.getName() == null || empresa.getStatus() == null) throw new NullPointerException("Não pode ter argumentos nulos como: CNPJ, Nome e/ou Status");
        validateCNPJ(empresa.getCNPJ());
        String validatedName = validateName(empresa.getName());
        EmpresaDAO empDAO = new EmpresaDAO();
        List<Empresa> empresas = empDAO.searchAll();
        if(!empresas.contains(empresa)){
            empDAO.create(empresa);
        }
        return empresa;
    }

    private static void validateCNPJ(String CNPJ)throws InvalidFormatException {
        if (!CNPJ.matches("\\d{2}[.\\s]?\\d{3}[.\\s]?\\d{3}/\\d{4}-\\d{2}")) throw new InvalidFormatException(CNPJ, ValidsFormats.CNPJ);
    }

    private static String validateName(String name) throws InvalidFormatException{
        if (name.isBlank() || name.matches("^[0-9]+$")) throw new InvalidFormatException(name, ValidsFormats.ENTERPRISE_NAME);
        return name.replaceAll("[,\\-_.]", " ");
    }
}
