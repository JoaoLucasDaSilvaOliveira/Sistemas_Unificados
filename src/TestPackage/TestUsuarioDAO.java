package TestPackage;

import DAO.UsuarioDAO;
import Model.EXEPTIONS.ExistingInstance;
import Model.EXEPTIONS.InvalidFormatException;
import Model.Empresa;
import Model.Usuario;

public class TestUsuarioDAO {
    public static void main(String[] args) throws ExistingInstance, InvalidFormatException {

        UsuarioDAO usuDAO = new UsuarioDAO();

        Empresa emp1 = Empresa.create("02.739.876/0001-76","Bentos House", Empresa.Status.ACTIVE);
        Empresa emp2 = Empresa.create("03.739.876/0001-76","Mentos House", Empresa.Status.ACTIVE);
        Usuario e1 = Usuario.create("Pedro", "pedro123@gmail.com", "0000", emp1);
        e1.includeEmpresa(emp2);
        usuDAO.update(e1);
    }
}
