package DAO;

import Model.Empresa;
import Model.Empresa.Status;

public class App {
    public static void main(String[] args) throws Exception{
        //testando se o cadastro de empresa funciona
        Empresa emp = Empresa.create("12.345.789/0001-01", "Nome", Status.ACTIVE);
        System.out.println(emp);
    }
}
