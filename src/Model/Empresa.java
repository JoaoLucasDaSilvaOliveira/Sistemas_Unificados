package Model;

public class Empresa {
    private Empresa (String CNPJ, String name, Status status){
        this.ID = ++currentID;
        this.CNPJ = CNPJ;
    }

    private final String CNPJ;
    private final int ID;
    private String name;
    private Status status;
    private static int currentID = 0;

    private enum Status {
        ACTIVE,
        INACTIVE
    }
}
