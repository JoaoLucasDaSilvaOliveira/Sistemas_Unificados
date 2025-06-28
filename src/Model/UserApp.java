package Model;

public class UserApp {

    public UserApp(String pass, String email, Usuario u, Empresa e) {
        this.pass = pass;
        this.email = email;
        this.u = u;
        this.e = e;
    }

    private final Empresa e;
    private final Usuario u;
    private final String email;
    private final String pass;

    public Empresa getEmpresa() {
        return e;
    }

    public Usuario getUsuario() {
        return u;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

}
