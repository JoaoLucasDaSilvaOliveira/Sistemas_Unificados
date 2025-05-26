package Model;

public interface Login {
    public boolean toLogin(String email, String password);
    public boolean recuperatePassword (String email);
}
