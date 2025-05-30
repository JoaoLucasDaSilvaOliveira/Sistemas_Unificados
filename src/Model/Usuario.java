package Model;

import DAO.UsuarioDAO;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Usuario {

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

    //não usaremos o construtor padrão para inicialização e sim um métod-o estático para isso


    private Usuario(String name, String email, String senha) {
        this.name = name;
        this.email = email;
        this.senha = senha;
        ID = ++currentID;
    }

    private final int ID;
    private String name;
    private String email;
    private String senha;
    //<codEmpresa, objEmpresa>
    private Map<Integer, Empresa> empresas = new LinkedHashMap<>();
    private static int currentID;

    public void includeEmpresa (Empresa e){
        if (e == null) throw new NullPointerException("Empresa não pode ser nula!");
        // a chave é o id da empresa e o value é a empresa de fato!
        empresas.put(e.getID(), e);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getID() {
        return ID;
    }

    public void redefinirSenha (String email){

    }
}
