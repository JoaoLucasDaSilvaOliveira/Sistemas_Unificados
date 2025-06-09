package Model;

import DAO.UsuarioDAO;

import java.util.List;
import java.util.UUID;

public abstract class Guia {

    static{
        UsuarioDAO e = new UsuarioDAO();
        //ver se o searchAll de UsuárioDAO funciona igual o de empresaDAO!
        List<Usuario> usuarios = e.searchAll();
        if (usuarios.isEmpty()){
            Usuario.currentID = 0;
        } else {
            for (Usuario temp : usuarios){
                Usuario.currentID = temp.getID();
            }
        }
    }

    public Guia (){
        this.idSerial = UUID.randomUUID();
    }
    private final UUID idSerial;
    private final ID;
}
