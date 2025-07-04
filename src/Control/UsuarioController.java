package Control;

import DAO.UsuarioDAO;
import Model.ENUMS.ValidsFormats;
import Model.EXEPTIONS.ExistingInstance;
import Model.EXEPTIONS.InvalidFormatException;
import Model.Empresa;
import Model.Usuario;

import java.util.List;


/**
 * Controlador responsável por gerenciar operações relacionadas a usuários.
 * Implementa a interface OperacoesController para usuários.
 */
public class UsuarioController implements OperacoesController<Usuario>{
    /**
     * Valida e cria um novo usuário no sistema.
     *
     * @param object O usuário a ser validado e criado
     * @return O usuário criado
     * @throws InvalidFormatException Quando o formato dos dados do usuário é inválido
     * @throws ExistingInstance       Quando já existe um usuário com os mesmos dados
     * @throws NullPointerException   Quando email, senha ou nome são nulos
     */
    @Override
    public Usuario validateAndCreate(Usuario object) throws InvalidFormatException, ExistingInstance {
        if (object == null || object.getEmail() == null || object.getSenha() == null || object.getName() == null) throw new NullPointerException("Usuário não pode ter referências nulas");
        validateEmail(object.getEmail());
        validateName(object.getName());
        UsuarioDAO usuDAO = new UsuarioDAO();
        List<Usuario> usuarios = usuDAO.searchAll();
        if(usuarios.contains(object)){
            throw new ExistingInstance("Já existe um objeto com esses atributos!");
        }
        usuDAO.create(object);
        return object;
    }

    /**
     * Valida o formato do email.
     *
     * @param email O email a ser validado
     * @throws InvalidFormatException Quando o formato do email é inválido
     */
    private static void validateEmail(String email)throws InvalidFormatException {
        if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) throw new InvalidFormatException(email, ValidsFormats.EMAIL);
    }

    /**
     * Valida e formata o nome do usuário.
     *
     * @param name O nome a ser validado
     * @return O nome formatado
     * @throws InvalidFormatException Quando o formato do nome é inválido
     */
    private static String validateName(String name) throws InvalidFormatException{
        if (name.isBlank() || name.matches("^[0-9]+$")) throw new InvalidFormatException(name, ValidsFormats.USER_NAME);
        return name.replaceAll("[,\\-_.]", " ");
    }
}
