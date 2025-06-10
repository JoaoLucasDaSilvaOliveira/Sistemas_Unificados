package TestPackage;

import DAO.UsuarioDAO;
import Model.EXEPTIONS.ExistingInstance;
import Model.EXEPTIONS.InvalidFormatException;
import Model.Empresa;
import Model.Usuario;

public class TestUsuarioDAO {
    public static void main(String[] args) throws ExistingInstance, InvalidFormatException {
        UsuarioDAO dao = new UsuarioDAO();

        System.out.println(dao.searchByValue("Pedro"));
    }
}
