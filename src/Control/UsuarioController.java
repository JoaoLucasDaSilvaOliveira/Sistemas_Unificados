package Control;

import DAO.UsuarioDAO;
import Model.ENUMS.ValidsFormats;
import Model.EXEPTIONS.ExistingInstance;
import Model.EXEPTIONS.InvalidFormatException;
import Model.Empresa;
import Model.Usuario;

import java.util.List;

/***
 * This class receives the calls from the user. It's purpose is to validate the entrys
 */
public class UsuarioController implements OperacoesController<Usuario>{
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

    private static void validateEmail(String email)throws InvalidFormatException {
        if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) throw new InvalidFormatException(email, ValidsFormats.EMAIL);
    }

    private static String validateName(String name) throws InvalidFormatException{
        if (name.isBlank() || name.matches("^[0-9]+$")) throw new InvalidFormatException(name, ValidsFormats.USER_NAME);
        return name.replaceAll("[,\\-_.]", " ");
    }
}
