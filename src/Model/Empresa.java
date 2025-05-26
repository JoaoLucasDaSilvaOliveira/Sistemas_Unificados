package Model;

import Control.EmpresaController;
import Model.EXEPTIONS.InvalidFormatException;

import java.util.Objects;

public class Empresa {
    //não usaremos o construtor padrão para inicialização e sim um métod-o estático para isso
    private Empresa (String CNPJ, String name, Status status){
        this.ID = ++currentID;
        this.CNPJ = CNPJ;
        setName(name);
        setStatus(status);
    }

    public static Empresa create (String CNPJ, String name, Status status) throws InvalidFormatException {
        //TODO: fazer as verificações aqui e lançar os erros se necessário
        return EmpresaController.validateEntrys(new Empresa(CNPJ, name, status));
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Empresa empresa)) return false;
        return ID == empresa.ID && Objects.equals(CNPJ, empresa.CNPJ) && Objects.equals(name, empresa.name) && status == empresa.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(CNPJ, ID, name, status);
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "CNPJ='" + CNPJ + '\'' +
                ", ID=" + ID +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
