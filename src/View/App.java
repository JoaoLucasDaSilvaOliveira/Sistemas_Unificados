package View;

import DAO.EmpresaDAO;
import Model.EXEPTIONS.ExistingInstance;
import Model.EXEPTIONS.InvalidFormatException;
import Model.EXEPTIONS.NoSuchArgument;
import Model.Empresa;
import Model.LoginImpl;
import Model.UserApp;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
    }
    private static void menu (){
        var scanf = new Scanner(System.in);
        System.out.print("Seja bem vindo ao Sistemas Unificados!\n\nPor favor, escolha uma opção:\n\n1. Já tenho usuario, fazer login\n2. Criar um novo usuario e vincular empresa\n3. Esqueci minha senha\n\nOpção: ");
        switch (scanf.nextLine()){
            case "1":
                        break;

            case "2":
                        break;

            case "3":
                        break;

            default: menu();
        }
    }
    private static void login (Scanner scan){
        System.out.print("\n\nOK!\nPor favor, digite seu email: ");
        String email = scan.nextLine();
        System.out.print("\nPor favor, digite sua senha: ");
        String senha = scan.nextLine();
        try {
            UserApp login = new LoginImpl(email, senha).toLogin();
        } catch (NoSuchArgument e){
            System.out.println(e.getMessage());
        }
    }
}
