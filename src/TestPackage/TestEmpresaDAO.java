package TestPackage;

import DAO.EmpresaDAO;
import Model.EXEPTIONS.ExistingInstance;
import Model.EXEPTIONS.InvalidFormatException;
import Model.Empresa;

import java.util.List;

public class TestEmpresaDAO {
    public static void main(String[] args) throws ExistingInstance, InvalidFormatException {
        EmpresaDAO empresaDAO = new EmpresaDAO();

        Empresa empresa = empresaDAO.searchByValue("Bentos");
        empresaDAO.delete(empresa);

    }
}
