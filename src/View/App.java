package View;

import DAO.EmpresaDAO;
import Model.EXEPTIONS.ExistingInstance;
import Model.EXEPTIONS.InvalidFormatException;
import Model.Empresa;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class App {
    public static void main(String[] args) {
        //testando se o cadastro de empresa funciona
        boolean entradaValidada = false;
        //while (!entradaValidada){
        try{
            Empresa emp = Empresa.create("11.345.789/0001-02", "Jaozinhos House", Empresa.Status.ACTIVE);
            System.out.println(emp);
//            System.out.println(emp2);
            entradaValidada = true;
        } catch (InvalidFormatException e) {
            System.out.println(e.getMessage());
        } catch (ExistingInstance e){
            System.out.println(e.getMessage());
        }
        //} //HABILITAR APENAS COM ENTRADAS DO USUÁRIO
    }
}
