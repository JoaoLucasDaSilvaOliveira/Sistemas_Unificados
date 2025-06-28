package Model;

import Control.EmpresaController;
import DAO.EmpresaDAO;
import Model.EXEPTIONS.ExistingInstance;
import Model.EXEPTIONS.InvalidFormatException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Empresa {

    static {
        EmpresaDAO e = new EmpresaDAO();
        List<Empresa> empresas = e.searchAll();
        if (empresas.isEmpty()) {
            Empresa.currentID = 0;
        } else {
            for (Empresa temp : empresas) {
                Empresa.currentID = temp.getID();
            }
        }
    }

    // Construtor com anotações para o Jackson
    @JsonCreator
    private Empresa(
            @JsonProperty("cnpj") String CNPJ, // O JSON contém "cnpj", que será mapeado para o campo "CNPJ"
            @JsonProperty("name") String name,
            @JsonProperty("status") Status status) {
        this.CNPJ = CNPJ;
        this.codDono = codDono;
        setName(name);
        setStatus(status);
        this.ID = ++currentID;
    }

    public static Empresa create (String CNPJ, String name, Status status) throws InvalidFormatException, ExistingInstance {
        //TODO: fazer as verificações aqui e lançar os erros se necessário
        EmpresaController control = new EmpresaController();
        return control.validateAndCreate(new Empresa(CNPJ, name, status));
    }

    @JsonProperty("cnpj") // Mapeia para "cnpj" no JSON
    private final String CNPJ;
    @JsonProperty("ID") // Mapeia para "ID" no JSON
    private final int ID;
    @JsonProperty("name") // Mapeia para "name" no JSON
    private String name;
    @JsonProperty("status") // Mapeia para "status" no JSON
    private Status status;
    private static int currentID;
    private int codDono;

    public int getCodDono() {
        return codDono;
    }

    public void setCodDono(int codDono) {
        this.codDono = codDono;
    }

    public enum Status {
        ACTIVE,
        INACTIVE
    }

    private int IdGenerate(Empresa empresa) {
        EmpresaDAO empDAO = new EmpresaDAO();
        List<Empresa> empresas = empDAO.searchAll();
        Iterator<Empresa> iter = empresas.iterator();
        if (!empresas.contains(empresa)) {
            if (!empresas.isEmpty()) {
                int id = 0;
                while (iter.hasNext()) {
                    Empresa temp = iter.next();
                    id = temp.getID();
                }
                return id + 1;
            }
        }
        return 1;
    }

    // Getters

    @JsonProperty("cnpj") // Indica que este getter está relacionado ao campo "cnpj" no JSON
    public String getCNPJ() {
        return CNPJ;
    }

    @JsonProperty("ID") // Indica que este getter está relacionado ao campo "ID" no JSON
    public int getID() {
        return ID;
    }

    @JsonProperty("name") // Indica que este getter está relacionado ao campo "name" no JSON
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("status") // Indica que este getter está relacionado ao campo "status" no JSON
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    // Equals e hashCode

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Empresa empresa)) return false;
        if ((Objects.equals(CNPJ, empresa.CNPJ) && Objects.equals(name, empresa.name) && status == empresa.status) || ID == empresa.ID) {
            return true;
        }
        return false;
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