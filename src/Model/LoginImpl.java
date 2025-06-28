package Model;

import DAO.EmpresaDAO;
import DAO.UsuarioDAO;
import Model.EXEPTIONS.NoSuchArgument;

public record LoginImpl(String email, String pass) implements Login <UserApp>{

    @Override
    public UserApp toLogin(String email, String password) throws NoSuchArgument {
        Usuario user = new UsuarioDAO().searchByValue(email);
        if (user !=  null){
            Empresa emp = new EmpresaDAO().searchByValue(user.getID());
            if (emp != null){
                return new UserApp(email, password, user, emp);
            }
        }
        throw new NoSuchArgument("Email não encontrado!");
    }

    @Override
    public boolean recuperatePassword(String email) {
        return false;
    }
}
