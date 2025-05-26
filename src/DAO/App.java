package DAO;

import Model.EXEPTIONS.InvalidFormatException;
import Model.Empresa;
import Model.Empresa.Status;

public class App {
    public static void main(String[] args) {
        //testando se o cadastro de empresa funciona
        boolean entradaValidada = false;
        //while (!entradaValidada){
            try{
                Empresa emp = Empresa.create("12.345.789/0001-01", "Nome", Status.ACTIVE);
                System.out.println(emp);
                entradaValidada = true;
            } catch (InvalidFormatException e) {
                System.out.println(e.getMessage());
            }
        //} //HABILITAR APENAS COM ENTRADAS DO USUÁRIO
    }
}
