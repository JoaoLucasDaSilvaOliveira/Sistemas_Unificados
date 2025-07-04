package Model;

import java.util.Objects;

/**
 * Representa um usuário autenticado no aplicativo, contendo suas credenciais,
 * dados pessoais e informações da empresa associada.
 *
 * Essa classe geralmente é usada após o login para armazenar o contexto da sessão.
 */
public class UserApp {

    /**
     * Construtor da classe {@code UserApp}.
     *
     * @param pass  Senha do usuário.
     * @param email E-mail do usuário.
     * @param u     Objeto {@link Usuario} representando os dados pessoais do usuário.
     * @param e     Objeto {@link Empresa} representando a empresa vinculada ao usuário.
     */
    public UserApp(String pass, String email, Usuario u, Empresa e) {
        this.pass = pass;
        this.email = email;
        this.u = u;
        this.e = e;
    }

    /** Empresa associada ao usuário. */
    private final Empresa e;

    /** Dados do usuário (nome, cargo, etc.). */
    private final Usuario u;

    /** E-mail do usuário. */
    private final String email;

    /** Senha do usuário (em texto puro — atenção à segurança!). */
    private final String pass;

    /**
     * Retorna o objeto {@link Empresa} vinculado ao usuário.
     *
     * @return empresa do usuário.
     */
    public Empresa getEmpresa() {
        return e;
    }

    /**
     * Retorna o objeto {@link Usuario} com os dados pessoais do usuário.
     *
     * @return usuário.
     */
    public Usuario getUsuario() {
        return u;
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
     * Retorna a senha do usuário.
     * @return senha.
     */
    public String getPass() {
        return pass;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserApp userApp)) return false;
        return Objects.equals(e, userApp.e) && Objects.equals(u, userApp.u) && Objects.equals(email, userApp.email) && Objects.equals(pass, userApp.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(e, u, email, pass);
    }

    @Override
    public String toString() {
        return "UserApp{" +
                "e=" + e +
                ", u=" + u +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
