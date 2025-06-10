package TestPackage;

import Model.EXEPTIONS.ExistingInstance;
import Model.EXEPTIONS.InvalidFormatException;
import Model.Empresa;
import Model.Usuario;

import java.util.List;

public class TestEmpresaDAO {
    public static void main(String[] args) throws ExistingInstance, InvalidFormatException {
        
        Empresa empresa = Empresa.create("02.739.876/0001-76","Bentos House", Empresa.Status.ACTIVE);
        Empresa empresa2 = Empresa.create("03.739.876/0001-76","Mentos House", Empresa.Status.ACTIVE);
        Usuario usuario = Usuario.create("jonas", "jonasgay", "0000", empresa);

    }
}
