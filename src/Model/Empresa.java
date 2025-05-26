package Model;

import Control.EmpresaController;
import DAO.EmpresaDAO;
import Model.EXEPTIONS.ExistingInstance;
import Model.EXEPTIONS.InvalidFormatException;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Empresa {

    static{
        EmpresaDAO e = new EmpresaDAO();
        List<Empresa> empresas = e.searchAll();
        if (empresas.isEmpty()){
            Empresa.currentID = 0;
        } else {
            for (Empresa temp : empresas){
                Empresa.currentID = temp.getID();
            }
        }
    }

    //não usaremos o construtor padrão para inicialização e sim um métod-o estático para isso
    private Empresa (String CNPJ, String name, Status status){
        this.CNPJ = CNPJ;
        setName(name);
        setStatus(status);
        ID = ++currentID;
    }

    public static Empresa create (String CNPJ, String name, Status status) throws InvalidFormatException, ExistingInstance {
        //TODO: fazer as verificações aqui e lançar os erros se necessário
        return EmpresaController.validateEntrys(new Empresa (CNPJ, name, status));
    }

    private final String CNPJ;
    private final int ID;
    private String name;
    private Status status;
    private static int currentID;

    public enum Status {
        ACTIVE,
        INACTIVE
    }

    private int IdGenerate (Empresa empresa){
        EmpresaDAO empDAO = new EmpresaDAO();
        List<Empresa> empresas = empDAO.searchAll();
        Iterator<Empresa> iter = empresas.iterator();
        if(!empresas.contains(empresa)){
            if (!empresas.isEmpty()){
                int id=0;
                while (iter.hasNext()){
                    Empresa temp = iter.next();
                    id = temp.getID();
                }
                return id+1;
            }
        }
        return 1;
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

    public int ID() {
        return ID;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Empresa empresa)) return false;
        if ((Objects.equals(CNPJ, empresa.CNPJ) && Objects.equals(name, empresa.name) && status == empresa.status) || ID == empresa.ID){
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
