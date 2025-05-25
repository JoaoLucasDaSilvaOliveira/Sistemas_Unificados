package Model;

import java.util.Locale;

public class Empresa {
    //não usaremos o construtor padrão para inicialização e sim um métod-o estático para isso
    private Empresa (String CNPJ, String name, Status status){
        this.ID = ++currentID;
        this.CNPJ = CNPJ;
        setName(name);
        setStatus(status);
    }

    public static Empresa create (String CNPJ, String name, Status status){
        //TODO: fazer as verificações aqui e lançar os erros se necessário
        return new Empresa(CNPJ, name, status);
    }

    private final String CNPJ;
    private final int ID;
    private String name;
    private Status status;
    private static int currentID = 0;

    public enum Status {
        ACTIVE,
        INACTIVE
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static int getCurrentID() {
        return currentID;
    }
}
