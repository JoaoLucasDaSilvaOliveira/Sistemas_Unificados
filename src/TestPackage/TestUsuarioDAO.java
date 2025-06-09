package TestPackage;

import DAO.UsuarioDAO;
import Model.EXEPTIONS.ExistingInstance;
import Model.EXEPTIONS.InvalidFormatException;
import Model.Empresa;
import Model.Usuario;

public class TestUsuarioDAO {
    public static void main(String[] args) throws ExistingInstance, InvalidFormatException {
        UsuarioDAO dao = new UsuarioDAO();
        Empresa e1 = Empresa.create("01.739.876/0001-76","Sonhos House", Empresa.Status.ACTIVE);

        Usuario u1 = Usuario.create("kaua", "kauafoda@gmail.com", "0000", e1);
    }
}
