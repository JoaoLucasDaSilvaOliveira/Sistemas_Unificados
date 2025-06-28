package Model;

import Model.EXEPTIONS.NoSuchArgument;

public interface Login <T>{
    public T toLogin(String email, String password) throws NoSuchArgument;
    public boolean recuperatePassword (String email);
}
