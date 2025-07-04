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

/**
 * Representa um usuário do sistema. Contém dados pessoais, credenciais de acesso e as empresas associadas ao usuário.
 */
public class Usuario {

    /**
     * Bloco estático para inicializar o ID sequencial do usuário com base nos registros existentes no banco de dados.
     */
    static {
        UsuarioDAO e = new UsuarioDAO();
        List<Usuario> usuarios = e.searchAll();
        if (usuarios.isEmpty()) {
            Usuario.currentID = 0;
        } else {
            for (Usuario temp : usuarios) {
                Usuario.currentID = temp.getID();
            }
        }
    }

    /**
     * Construtor usado pela biblioteca Jackson para desserialização de objetos.
     *
     * @param name     nome do usuário.
     * @param email    e-mail do usuário.
     * @param senha    senha do usuário.
     * @param empresas mapa de empresas associadas ao usuário.
     */
    @JsonCreator
    private Usuario(
            @JsonProperty("name") String name,
            @JsonProperty("email") String email,
            @JsonProperty("senha") String senha,
            @JsonProperty("empresas") Map<Integer, Empresa> empresas) {
        this.name = name;
        this.senha = senha;
        this.email = email;
        this.senha = senha;
        this.ID = ++currentID;
    }

    /**
     * Construtor interno utilizado pelo método estático {@code create}.
     *
     * @param name  nome do usuário.
     * @param email e-mail do usuário.
     * @param senha senha do usuário.
     * @param e     empresa inicial a ser associada ao usuário.
     */
    private Usuario(String name, String email, String senha, Empresa e) {
        this.name = name;
        this.email = email;
        this.senha = senha;
        ID = ++currentID;
        includeEmpresa(e);
        EmpresaController.putOwnerId(this.ID, e);
    }

    /**
     * Cria e valida um novo usuário no sistema, utilizando o {@link UsuarioController}.
     *
     * @param name     nome do usuário.
     * @param email    e-mail do usuário.
     * @param password senha do usuário.
     * @param e        empresa associada ao usuário.
     * @return uma nova instância de {@code Usuario}.
     * @throws InvalidFormatException se algum campo estiver mal formatado.
     * @throws ExistingInstance       se o usuário já existir.
     */
    public static Usuario create(String name, String email, String password, Empresa e)
            throws InvalidFormatException, ExistingInstance {
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

    /**
     * Mapa que associa o código da empresa ao objeto {@link Empresa}.
     */
    @JsonProperty("empresas")
    private Map<Integer, Empresa> empresas = new LinkedHashMap<>();

    /** ID incremental usado para controlar a criação de novos usuários. */
    private static int currentID;

    /**
     * Associa uma nova empresa ao usuário.
     *
     * @param e empresa a ser associada.
     * @throws NullPointerException se a empresa for nula.
     */
    public void includeEmpresa(Empresa e) {
        if (e == null) throw new NullPointerException("Empresa não pode ser nula!");
        e.setCodDono(this.ID);
        empresas.put(e.getID(), e);
    }

    /**
     * Retorna as empresas vinculadas ao usuário.
     *
     * @return mapa de empresas.
     */
    public Map<Integer, Empresa> getEmpresas() {
        return empresas;
    }

    /**
     * Define um novo mapa de empresas para o usuário.
     *
     * @param empresas novo mapa.
     */
    public void setEmpresas(Map<Integer, Empresa> empresas) {
        this.empresas = empresas;
    }

    /**
     * Retorna o nome do usuário.
     *
     * @return nome.
     */
    public String getName() {
        return name;
    }

    /**
     * Define um novo nome para o usuário.
     *
     * @param name novo nome.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna o e-mail do usuário.
     *
     * @return e-mail.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define um novo e-mail para o usuário.
     *
     * @param email novo e-mail.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna a senha do usuário.
     *
     * @return senha.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define uma nova senha para o usuário.
     *
     * @param senha nova senha.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Retorna o ID do usuário.
     *
     * @return ID numérico.
     */
    public int getID() {
        return ID;
    }

    /**
     * Recupera a senha de um usuário pelo e-mail e define uma nova senha.
     *
     * @param email   e-mail do usuário.
     * @param newPass nova senha a ser definida.
     */
    public static void recuperatePassword(String email, String newPass) {
        Usuario userTemp = new UsuarioDAO().searchByValue(email);
        if (userTemp == null) {
            System.out.println("Email não cadastrado!");
        } else {
            userTemp.setSenha(newPass);
            UsuarioDAO u = new UsuarioDAO();
            u.update(userTemp);
            System.out.println("Senha alterada com sucesso!");
        }
    }

    /**
     * Redefine o e-mail de um usuário, se nome e senha corresponderem.
     *
     * @param name      nome do usuário.
     * @param password  senha atual.
     * @param newEmail  novo e-mail a ser atribuído.
     * @return {@code true} se a alteração foi realizada com sucesso.
     */
    public boolean redefinirEmail(String name, String password, String newEmail) {
        if (name == null || password == null)
            throw new NullPointerException("O email e a senha não podem ser nulos amigo!");

        UsuarioDAO u = new UsuarioDAO();
        List<Usuario> searching = u.searchAll();
        for (Usuario us : searching) {
            if (us.getEmail().matches(name) && us.getSenha().matches(password)) {
                us.setEmail(newEmail);
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna uma representação textual do usuário, incluindo ID, nome, email e empresas.
     *
     * @return representação como {@code String}.
     */
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

    /**
     * Compara dois objetos {@code Usuario} com base nos atributos principais.
     *
     * @param o objeto a ser comparado.
     * @return {@code true} se os objetos forem considerados iguais.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Usuario usuario)) return false;
        return ID == usuario.ID ||
                (Objects.equals(name, usuario.name) &&
                        Objects.equals(email, usuario.email) &&
                        Objects.equals(senha, usuario.senha) &&
                        Objects.equals(empresas, usuario.empresas));
    }

    /**
     * Gera o código hash com base nos atributos do usuário.
     *
     * @return valor hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(ID, name, email, senha, empresas);
    }
}
