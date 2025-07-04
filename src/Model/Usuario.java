package Model;

import Control.EmpresaController;
import Control.UsuarioController;
import DAO.UsuarioDAO;
import Model.EXEPTIONS.ExistingInstance;
import Model.EXEPTIONS.InvalidFormatException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    // Construtor com anotações para o Jackson
    @JsonCreator
    private Usuario(
            @JsonProperty("name") String name, // O JSON contém "cnpj", que será mapeado para o campo "CNPJ"
            @JsonProperty("email") String email,
            @JsonProperty("senha") String senha,
            @JsonProperty("empresas") Map<Integer, Empresa> empresas) {
        this.name = name;
        this.senha = senha;
        this.email = email;
        this.senha = senha;
        this.ID = ++currentID;
    }

    //não usaremos o construtor padrão para inicialização e sim um métod-o estático para isso
    private Usuario(String name, String email, String senha, Empresa e) {
        this.name = name;
        this.email = email;
        this.senha = senha;
        ID = ++currentID;
        includeEmpresa(e);
        EmpresaController.putOwnerId(this.ID, e);
    }

    public static Usuario create (String name, String email, String password, Empresa e) throws InvalidFormatException, ExistingInstance {
        //TODO: fazer as verificações aqui e lançar os erros se necessário
        UsuarioController control = new UsuarioController();
        return control.validateAndCreate(new Usuario(name, email, password, e));
    }

    @JsonProperty("id")
    private final int ID;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("senha")
    private String senha;
    //<codEmpresa, objEmpresa>
    @JsonProperty("empresas")
    private Map<Integer, Empresa> empresas = new LinkedHashMap<>();
    private static int currentID;

    public void includeEmpresa (Empresa e){
        if (e == null) throw new NullPointerException("Empresa não pode ser nula!");
        e.setCodDono(this.ID);
        // a chave é o id da empresa e o value é a empresa de fato!
        empresas.put(e.getID(), e);
    }

    public Map<Integer, Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(Map<Integer, Empresa> empresas) {
        this.empresas = empresas;
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

    public static void recuperatePassword(String email, String newPass) {
        Usuario userTemp = new UsuarioDAO().searchByValue(email);
        if (userTemp == null){
            System.out.println("Email não cadastrado!");
        } else{
            userTemp.setSenha(newPass);
            UsuarioDAO u = new UsuarioDAO();
            u.update(userTemp);
            System.out.println("Senha alterada com sucesso!");
        }
    }

    public boolean redefinirEmail (String name, String password, String newEmail){
        if (name == null || password == null) throw new NullPointerException("O email e a senha não podem ser nulos amigo!");
        UsuarioDAO u = new UsuarioDAO();
        List<Usuario> searching = u.searchAll();
        for (Usuario us : searching){
            if (us.getEmail().matches(name) && us.getSenha().matches(password)){
                us.setEmail(newEmail);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", empresas=" + empresas +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Usuario usuario)) return false;
        return ID == usuario.ID || (Objects.equals(name, usuario.name) && Objects.equals(email, usuario.email) && Objects.equals(senha, usuario.senha) && Objects.equals(empresas, usuario.empresas));
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, email, senha, empresas);
    }
}
