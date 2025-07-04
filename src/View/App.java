package View;

import Model.EXEPTIONS.NoSuchArgument;
import Model.EXEPTIONS.WrongPass;
import Model.UserApp;

import java.util.Scanner;

/**
 * Classe principal que gerencia a interface do usuário do Sistema Unificado.
 * Responsável por exibir menus e gerenciar as operações de login.
 */
public class App {
    /**
     * Método principal que inicia a aplicação.
     *
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        menu();
    }

    /**
     * Exibe o menu principal do sistema e processa as escolhas do usuário.
     * Oferece opções para login, criação de usuário, recuperação de senha e saída.
     */
    private static void menu (){
        var scanf = new Scanner(System.in);
        while (true){
            System.out.print("Seja bem vindo ao Sistemas Unificados!\n\nPor favor, escolha uma opção:\n\n0. Sair do sistema\n1. Já tenho usuario, fazer login\n2. Criar um novo usuario e vincular empresa\n3. Esqueci minha senha\n\nOpção: ");
            switch (scanf.nextLine()){
                case "0":
                    System.out.println("Obrigado por usar nosso sistema!");
                    System.exit(0);
    
                case "1": login(scanf);
                            break;
    
                case "2": LoginImpl.criarUser(scanf);
                            break;
    
                case "3": LoginImpl.recuperatePass(scanf);
                            break;
    
                default: menu();
            }
        }
    }

    /**
     * Gerencia o processo de login do usuário.
     *
     * @param scan Scanner para leitura dos dados de entrada do usuário
     */
    private static void login (Scanner scan){
        System.out.print("OK!\nPor favor, digite seu email: ");
        String email = scan.nextLine();
        System.out.print("Por favor, digite sua senha: ");
        String senha = scan.nextLine();
        try {
            UserApp userApp = new LoginImpl(email, senha).toLogin(email, senha);
            LoginImpl.telaPrincipal(userApp, scan);
        } catch (NoSuchArgument | WrongPass e){
            System.out.println(e.getMessage());
            System.out.println("Deseja tentar novamente?\n\nPressione qualquer tecla - Sim\n2 - Sair do sistema");
            if (scan.nextLine().equals("2")){
                System.out.println("Obrigado por usar nosso sistema!");
                System.exit(0);
            }
            login(scan);
        }
    }
}
