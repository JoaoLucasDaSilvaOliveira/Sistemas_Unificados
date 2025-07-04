package Model;

import Model.EXEPTIONS.NoSuchArgument;

public interface Login <T>{
    public T toLogin(String email, String password) throws NoSuchArgument;
    public static void showLoginInfos(String userName, String email, String enterpriseName, String CNPJ){
        System.out.printf("Empresa: CNPJ: %-25s Nome: %s\n", CNPJ, enterpriseName);
        System.out.printf("Usuário: Nome: %-25s Email: %s\n", userName, email);
    }
}
