package Model;

import Model.EXEPTIONS.InvalidFormatException;

public class App {
    //use this class for testing the current package
    public static void main(String[] args) {
        try{
            Empresa e = Empresa.create("11.111.711/0001-10", "Testando", Empresa.Status.ACTIVE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
