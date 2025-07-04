package Control;

import DAO.EmpresaDAO;
import DAO.OperacoesDAO;
import Model.EXEPTIONS.ExistingInstance;
import Model.Empresa;
import Model.EXEPTIONS.InvalidFormatException;
import Model.ENUMS.*;

import java.util.List;


/**
 * Controlador responsável por gerenciar operações relacionadas a empresas.
 * Implementa a interface OperacoesController para empresas.
 */
public class EmpresaController implements OperacoesController<Empresa>{
    //estamos propagando o InvalidFormatException para, idealmente, a main ou onde será feita a entrada de dados a fim de
    //colocar essa resolução em um bloco while e resolver a exception

    /**
     * Valida e cria uma nova empresa no sistema.
     *
     * @param empresa A empresa a ser validada e criada
     * @return A empresa criada
     * @throws InvalidFormatException Quando o formato dos dados da empresa é inválido
     * @throws ExistingInstance       Quando já existe uma empresa com os mesmos dados
     * @throws NullPointerException   Quando CNPJ, Nome ou Status são nulos
     */
    @Override
    public Empresa validateAndCreate(Empresa empresa) throws InvalidFormatException, ExistingInstance {
        if( empresa.getCNPJ() == null || empresa.getName() == null || empresa.getStatus() == null) throw new NullPointerException("Não pode ter argumentos nulos como: CNPJ, Nome e/ou Status");
        validateCNPJ(empresa.getCNPJ());
        String validatedName = validateName(empresa.getName());
        EmpresaDAO empDAO = new EmpresaDAO();
        List<Empresa> empresas = empDAO.searchAll();
        if(empresas.contains(empresa)){
            throw new ExistingInstance("Já existe um objeto com esses atributos!");
        }
        empDAO.create(empresa);
        return empresa;
    }

    /**
     * Valida o formato do CNPJ.
     *
     * @param CNPJ O CNPJ a ser validado
     * @throws InvalidFormatException Quando o formato do CNPJ é inválido
     */
    private static void validateCNPJ(String CNPJ)throws InvalidFormatException {
        if (!CNPJ.matches("\\d{2}[.\\s]?\\d{3}[.\\s]?\\d{3}/\\d{4}-\\d{2}")) throw new InvalidFormatException(CNPJ, ValidsFormats.CNPJ);
    }

    /**
     * Valida e formata o nome da empresa.
     *
     * @param name O nome a ser validado
     * @return O nome formatado
     * @throws InvalidFormatException Quando o formato do nome é inválido
     */
    private static String validateName(String name) throws InvalidFormatException{
        if (name.isBlank() || name.matches("^[0-9]+$")) throw new InvalidFormatException(name, ValidsFormats.ENTERPRISE_NAME);
        return name.replaceAll("[,\\-_.]", " ");
    }

    /**
     * Define o código do proprietário para uma empresa.
     *
     * @param id  O código do proprietário
     * @param obj A empresa a ser atualizada
     */
    public static void putOwnerId (int id, Empresa obj){
        obj.setCodDono(id);
        EmpresaDAO empDao = new EmpresaDAO();
        empDao.update(obj);
    }
}
